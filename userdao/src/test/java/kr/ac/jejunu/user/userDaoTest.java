package kr.ac.jejunu.user;

import org.junit.jupiter.api.Test;
import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class userDaoTest {
    @Test
    public void get() {
        UserDao userDao = new UserDao();
        Integer id = 1;
        String name = "hoon";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
