package kr.ac.jejunu.user;

import kr.ac.jejunu.database.object.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class LombokTests {
    @Test
    public void equals() {
        User user1 = User.builder().id("1").name("hoon").password("1234").build();
        User user2 = User.builder().id("1").name("hoon").password("1234").build();
        assertThat(user1, is(user2));
    }
}
