package kr.ac.jejunu.user;

import kr.ac.jejunu.database.object.Shop;
import kr.ac.jejunu.database.dao.ShopDao;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@AllArgsConstructor
public class ShopDaoTest {

    private static ShopDao shopDao;
    Integer id = 1;

    @BeforeAll
    public static void setup() {
        //  ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        //  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("daoFactory.xml");
        //  ApplicationContext applicationContext = new GenericGroovyApplicationContext("daoFactory.groovy");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
        shopDao = applicationContext.getBean("shopDao", ShopDao.class);
    }

    @Test
    public void get (){
        Shop shop = shopDao.get(id);
        printShop(shop);
    }

    public void printShop(Shop shop){
        System.out.println("id: "+shop.getId());
        System.out.println("owner_id: "+shop.getOwnerId());
        System.out.println("name: "+shop.getName());
        System.out.println("address: "+shop.getAddress());
        System.out.println("locate_x: "+shop.getLocateX());
        System.out.println("locate_y: "+shop.getLocateY());
    }

    // DB_PASSWORD=1234;DB_URL=jdbc:mysql://localhost/kakao_potal?serverTimezone=Asia/Seoul;DB_USERNAME=root;DB_CLASSNAME=com.mysql.cj.jdbc.Driver
}
