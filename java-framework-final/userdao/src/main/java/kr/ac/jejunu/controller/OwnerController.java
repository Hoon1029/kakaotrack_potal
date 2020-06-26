package kr.ac.jejunu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jejunu.database.dao.*;
import kr.ac.jejunu.database.object.*;
import kr.ac.jejunu.login.UserManager;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.UnsupportedEncodingException;
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
    private final StampRequestDao stampRequestDao;
    private final CouponDao couponDao;

    @RequestMapping(path = "/shopList")
    public ModelAndView index(HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("owner/shopList");;
        User user = userManager.getUser(request);
        ArrayList<Shop> shops = shopDao.getByOwnerId(user.getId());
        modelAndView.addObject("shopsJson", objectMapper.writeValueAsString(shops));
        return modelAndView;
    }

    @RequestMapping(path = "/createShop" ,method = RequestMethod.POST)
    public String createShop(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
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
        ArrayList<StampRequest> stampRequests = new ArrayList<StampRequest>();
        for(int i=0 ; i<couponInfors.size() ; i++) {
            ArrayList<StampRequest> temp = stampRequestDao.getAllById(couponInfors.get(i).getId());
            stampRequests.addAll(temp);
        }
        ArrayList<String> couponNames = new ArrayList<String>();
        for(int i=0 ; i<stampRequests.size() ; i++){
            couponNames.add(couponInforDao.get(stampRequests.get(i).getCouponInforId()).getName());
        }
        ModelAndView modelAndView = new ModelAndView("owner/shop");
        modelAndView.addObject("shopId", shopId);
        modelAndView.addObject("productsJson", objectMapper.writeValueAsString(products));
        modelAndView.addObject("couponInforsJson", objectMapper.writeValueAsString(couponInfors));
        modelAndView.addObject("stampRequestsJson", objectMapper.writeValueAsString(stampRequests));
        modelAndView.addObject("stampRequestNamesJson", objectMapper.writeValueAsString(couponNames));
        return modelAndView;
    }

    @PostMapping("/enrollProduct/{shopId}")
    public String enrollProduct(@PathVariable("shopId") Integer shopId, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
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
    public String enrollCoupon(@PathVariable("shopId") Integer shopId, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
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

    @RequestMapping("/acceptStampRequest/{id}")
    public String acceptStampRequest(@PathVariable("id") Integer id) {
        StampRequest stampRequest = stampRequestDao.getById(id);
        String customerId = stampRequest.getCustomerId();
        Integer couponInforId = stampRequest.getCouponInforId();
        Integer shopId = couponInforDao.get(couponInforId).getShopId();
        Coupon coupon = couponDao.get(couponInforId, customerId);
        CouponInfor couponInfor = couponInforDao.get(couponInforId);
        Integer stampNum = coupon.getNum()+stampRequest.getStampNum();
        if(stampNum >= couponInfor.getMaxStampNum()){
            coupon.setNum(couponInfor.getMaxStampNum());
        }else{
            coupon.setNum(stampNum);
        }
        couponDao.update(coupon);
        stampRequestDao.deleteById(id);
        return "redirect:/owner/shop/"+shopId;
    }

    @RequestMapping("/refuseStampRequest/{id}")
    public String refuseStampRequest(@PathVariable("id") Integer id) {
        StampRequest stampRequest = stampRequestDao.getById(id);
        Integer shopId = couponInforDao.get(stampRequest.getCouponInforId()).getShopId();
        stampRequestDao.delete(stampRequest.getCouponInforId(), stampRequest.getCustomerId());
        return "redirect:/owner/shop/"+shopId;
    }
}