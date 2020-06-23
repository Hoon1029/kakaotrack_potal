package kr.ac.jejunu.owner;

import kr.ac.jejunu.login.UserManager;
import kr.ac.jejunu.user.User;
import kr.ac.jejunu.user.UserDao;
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

@Controller
@RequiredArgsConstructor

@RequestMapping("/owner/*")
public class OwnerController {
    private final UserDao userDao;
    private final UserManager userManager;

    @RequestMapping(path = "/shopList")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();;
        if(userManager.isOnLogin(request)){
            modelAndView.addObject("loginFlag", true);
            modelAndView.addObject("user", userManager.getUser(request));
        }
        else{
            modelAndView.addObject("loginFlag", false);
        }
        return modelAndView;
    }
}