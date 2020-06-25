package kr.ac.jejunu.user;

import kr.ac.jejunu.login.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Component
public class MainInterceptor implements HandlerInterceptor {
    @Autowired
    private final UserManager loginManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("*************** interceptor prehandle ***************");

//        ModelAndView modelAndView;
//        if(!loginManager.isOnLogin(request)) {
//            response.sendRedirect("/login");
//            return false;
//        }
//        return true;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("*************** interceptor posthandle ***************");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("*************** interceptor afterhandle ***************");
    }
}
