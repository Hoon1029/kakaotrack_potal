package kr.ac.jejunu;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
public class UserDaoTests {

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        UserDao userDao = new UserDao();
        User user = userDao.get(id);


    }
}
