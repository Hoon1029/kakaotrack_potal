package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    public User get(Integer code) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/kakao_potal_card_platform?serverTimezone=Asia/Seoul", "root", "1234");
        PreparedStatement preparedStatement = connection.prepareStatement("select code, id, password, name, owner_flag from user where code = ?");
        preparedStatement.setInt(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        User user = new User();
        user.setCode(resultSet.getInt("code"));
        user.setId(resultSet.getString("id"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setOwnerFlag(resultSet.getBoolean("owner_flag"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }
}
