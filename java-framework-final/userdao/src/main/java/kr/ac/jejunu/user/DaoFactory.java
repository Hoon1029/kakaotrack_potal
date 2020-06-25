package kr.ac.jejunu.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jejunu.database.dao.*;
import kr.ac.jejunu.login.LoginInterceptor;
import kr.ac.jejunu.login.UserManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "kr.ac.jejunu.database.dao", entityManagerFactoryRef = "entityManagerFactoryBean")
public class DaoFactory {

    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(("kr.ac.jejunu.database.object"));
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hiberante.dialect", "org.hibernate.dialect.MySQLDialect");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
//    @Bean
//    public UserDao userDao() {
//        return new UserDao(jdbcTemplete());
//    }
//
//    @Bean
//    public ShopDao shopDao() {
//        return new ShopDao(jdbcTemplete());
//    }
//
//    @Bean
//    public CouponInforDao couponInforDao() {
//        return new CouponInforDao(jdbcTemplete());
//    }
//
//    @Bean
//    public CouponDao couponDao(){
//        return new CouponDao(jdbcTemplete(), couponInforDao(), productDao());
//    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

//    @Bean
//    ProductDao productDao(){ return new ProductDao(jdbcTemplete()); }

    @Bean
    public JdbcTemplate jdbcTemplete() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
//
//    @Bean
//    public UserManager loginManager(){ return new UserManager(userDao());}
//
//    @Bean
//    MainInterceptor userInterceptor(){ return new MainInterceptor(loginManager());}
//    @Bean
//    LoginInterceptor loginInterceptor(){ return new LoginInterceptor(loginManager());}
}
