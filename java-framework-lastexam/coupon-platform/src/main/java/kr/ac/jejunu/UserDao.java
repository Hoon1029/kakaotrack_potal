package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select id, password, name, owner_flag from user where id = ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false) {
                return null;
            }

            user = new User();
            user.setId(resultSet.getString("id"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setOwnerFlag(resultSet.getBoolean("owner_flag"));
        } finally {

            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

            try{
                preparedStatement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("insert into user (id, password, name, owner_flag) values(?, ?, ?, ?)");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setBoolean(4, user.isOwnerFlag());
            preparedStatement.executeUpdate();
        } finally {
            try{
                preparedStatement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}
