package kr.ac.jejunu.database.dao;

import kr.ac.jejunu.database.object.Shop;
import kr.ac.jejunu.database.object.StampRequest;
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
public class StampRequestDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StampRequest get(Integer couponInforId, String customerId){
        Object[] params = new Object[]{couponInforId, customerId};
        String sql = "select id, ownerId, name, address, locateX, locateY from shop where id = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            StampRequest stampRequest = null;
            if (rs.next()) {
                stampRequest = StampRequest.builder()
                        .couponInforId(rs.getInt("couponInforId"))
                        .customerId(rs.getString("customerId"))
                        .stampNum(rs.getInt("stampNum")).build();
            }
            return stampRequest;
        });
    }

    public void insert(StampRequest stampRequest) {
        Object[] params = new Object[]{stampRequest.getCouponInforId(), stampRequest.getCustomerId(), stampRequest.getStampNum()};
        String sql = "insert into shop * values (?, ?, ?)";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer couponInforId, String customerId) {
        Object[] params = new Object[]{couponInforId, customerId};
        String sql = "delete from user where couponInforId = ? and customerId = ?";
        jdbcTemplate.update(sql, params);
    }
}
