package kr.ac.jejunu.database.dao;

import kr.ac.jejunu.database.object.Coupon;
import kr.ac.jejunu.database.object.CouponInfor;
import kr.ac.jejunu.database.object.Enrollment;
import kr.ac.jejunu.database.object.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

@AllArgsConstructor
@Component
public class EnrollmentDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ArrayList<Enrollment> getAllByCustomerId(String customerId){
        Object[] params = new Object[]{customerId};
        String sql = "select shopId, customerId from emrollment where customerId = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
            Enrollment enrollment = null;
            if (rs.next()) {
                enrollment = Enrollment.builder()
                        .shopId(rs.getInt("shopId"))
                        .customerId(rs.getString("customerId")).build();
                enrollments.add(enrollment);
            }
            return enrollments;
        });
    }

    public void insert(Enrollment enrollment){
        Object[] params = new Object[]{enrollment.getShopId(), enrollment.getCustomerId()};
        String sql = "insert into enrollment (shopId, customerId) values (?, ?)";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer shopId, String customerId){
        Object[] params = new Object[]{shopId, customerId};
        String sql = "delete from enrollment where shopId = ? and customerId = ?";
        jdbcTemplate.update(sql, params);
    }
}
