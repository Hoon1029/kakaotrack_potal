package kr.ac.jejunu.userdao;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.expression.spel.ast.NullLiteral;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    Integer id = 1;
    String name = "hoon";
    String password = "1234";

    private static UserDao userDao;

    @BeforeAll
    public static void setup(){
    //  ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
    //  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("daoFactory.xml");
    //  ApplicationContext applicationContext = new GenericGroovyApplicationContext("daoFactory.groovy");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.userdao");
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testInsert () throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        User insertedUser = userDao.get(user.getId());

        assertThat(user.getId(), is(insertedUser.getId()));
        assertThat(user.getName(), is(insertedUser.getName()));
        assertThat(user.getPassword(), is(insertedUser.getPassword()));
    }

    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);
        user.setName("updated name");
        user.setPassword("updated Password");
        userDao.update(user);

        User updatedUser = new User();
        updatedUser = userDao.get(user.getId());
        assertThat(user.getId(), is(updatedUser.getId()));
        assertThat(user.getName(), is(updatedUser.getName()));
        assertThat(user.getPassword(), is(updatedUser.getPassword()));
    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        user = userDao.get(user.getId());
        assertThat(user, IsNull.nullValue());
    }
}
//DB_URL=jdbc:mysql://localhost/kakao_potal_midexam?serverTimezone=Asia/Seoul;DB_CLASSNAME=com.mysql.cj.jdbc.Driver;DB_USERNAME=root;DB_PASSWORD=1234