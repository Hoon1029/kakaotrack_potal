package kr.ac.jejunu.user;

import org.junit.jupiter.api.Test;
import org.hamcrest.Matcher;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class userDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        UserDao userDao = new jejuUserDao();
        Integer id = 1;
        String name = "hoon";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {

        String name = "helen";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new jejuUserDao();
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(0));

        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    @Test
    public void getHalla() throws SQLException, ClassNotFoundException {
        UserDao userDao = new hallaUserDao();
        Integer id = 1;
        String name = "hoon";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insertHalla() throws SQLException, ClassNotFoundException {

        String name = "helen";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new hallaUserDao();
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(0));

        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }
}
