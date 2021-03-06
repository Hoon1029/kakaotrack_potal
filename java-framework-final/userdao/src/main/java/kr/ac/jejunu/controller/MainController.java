package kr.ac.jejunu.controller;

import kr.ac.jejunu.database.dao.UserDao2;
import kr.ac.jejunu.login.UserManager;
import kr.ac.jejunu.database.object.User;
import kr.ac.jejunu.database.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserDao userDao;
    private final UserManager userManager;
    private final UserDao2 userDao2;

    @RequestMapping(path = "/*")
    public String all(){
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public ModelAndView loginPage(){
        return new ModelAndView();
    }

    @RequestMapping("/join")
    public ModelAndView joinPage(){
        return new ModelAndView();
    }

    @RequestMapping(path = "/loginRequest")
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

    @RequestMapping(path = "/joinRequest")
    public ModelAndView join(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("hello1");
        User user = User.builder().id(request.getParameter("id"))
                .password(request.getParameter("password"))
                .name(request.getParameter("name")).build();
        System.out.println("hello2");
        ModelAndView modelAndView = new ModelAndView("join");
        System.out.println("hello3");
        if(userManager.isAlreadyExist(user)){
            modelAndView.addObject("message", "It's already exist");
        }else{
//            userManager.createUser(user);
            System.out.println("hello3");
            userDao2.insert(user);
            System.out.println("hello3");
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