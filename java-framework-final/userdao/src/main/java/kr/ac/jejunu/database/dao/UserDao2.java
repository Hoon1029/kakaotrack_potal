package kr.ac.jejunu.database.dao;

import kr.ac.jejunu.database.object.StampRequest;
import kr.ac.jejunu.database.object.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;

@AllArgsConstructor
@Component
public class UserDao2 {
    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public User get(String id) {
    Object[] params = new Object[]{id};
    String sql = "select id, password, name, ownerFlag from user where id = ?";
    return jdbcTemplate.query(sql, params, rs -> {
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setOwnerFlag(rs.getBoolean("ownerFlag"));
        }
        return user;
    });

    }

    public void insert(User user) {
        Object[] params = new Object[]{user.getId(), user.getPassword(), user.getName(), user.isOwnerFlag()};
        String sql = "insert into user (id, password, name, ownerFlag) values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, params);
    }
}
