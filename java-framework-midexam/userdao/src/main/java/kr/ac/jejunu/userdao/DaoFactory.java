package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ejb.config.JeeNamespaceHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;

@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    String className;
    @Value("${db.url}")
    String url;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;
//
//    @Bean
//    public UserDao userDao() throws SQLException, ClassNotFoundException {
//        return new UserDao(jdbcTemplate() );
//    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException, ClassNotFoundException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws SQLException, ClassNotFoundException {

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
            dataSource.setUrl(url);
            dataSource.setUsername(username);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        dataSource.setPassword(password);
        return dataSource;
    }
}
