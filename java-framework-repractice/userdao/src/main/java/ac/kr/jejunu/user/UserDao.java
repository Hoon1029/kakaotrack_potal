package ac.kr.jejunu.user;

import java.sql.*;
import java.sql.DriverManager;

abstract public class UserDao {

    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("select id, name, password from user_infor where id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return user;
    }


    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into user_infor (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    abstract public Connection getConnection() throws ClassNotFoundException, SQLException ;

}
