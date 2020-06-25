package kr.ac.jejunu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jejunu.database.dao.CouponInforDao;
import kr.ac.jejunu.database.dao.ProductDao;
import kr.ac.jejunu.database.dao.ShopDao;
import kr.ac.jejunu.database.dao.UserDao;
import kr.ac.jejunu.database.object.CouponInfor;
import kr.ac.jejunu.database.object.Product;
import kr.ac.jejunu.database.object.Shop;
import kr.ac.jejunu.database.object.User;
import kr.ac.jejunu.login.UserManager;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor

@RequestMapping("/owner/*")
public class OwnerController {
    private final UserDao userDao;
    private final UserManager userManager;
    private final ShopDao shopDao;
    private final ObjectMapper objectMapper;
    private final CouponInforDao couponInforDao;
    private final ProductDao productDao;

    @RequestMapping(path = "/shopList")
    public ModelAndView index(HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("owner/shopList");;
        User user = userManager.getUser(request);
        ArrayList<Shop> shops = shopDao.getByOwnerId(user.getId());
        modelAndView.addObject("shopsJson", objectMapper.writeValueAsString(shops));
        return modelAndView;
    }

    @RequestMapping(path = "/createShop" ,method = RequestMethod.POST)
    public String createShop(HttpServletRequest request){
        User user = userManager.getUser(request);
        Shop shop = Shop.builder()
                .ownerId(user.getId())
                .name(request.getParameter("name"))
                .address(request.getParameter("address"))
                .locateX(Double.valueOf(request.getParameter("locateX")))
                .locateY(Double.valueOf(request.getParameter("locateY"))).build();
            shopDao.insert(shop);
        return "redirect:/owner/shopList";
    }

    @GetMapping("/shop/{shopId}")
    public ModelAndView couponList(@PathVariable("shopId") Integer shopId) throws JsonProcessingException {
        ArrayList<CouponInfor> couponInfors = couponInforDao.getByShopId(shopId);
        ArrayList<Product> products = productDao.getByShopId(shopId);
        ModelAndView modelAndView = new ModelAndView("owner/shop");
        modelAndView.addObject("shopId", shopId);
        modelAndView.addObject("productsJson", objectMapper.writeValueAsString(products));
        modelAndView.addObject("couponInforsJson", objectMapper.writeValueAsString(couponInfors));
        return modelAndView;
    }

    @PostMapping("/enrollProduct/{shopId}")
    public String enrollProduct(@PathVariable("shopId") Integer shopId, HttpServletRequest request){
        Product product = Product.builder()
                .shopId(shopId)
                .name(request.getParameter("name"))
                .price(Integer.valueOf(request.getParameter("price")))
                .sellFlag(true).build();
        System.out.println(request.getParameter("name"));
        productDao.insert(product);
        return "redirect:/owner/shop/{shopId}";
    }

    @PostMapping("/enrollCoupon/{shopId}")
    public String enrollCoupon(@PathVariable("shopId") Integer shopId, HttpServletRequest request){
        CouponInfor couponInfor = CouponInfor.builder()
                .shopId(shopId)
                .name(request.getParameter("name"))
                .productId(Integer.valueOf(request.getParameter("productId")))
                .maxStampNum(Integer.valueOf(request.getParameter("maxStampNum")))
                .backgoundImgId("default.png")
                .stampImgId("default.png").build();
        couponInforDao.insert(couponInfor);
        return "redirect:/owner/shop/{shopId}";
    }

    @RequestMapping("/deleteShop/{shopId}")
    public String deleteShop(@PathVariable("shopId") Integer shopId){
        shopDao.delete(shopId);
        return "redirect:/owner/shopList";
    }

    @RequestMapping("/deleteCouponInfor/{shopId}/{couponInforId}")
    public String deleteCouponInfor(@PathVariable("shopId") Integer shopId,
                                    @PathVariable("couponInforId") Integer couponInforId) {
        couponInforDao.delete(couponInforId);
        return "redirect:/owner/shop/{shopId}";
    }

    @RequestMapping("/deleteProduct/{shopId}/{productId}")
    public String deleteProduct(@PathVariable("shopId") Integer shopId,
                                    @PathVariable("productId") Integer productId) {
        productDao.delete(productId);
        return "redirect:/owner/shop/{shopId}";
    }

}