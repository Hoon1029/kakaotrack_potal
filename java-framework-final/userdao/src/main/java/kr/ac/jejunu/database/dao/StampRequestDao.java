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
import java.util.ArrayList;

@AllArgsConstructor
@Component
public class StampRequestDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ArrayList<StampRequest> getAllById(Integer couponInforId){
        Object[] params = new Object[]{couponInforId};
        String sql = "select * from stampRequest where couponInforId = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            ArrayList<StampRequest> stampRequests = new ArrayList<StampRequest>();
            StampRequest stampRequest = null;
            while (rs.next()) {
                stampRequest = StampRequest.builder()
                        .id(rs.getInt("id"))
                        .couponInforId(rs.getInt("couponInforId"))
                        .customerId(rs.getString("customerId"))
                        .stampNum(rs.getInt("stampNum")).build();
                stampRequests.add(stampRequest);
            }
            return stampRequests;
        });
    }

    public StampRequest getById(Integer id){
        Object[] params = new Object[]{id};
        String sql = "select * from stampRequest where id = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            StampRequest stampRequest = null;
            if (rs.next()) {
                stampRequest = StampRequest.builder()
                        .id(rs.getInt("id"))
                        .couponInforId(rs.getInt("couponInforId"))
                        .customerId(rs.getString("customerId"))
                        .stampNum(rs.getInt("stampNum")).build();
            }
            return stampRequest;
        });
    }
//
//    public StampRequest getById(Integer couponInforId, String customerId){
//        Object[] params = new Object[]{couponInforId, customerId};
//        String sql = "select * from stampRequest where couponInforId = ? and customerId = ?";
//        return jdbcTemplate.query(sql, params, rs -> {
//            StampRequest stampRequest = null;
//            if (rs.next()) {
//                stampRequest = StampRequest.builder()
//                        .id(rs.getInt("id"))
//                        .couponInforId(rs.getInt("couponInforId"))
//                        .customerId(rs.getString("customerId"))
//                        .stampNum(rs.getInt("stampNum")).build();
//            }
//            return stampRequest;
//        });
//    }

    public void insert(StampRequest stampRequest) {
        Object[] params = new Object[]{stampRequest.getCouponInforId(), stampRequest.getCustomerId(), stampRequest.getStampNum()};
        String sql = "insert into stampRequest (couponInforId, customerId, stampNum) values (?, ?, ?)";
        jdbcTemplate.update(sql, params);
    }

    public void deleteById(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "delete from stampRequest where id = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer couponInforId, String customerId) {
        Object[] params = new Object[]{couponInforId, customerId};
        String sql = "delete from stampRequest where couponInforId = ? and customerId = ?";
        jdbcTemplate.update(sql, params);
    }
}
