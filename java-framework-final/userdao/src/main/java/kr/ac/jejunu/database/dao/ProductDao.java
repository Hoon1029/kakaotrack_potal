package kr.ac.jejunu.database.dao;

import kr.ac.jejunu.database.object.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
//public interface ProductDao extends JpaRepository <Product, Integer> {
public class ProductDao {
//    @Query(value = "select id, shopId, name, price, sellFlag from product where shopId = ?",
//    nativeQuery = true)
//    ArrayList<Product> findAllByShopId(Integer shopId);
        @Autowired
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

    public void insert(Product product){
        Object[] params = new Object[]{product.getShopId(), product.getName(), product.getPrice(), product.getSellFlag()};
        String sql = "insert into product (shopId, name, price, sellFlag) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        product.setId(keyHolder.getKey().intValue());
    }
}
