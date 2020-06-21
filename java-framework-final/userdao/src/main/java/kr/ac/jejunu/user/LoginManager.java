package kr.ac.jejunu.user;

import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;

@AllArgsConstructor
public class LoginManager{

    private final UserDao userDao;

    public boolean isOnLogin(HttpServletRequest request, HttpSession session){
        String key = request.getCookies()[0].getValue();
        User user = (User)session.getAttribute("key");
        if(user == null){
            return false;
        }
        return true;
    }

    public boolean isMember(User user){
        User userData = userDao.get(user.getId());
        if(userData == null)
            return false;
        System.out.println("g");
        System.out.println(userData.getPassword());
        System.out.println(user.getPassword());
        if(!userData.getPassword().equals(user.getPassword()))
            return false;
        System.out.println("h");
        return true;
    }

    public void login(User user, HttpServletRequest request, HttpSession session){
        String key = request.getCookies()[0].getValue();
        User userData = userDao.get(user.getId());
        session.setAttribute(key, userData);
    }
}