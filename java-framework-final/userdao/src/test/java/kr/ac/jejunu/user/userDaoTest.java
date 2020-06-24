package kr.ac.jejunu.user;

import kr.ac.jejunu.database.object.User;
import kr.ac.jejunu.database.dao.UserDao;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class userDaoTest {

    private static UserDao userDao;

    Integer id = 1;
    String name = "hoon";
    String password = "1234";

    @BeforeAll
    public static void setup() {
        //  ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        //  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("daoFactory.xml");
        //  ApplicationContext applicationContext = new GenericGroovyApplicationContext("daoFactory.groovy");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        User user = userDao.get("1");
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
        System.out.println(user.getId());
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);
        assertThat(user.getId(), greaterThan("1"));

        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    @Test
    public void update() throws SQLException {
        User user = new User(name, password);
        userDao.insert(user);

        String updatedName = "abc";
        String updatedPassword = "12345";
        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);

        User updatedUser = userDao.get(user.getId());
        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));
    }

    @Test
    public void delete() throws SQLException {
        User user = new User(name, password);
        userDao.insert(user);
        userDao.delete(user.getId());
        User deletedUser = userDao.get(user.getId());

        assertThat(deletedUser, IsNull.nullValue());
    }

    // DB_PASSWORD=1234;DB_URL=jdbc:mysql://localhost/kakao_potal?serverTimezone=Asia/Seoul;DB_USERNAME=root;DB_CLASSNAME=com.mysql.cj.jdbc.Driver
}