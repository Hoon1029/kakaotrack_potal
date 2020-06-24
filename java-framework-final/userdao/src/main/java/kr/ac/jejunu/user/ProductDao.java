package kr.ac.jejunu.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@AllArgsConstructor
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public Product get(Integer id){
        Object[] params = new Object[]{id};
        String sql = "select id, shopId, name, price, sellFlag from product where id = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            Product product = null;
            if(rs.next()){
                product = Product.builder()
                        .id(rs.getInt("id"))
                        .shopId(rs.getInt("shopId"))
                        .name(rs.getString("name"))
                        .price(rs.getInt("price"))
                        .sellFlag(rs.getBoolean("sellFlag")) .build();
            }
            return product;
        });
    }
}
