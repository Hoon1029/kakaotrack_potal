package ac.kr.jejunu.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuUserDao extends UserDao{

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/kakao_potal?serverTimezone=Asia/Seoul", "root", "1234");
    }
}
