package kr.ac.jejunu.database.dao;

import java.sql.*;
import java.util.ArrayList;

import kr.ac.jejunu.database.object.Shop;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ShopDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public Shop get(Integer id){
        Object[] params = new Object[]{id};
        String sql = "select id, ownerId, name, address, locateX, locateY from shop where id = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            Shop shop = null;
            if (rs.next()) {
                shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setOwnerId(rs.getString("ownerId"));
                shop.setName(rs.getString("name"));
                shop.setAddress(rs.getString("address"));
                shop.setLocateX(rs.getDouble("locateX"));
                shop.setLocateY(rs.getDouble("locateY"));
            }
            return shop;
        });
    }

    public ArrayList<Shop> getAll(){
        Object[] params = new Object[]{};
        String sql = "select id, ownerId, name, address, locateX, locateY from shop";
        return jdbcTemplate.query(sql, params, rs -> {
            ArrayList<Shop> shops = new ArrayList();
            Shop shop = null;
            while (rs.next()) {
                shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setOwnerId(rs.getString("ownerId"));
                shop.setName(rs.getString("name"));
                shop.setAddress(rs.getString("address"));
                shop.setLocateX(rs.getDouble("locateX"));
                shop.setLocateY(rs.getDouble("locateY"));
                shops.add(shop);
            }
            return shops;
        });
    }

    public ArrayList<Shop> getAllEceptForByCustomerId(String customerId){
        Object[] params = new Object[]{customerId};
        String sql = "select * from shop where id not in (select shopId from enrollment where customerId = ?)";
        return jdbcTemplate.query(sql, params, rs -> {
            ArrayList<Shop> shops = new ArrayList();
            Shop shop = null;
            while (rs.next()) {
                shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setOwnerId(rs.getString("ownerId"));
                shop.setName(rs.getString("name"));
                shop.setAddress(rs.getString("address"));
                shop.setLocateX(rs.getDouble("locateX"));
                shop.setLocateY(rs.getDouble("locateY"));
                shops.add(shop);
            }
            return shops;
        });
    }

    public ArrayList<Shop> getByOwnerId(String ownerId){
        Object[] params = new Object[]{ownerId};
        System.out.println("id: "+ownerId);
        String sql = "select id, ownerId, name, address, locateX, locateY from shop where ownerId = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            ArrayList<Shop> shops = new ArrayList<Shop>();
            Shop shop = null;
            while (rs.next()) {
                shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setOwnerId(rs.getString("ownerId"));
                shop.setName(rs.getString("name"));
                shop.setAddress(rs.getString("address"));
                shop.setLocateX(rs.getDouble("locateX"));
                shop.setLocateY(rs.getDouble("locateY"));
                shops.add(shop);
            }
            return shops;
        });
    }

    public ArrayList<Shop> getByCustomerId(String customerId) {
        Object[] params = new Object[]{customerId};
        String sql = "select * from shop where id in (select shopId from enrollment where customerId = ?)";
        return jdbcTemplate.query(sql, params, rs -> {
            ArrayList<Shop> shops = new ArrayList<Shop>();
            Shop shop = null;
            while (rs.next()) {
                shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setOwnerId(rs.getString("ownerId"));
                shop.setName(rs.getString("name"));
                shop.setAddress(rs.getString("address"));
                shop.setLocateX(rs.getDouble("locateX"));
                shop.setLocateY(rs.getDouble("locateY"));
                shops.add(shop);
            }
            return shops;
        });
    }

    public void insert(Shop shop) {
        Object[] params = new Object[]{shop.getOwnerId(), shop.getName(), shop.getAddress(), shop.getLocateX(), shop.getLocateY()};
        String sql = "insert into shop (ownerId, name, address, locateX, locateY) values (?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, params);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        shop.setId(keyHolder.getKey().intValue());
    }

    public void update(Shop shop) {
        Object[] params = new Object[]{shop.getName(), shop.getAddress(), shop.getLocateX(), shop.getLocateY(), shop.getOwnerId()};
        String sql = "update shop set ownerId = ?, name = ?, address = ?, locateX = ?, locateY = ? where id = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "delete from shop where id = ?";
        jdbcTemplate.update(sql, params);
    }

}
