package kr.ac.jejunu.user;

import java.sql.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcContext) {
        this.jdbcTemplate = jdbcContext;
    }

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
                user.setOwnerFlag(rs.getBoolean("owner_flag"));
            }
            return user;
        });

    }

    public void insert(User user) {
        Object[] params = new Object[]{user.getId(), user.getPassword(), user.getName(), user.isOwnerFlag()};
        String sql = "insert into user (id, password, name, ownerFlag) values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, params);
//        KeyHolder keyHolder = new GeneratedKeyHolder();

//        jdbcTemplate.update(con -> {
//            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            for (int i = 0; i < params.length; i++) {
//                preparedStatement.setObject(i + 1, params[i]);
//            }
//            return preparedStatement;
//        }, keyHolder);
//        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) {
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        String sql = "update user set password = ?, name = ?, ownerFlag = ? where id = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(String id) {
        Object[] params = new Object[]{id};
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql, params);
    }

}
