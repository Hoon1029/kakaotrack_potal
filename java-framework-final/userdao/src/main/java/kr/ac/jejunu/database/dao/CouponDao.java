package kr.ac.jejunu.database.dao;

import kr.ac.jejunu.database.object.Coupon;
import kr.ac.jejunu.database.object.CouponInfor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CouponDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final CouponInforDao couponInforDao;
    @Autowired
    private final ProductDao productDao;

    public Coupon get(Integer couponId, String userId){
        Object[] params = new Object[]{couponId, userId};
        String sql = "select couponInforId, userId, num from coupon where couponInforId = ? and userId = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            Coupon coupon = null;
            CouponInfor couponInfor = null;
            if(rs.next()){
                couponInfor = couponInforDao.get(couponId);
                coupon = Coupon.builder()
                        .couponInforId(rs.getInt("couponInforId"))
                        .userId(rs.getString("userId"))
                        .num(rs.getInt("num")).build();
            }
            return coupon;
        });
    }

    public void insert(Coupon coupon) {
        Object[] params = new Object[]{coupon.getCouponInforId(), coupon.getUserId(), coupon.getNum()};
        String sql = "insert into user (couponInforId, userId, num) values (?, ?, ?)";
        jdbcTemplate.update(sql, params);
    }

    public void update(Coupon coupon){
        Object[] params = new Object[]{coupon.getCouponInforId(), coupon.getUserId()};
        String sql = "update coupon set couponInforId = ?, userId = ?, num = ? where couponInforId = ? and userId = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Coupon coupon){
        delete(coupon.getCouponInforId(), coupon.getUserId());
    }

    public void delete(Integer couponInforId, String userId) {
        Object[] params = new Object[]{couponInforId, userId};
        String sql = "delete from coupon where couponInfor = ? and userId = ?";
        jdbcTemplate.update(sql, params);
    }
}