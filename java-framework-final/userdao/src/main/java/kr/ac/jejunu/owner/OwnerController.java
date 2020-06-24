package kr.ac.jejunu.owner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jejunu.login.UserManager;
import kr.ac.jejunu.user.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @RequestMapping("/couponList/{shopId}")
    public ModelAndView couponList(@PathVariable("shopId") Integer shopId){
        @Data
        class CouponData{
            String couponName, productName;
            Integer productPrice, couponInforId;
        }
        ArrayList<CouponInfor> couponInfors = couponInforDao.getByShopId(shopId);
        return null;
    }
}