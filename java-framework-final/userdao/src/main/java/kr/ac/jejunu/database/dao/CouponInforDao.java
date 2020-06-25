package kr.ac.jejunu.database.dao;

import kr.ac.jejunu.database.object.CouponInfor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@AllArgsConstructor
@Component
public class CouponInforDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ArrayList<CouponInfor> getByShopId(Integer shopId){
        Object[] params = new Object[]{shopId};
        String sql = "select id, shopId, name, productId, backgroundId, stampId from couponInfor where shopId = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            ArrayList<CouponInfor> couponInfors = new ArrayList<CouponInfor>();
            CouponInfor couponInfor = null;
            while(rs.next()){
                 couponInfor = CouponInfor.builder().id(rs.getInt("id"))
                        .shopId(rs.getInt("shopId"))
                        .name(rs.getString("name"))
                        .backgoundId(rs.getString("backgroundId"))
                        .productId(rs.getInt("productId"))
                        .stampId(rs.getString("stampId")).build();
                 couponInfors.add(couponInfor);
            }
            return couponInfors;
        });
    }

    public CouponInfor get(Integer id){
        Object[] params = new Object[]{id};
        String sql = "select id, shopId, name, productId, backgroundId, stampId from couponInfor where id = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            CouponInfor couponInfor = null;
            if (rs.next()) {
                couponInfor = CouponInfor.builder().id(rs.getInt("id"))
                        .shopId(rs.getInt("shopId"))
                        .name(rs.getString("name"))
                        .backgoundId(rs.getString("backgroundId"))
                        .productId(rs.getInt("productId"))
                        .stampId(rs.getString("stampId")).build();
            }
            return couponInfor;
        });
    }
//
//    public ArrayList<Shop> getJoinedShop(String user_id) {
//        Object[] params = new Object[]{user_id};
//        String sql = "select * from shop where id in (select shop_id from shop_join where user_id = ?)";
//        return jdbcTemplate.query(sql, params, rs -> {
//            ArrayList<Shop> shops = null;
//            Shop shop = null;
//            while (rs.next()) {
//                shop = new Shop();
//                shop.setId(rs.getInt("id"));
//                shop.setOwnerId(rs.getString("owner_id"));
//                shop.setName(rs.getString("name"));
//                shop.setAddress(rs.getString("address"));
//                shop.setLocateX(rs.getDouble("locate_x"));
//                shop.setLocateY(rs.getDouble("locate_y"));
//                if( shops == null)
//                    shops = new ArrayList<Shop>();
//                shops.add(shop);
//            }
//            return shops;
//        });
//    }
//
//    public void insert(Shop shop) {
//        Object[] params = new Object[]{shop.getId(), shop.getOwnerId(), shop.getName(), shop.getAddress(), shop.getLocateX(), shop.getLocateY()};
//        String sql = "insert into shop (owner_id, name, address, locate_x, locate_y) values (?, ?, ?, ?, ?, ?)";
////        jdbcTemplate.update(sql, params);
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            for (int i = 0; i < params.length; i++) {
//                preparedStatement.setObject(i + 1, params[i]);
//            }
//            return preparedStatement;
//        }, keyHolder);
//        shop.setId(keyHolder.getKey().intValue());
//    }
//
//    public void update(Shop shop) {
//        Object[] params = new Object[]{shop.getName(), shop.getAddress(), shop.getLocateX(), shop.getLocateY(), shop.getOwnerId()};
//        String sql = "update shop set owner_id = ?, name = ?, address = ?, locate_x = ?, locate_y = ? where id = ?";
//        jdbcTemplate.update(sql, params);
//    }
//
//    public void delete(Integer id) {
//        Object[] params = new Object[]{id};
//        String sql = "delete from shop where id = ?";
//        jdbcTemplate.update(sql, params);
//    }

}
