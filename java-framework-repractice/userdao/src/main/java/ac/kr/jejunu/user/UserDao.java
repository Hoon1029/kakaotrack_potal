package ac.kr.jejunu.user;

import java.sql.*;
import java.sql.DriverManager;

public class UserDao {

    public User get(Integer id) throws ClassNotFoundException, SQLException {
        //mysql
        //driver 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/kakao_potal?serverTimezone=Asia/Seoul", "root", "1234");

        //query
        PreparedStatement preparedStatement =
                connection.prepareStatement("select id, name, password from user_infor where id = ?");
        preparedStatement.setInt(1, id);

        //실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //결과매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();

        //결과리턴
        return user;
    }
}
