package kr.ac.jejunu.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UserServlet extends GenericServlet {

    private UserDao userDao;

    @Override
    public void destroy() {
        System.out.println("*************** servlet destroy ***************");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("*************** servlet init ***************");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
        this.userDao = applicationContext.getBean("userDao", UserDao.class);
        super.init();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("*************** servlet service ***************");
        User user = userDao.get(10);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html>");
        stringBuffer.append("<h1>");
        stringBuffer.append(String.format("Hello %s!", user.getName()));
        stringBuffer.append("</h1>");
        stringBuffer.append("</html>");
        res.setContentType("text/html;charset=UTF-8");
        res.getWriter().println(stringBuffer.toString());
    }
}
