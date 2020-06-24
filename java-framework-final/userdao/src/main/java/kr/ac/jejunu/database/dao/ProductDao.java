package kr.ac.jejunu.database.dao;

import kr.ac.jejunu.database.object.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public ArrayList<Product> getByShopId(Integer shopId){
        Object[] params = new Object[]{shopId};
        String sql = "select id, shopId, name, price, sellFlag from product where shopId = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            ArrayList<Product> products = new ArrayList<Product>();
            Product product = null;
            while(rs.next()){
                product = Product.builder()
                        .id(rs.getInt("id"))
                        .shopId(rs.getInt("shopId"))
                        .name(rs.getString("name"))
                        .price(rs.getInt("price"))
                        .sellFlag(rs.getBoolean("sellFlag")) .build();
                products.add(product);
            }
            return products;
        });
    }
}
