package kr.ac.jejunu.login;

import kr.ac.jejunu.database.object.User;
import kr.ac.jejunu.database.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Component
public class UserManager {
    @Autowired
    private final UserDao userDao;

    public boolean isOnLogin(HttpServletRequest request){
        String key = request.getCookies()[0].getValue();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(key);
        if(user == null){
            return false;
        }
        return true;
    }

    public boolean isAlreadyExist(User user){
        if(userDao.get(user.getId()) == null)
            return false;
        return true;
    }

    public boolean isMember(User user){
        User userData = userDao.get(user.getId());
        if(userData == null)
            return false;

        System.out.println(userData.getPassword());
        System.out.println(user.getPassword());
        if(!userData.getPassword().equals(user.getPassword()))
            return false;

        return true;
    }

    public void login(User user, HttpServletRequest request){
        String key = request.getCookies()[0].getValue();
        HttpSession session = request.getSession();
        User userData = userDao.get(user.getId());
        session.setAttribute(key, userData);
    }

    public User getUser(HttpServletRequest request){
        Cookie cookie = request.getCookies()[0];
        String key = cookie.getValue();
        HttpSession session = request.getSession();
        return (User)session.getAttribute(key);
    }

    public void createUser(User user){
        userDao.insert(user);
    }
}