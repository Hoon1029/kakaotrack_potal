package kr.ac.jejunu.controller;

import kr.ac.jejunu.login.UserManager;
import kr.ac.jejunu.database.User;
import kr.ac.jejunu.database.UserDao;
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

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;
    private final UserManager userManager;

    @RequestMapping(path = "/*")
    public void all(){
        return;
    }

    @RequestMapping(path = "/index")
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

    @RequestMapping(path = "/login_request")
    public ModelAndView login(HttpServletRequest request, HttpSession session){
        User user = User.builder().id(request.getParameter("id"))
                .password(request.getParameter("password")).build();

        ModelAndView modelAndView;

        if(userManager.isMember(user)) {
            userManager.login(user, request);
            modelAndView = new ModelAndView("redirect:/customer/shopList");
        } else {
            modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginFlag", false);
            modelAndView.addObject("message", "It's invalid user infor");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/join_request")
    public ModelAndView join(HttpServletRequest request, HttpSession session){
        User user = User.builder().id(request.getParameter("id"))
                .password(request.getParameter("password"))
                .name(request.getParameter("name")).build();

        ModelAndView modelAndView = new ModelAndView("join");

        if(userManager.isAlreadyExist(user)){
            modelAndView.addObject("message", "It's already exist");
        }else{
            userManager.createUser(user);
            modelAndView.addObject("message", "The join has been success");
        }

        return modelAndView;
    }

    @RequestMapping("/exception")
    public void exception(){ throw new RuntimeException("어이쿠!"); }

    @RequestMapping(path ="/upload", method = RequestMethod.GET)
    public void upload(){ }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/") + "/WEB-INF/static/" + file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOUtputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOUtputStream.write(file.getBytes());
        bufferedOUtputStream.close();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "/images/"+file.getOriginalFilename());
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().invalidate();
        return new ModelAndView("login");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", e);
        return modelAndView;
    }
}