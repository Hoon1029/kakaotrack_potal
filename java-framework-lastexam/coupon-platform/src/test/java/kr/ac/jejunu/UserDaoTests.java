package kr.ac.jejunu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
public class UserDaoTests {

    private static UserDao userDao;

    @BeforeAll
    public static void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        String id = "yh1233";
        User user = userDao.get(id);

        if(user == null)
            System.out.println("null");
        else
            assertThat(user.getId(), is(id));

    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String id = "mh1234567891012";
        String password = "1234";
        String name = "한명훈";
        boolean ownerFlag = false;

        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setOwnerFlag(ownerFlag);
        userDao.insert(user);

        User user2 = userDao.get(id);
        System.out.println(user2.getId());
        System.out.println(user2.getPassword());
        System.out.println(user2.getName());
        System.out.println(user2.isOwnerFlag());
    }
    //DB_CLASSNAME=com.mysql.cj.jdbc.Driver;DB_USERNAME=root;DB_URL=jdbc:mysql://localhost/kakao_potal_card_platform?serverTimezone=Asia/Seoul;DB_PASSWORD=1234
}
