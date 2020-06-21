package kr.ac.jejunu.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jejunu.login.UserManager;
import kr.ac.jejunu.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(path = "/index")
    public ModelAndView index(HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();;
        if(userManager.isOnLogin(request)){
            User user = userManager.getUser(request);
            ArrayList<Shop> shops = shopDao.getJoinedShop(user.getId());
            ObjectMapper objectMapper = new ObjectMapper();
            String shopsJson = objectMapper.writeValueAsString(shops);
            modelAndView.addObject("loginFlag", true);
            modelAndView.addObject("user", userManager.getUser(request));
            modelAndView.addObject("shopsJson", shopsJson);



        }
        else{
            modelAndView.addObject("loginFlag", false);
        }
        return modelAndView;
    }
}