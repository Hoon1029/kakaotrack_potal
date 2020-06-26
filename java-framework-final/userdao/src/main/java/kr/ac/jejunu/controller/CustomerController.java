package kr.ac.jejunu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jejunu.database.dao.*;
import kr.ac.jejunu.database.object.*;
import kr.ac.jejunu.login.UserManager;
import kr.ac.jejunu.database.object.User;
import kr.ac.jejunu.database.dao.UserDao;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
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
    private final EnrollmentDao enrollmentDao;
    private final StampRequestDao stampRequestDao;

    @RequestMapping(path = "/shopList")
    public ModelAndView index(HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();;
        if(userManager.isOnLogin(request)){
            User user = userManager.getUser(request);
            ArrayList<Shop> enrolledShops = shopDao.getByCustomerId(user.getId());
            modelAndView.addObject("loginFlag", true);
            modelAndView.addObject("user", userManager.getUser(request));
            modelAndView.addObject("enrolledShopsJson", objectMapper.writeValueAsString(enrolledShops));

            ArrayList<Shop> unEnrolledShops = shopDao.getAllEceptForByCustomerId(user.getId());
            modelAndView.addObject("unEnrolledShopsJson", objectMapper.writeValueAsString(unEnrolledShops));
        }
        else{
            modelAndView.addObject("loginFlag", false);
        }
        return modelAndView;
    }

    @GetMapping("/couponList/{shopId}")
    public ModelAndView couponList(@PathVariable("shopId") Integer shopId, HttpServletRequest request) throws JsonProcessingException {
        String userId = userManager.getUser(request).getId();
        ArrayList<CouponInfor> couponInfors = couponInforDao.getByShopId(shopId);
        ArrayList<CouponData> enrolledCouponDatas = new ArrayList<CouponData>();
        ArrayList<CouponData> unEnrolledCouponDatas = new ArrayList<CouponData>();
        Coupon coupon = null;
        Product product = null;
        CouponInfor couponInfor = null;
        CouponData couponData = null;
        for(int i=0 ; i<couponInfors.size() ; i++){
            coupon = couponDao.get(couponInfors.get(i).getId(), userId);
            couponInfor = couponInfors.get(i);
            product = productDao.get(couponInfor.getProductId());
            couponData = new CouponData();
            couponData.setCouponName(couponInfor.getName());
            couponData.setProductName(product.getName());
            couponData.setProductPrice(product.getPrice());
            couponData.setMaxStampNum(couponInfor.getMaxStampNum());
            couponData.setCouponInforId(couponInfor.getId());
            if(coupon != null) {
                couponData.setStampNum(coupon.getNum());
                enrolledCouponDatas.add(couponData);
            }else{
                unEnrolledCouponDatas.add(couponData);
            }
        }

        ModelAndView modelAndView = new ModelAndView("customer/couponList");
        modelAndView.addObject("shop", shopDao.get(shopId));
        modelAndView.addObject("enrolledCouponDatasJson", objectMapper.writeValueAsString(enrolledCouponDatas));
        modelAndView.addObject("unEnrolledCouponDatasJson", objectMapper.writeValueAsString(unEnrolledCouponDatas));
        return modelAndView;

    }

    @RequestMapping("/enrollShop/{shopId}")
    public String enrollShop(@PathVariable("shopId") Integer shopId, HttpServletRequest request) throws UnsupportedEncodingException {

        if(userManager.isOnLogin(request)) {
            User user = userManager.getUser(request);
            enrollmentDao.insert(new Enrollment(shopId, user.getId()));
        } else {

        }
        return "redirect:/customer/shopList";
    }

    @RequestMapping("withdrawShop/{shopId}")
    public String withdrawShop(@PathVariable("shopId") Integer shopId, HttpServletRequest request){
        if(userManager.isOnLogin(request)) {
            User user = userManager.getUser(request);
            enrollmentDao.delete(shopId, user.getId());
        } else {

        }
        return "redirect:/customer/shopList";
    }

    @RequestMapping("dropCoupon/{couponInforId}")
    public String dropCoupon(@PathVariable("couponInforId") Integer couponInforId, HttpServletRequest request){
        System.out.println("couponInforId: "+couponInforId);
        Integer shopId = couponInforDao.get(couponInforId).getShopId();
        if(userManager.isOnLogin(request)) {
            User user = userManager.getUser(request);
            couponDao.delete(couponInforId, user.getId());
        } else {

        }
        return "redirect:/customer/couponList/"+String.valueOf(shopId);
    }

    @RequestMapping("/enrollCoupon/{couponInforId}")
    public String enrollCoupon(@PathVariable("couponInforId") Integer couponInforId, HttpServletRequest request){
        Integer shopId = couponInforDao.get(couponInforId).getShopId();
        if(userManager.isOnLogin(request)) {
            User user = userManager.getUser(request);
            Coupon coupon = Coupon.builder()
                    .couponInforId(couponInforId)
                    .customerId(user.getId())
                    .num(0).build();
            couponDao.insert(coupon);
        } else {

        }
        return "redirect:/customer/couponList/"+String.valueOf(shopId);
    }

    @RequestMapping("stampRequest/{couponInforId}/{stampNum}")
    public String stampRequest(@PathVariable("couponInforId") Integer couponInforId,
                               @PathVariable("stampNum") Integer stampNum,
                               HttpServletRequest request){
        Integer shopId = couponInforDao.get(couponInforId).getShopId();
        if(userManager.isOnLogin(request)) {
            User user = userManager.getUser(request);
            StampRequest stampRequest = StampRequest.builder()
                    .couponInforId(couponInforId)
                    .customerId(user.getId())
                    .stampNum(stampNum).build();
            stampRequestDao.insert(stampRequest);
        } else {

        }
        return "redirect:/customer/couponList/"+String.valueOf(shopId);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", e);
        return modelAndView;
    }
}

