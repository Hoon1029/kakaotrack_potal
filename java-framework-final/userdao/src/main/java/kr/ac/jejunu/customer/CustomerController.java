package kr.ac.jejunu.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jejunu.login.UserManager;
import kr.ac.jejunu.user.*;
import lombok.*;
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

@RequestMapping("/customer/*")
public class CustomerController {
    private final UserDao userDao;
    private final ShopDao shopDao;
    private final UserManager userManager;
    private final CouponInforDao couponInforDao;
    private final CouponDao couponDao;
    private final ObjectMapper objectMapper;
    private final ProductDao productDao;

    @RequestMapping(path = "/shopList")
    public ModelAndView index(HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();;
        if(userManager.isOnLogin(request)){
            User user = userManager.getUser(request);
            ArrayList<Shop> shops = shopDao.getByUserId(user.getId());
            modelAndView.addObject("loginFlag", true);
            modelAndView.addObject("user", userManager.getUser(request));
            modelAndView.addObject("shopsJson", objectMapper.writeValueAsString(shops));
        }
        else{
            modelAndView.addObject("loginFlag", false);
        }
        return modelAndView;
    }

    @GetMapping("/couponList/{shopId}")
    public ModelAndView couponList(@PathVariable("shopId") Integer shopId, HttpServletRequest request) throws JsonProcessingException {
        @Data
        class CouponData{
            String couponName, productName;
            Integer productPrice, stampNum;
            Integer couponInforId;
        }
        String userId = userManager.getUser(request).getId();
        ArrayList<CouponInfor> couponInfors = couponInforDao.getByShopId(shopId);
        ArrayList<CouponData> couponDatas = new ArrayList<CouponData>();
        Coupon coupon = null;
        Product product = null;
        CouponInfor couponInfor = null;
        CouponData couponData = null;
        for(int i=0 ; i<couponInfors.size() ; i++){
            coupon = couponDao.get(couponInfors.get(i).getId(), userId);
            if(coupon != null) {
                System.out.println("1234");
                couponInfor = couponInforDao.get(coupon.getCouponInforId());
                if(couponInfor == null)
                    System.out.println("hello");
                System.out.println(couponInfor.getProductId());
                product = productDao.get(couponInfor.getProductId());
                System.out.println("1234");
                couponData = new CouponData();
                couponData.setCouponName(couponInfor.getName());
                couponData.setProductName(product.getName());
                couponData.setProductPrice(product.getPrice());
                couponData.setStampNum(coupon.getNum());
                couponData.setCouponInforId(couponInfor.getId());
                couponDatas.add(couponData);
            }
        }
        ModelAndView modelAndView = new ModelAndView("customer/couponList");
        modelAndView.addObject("shop", shopDao.get(shopId));
        modelAndView.addObject("couponsJson", objectMapper.writeValueAsString(couponDatas));
        return modelAndView;
    }
}