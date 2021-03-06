package kr.ac.jejunu.database.dao;

import kr.ac.jejunu.database.object.Coupon;
import kr.ac.jejunu.database.object.CouponInfor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@AllArgsConstructor
@Component
public class CouponDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final CouponInforDao couponInforDao;
    @Autowired
    private final ProductDao productDao;

    public Coupon get(Integer couponInforId, String customerId){
        Object[] params = new Object[]{couponInforId, customerId};
        String sql = "select couponInforId, customerId, num from coupon where couponInforId = ? and customerId = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            Coupon coupon = null;
            CouponInfor couponInfor = null;
            if(rs.next()){
                couponInfor = couponInforDao.get(couponInforId);
                coupon = Coupon.builder()
                        .couponInforId(rs.getInt("couponInforId"))
                        .customerId(rs.getString("customerId"))
                        .num(rs.getInt("num")).build();
            }
            return coupon;
        });
    }

    public void insert(Coupon coupon) {
        Object[] params = new Object[]{coupon.getCouponInforId(), coupon.getCustomerId(), coupon.getNum()};
        String sql = "insert into coupon (couponInforId, customerId, num) values (?, ?, ?)";
        jdbcTemplate.update(sql, params);
    }

    public void update(Coupon coupon){
        Object[] params = new Object[]{coupon.getCouponInforId(), coupon.getCustomerId(), coupon.getNum(), coupon.getCouponInforId(), coupon.getCustomerId()};
        String sql = "update coupon set couponInforId = ?, customerId = ?, num = ? where couponInforId = ? and customerId = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Coupon coupon){
        delete(coupon.getCouponInforId(), coupon.getCustomerId());
    }

    public void delete(Integer couponInforId, String userId) {
        Object[] params = new Object[]{couponInforId, userId};
        String sql = "delete from coupon where couponInforId = ? and customerId = ?";
        jdbcTemplate.update(sql, params);
    }
}
