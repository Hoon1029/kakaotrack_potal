package kr.ac.jejunu;

import kr.ac.jejunu.DaoFactory;
import kr.ac.jejunu.User;
import kr.ac.jejunu.UserDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTests {

    private static UserDao userDao;

    String id = "mh123000";
    String password = "1234";
    String name = "hoon";
    Boolean ownerFlag = false;

    @BeforeAll
    public static void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        User user = userDao.get(id);
        if(user == null)
            System.out.println("null");
        else
            printUser(user);

    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = new User(id, password, name, ownerFlag);
        userDao.insert(user);

        User user2 = userDao.get(id);
        printUser(user2);
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = userDao.get(id);

        user.setName("hoon");
        userDao.update(user);

        User user2 = userDao.get(id);
        printUser(user2);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        userDao.delete(id);

        User user = userDao.get(id);
        if(user == null)
            System.out.println("null");
        else
            printUser(user);
    }

    private void printUser(User user){
        System.out.println("id: "+user.getId());
        System.out.println("password: "+user.getPassword());
        System.out.println("name: "+user.getName());
        System.out.println("owner: "+user.isOwnerFlag());
    }
    //DB_CLASSNAME=com.mysql.cj.jdbc.Driver;DB_USERNAME=root;DB_URL=jdbc:mysql://localhost/kakao_potal_card_platform?serverTimezone=Asia/Seoul;DB_PASSWORD=1234
}
