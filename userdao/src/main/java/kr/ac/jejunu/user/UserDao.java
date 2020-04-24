package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final DataSource dataSource;

    public UserDao (DataSource dataSource){
        this.dataSource = dataSource;
    }

    public User get(Integer id) throws ClassNotFoundException, SQLException {
        //mysql
        //driver 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection
        Connection connection = dataSource.getConnection();
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

    public void insert(User user) throws ClassNotFoundException, SQLException {
        //mysql
        //driver 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection
        Connection connection = dataSource.getConnection();
        //query
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into user_infor (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        //실행
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        //결과매핑
        user.setId(resultSet.getInt(1));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과리턴
    }
}
