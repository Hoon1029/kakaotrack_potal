package kr.ac.jejunu;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(String id){
        Object[] params = new Object[] {id};
        String sql = "select id, password, name, owner_flag from user where id = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            User user = null;
            if(rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setOwnerFlag(rs.getBoolean("owner_flag"));
            }
            return user;
        });
    }

    public void insert(User user){
        Object[] params = new Object[] {user.getId(), user.getPassword(), user.getName(), user.isOwnerFlag()};
        String sql = "insert into user (id, password, name, owner_flag) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql, params);
//        jdbcTemplate.update(con -> {
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            for(int i=0 ; i<params.length; i++){
//                preparedStatement.setObject(i+1, params[i]);
//            }
//            return preparedStatement;
//        }
//        );
    }

    public void update(User user){
        Object[] params = new Object[] {user.getPassword(), user.getName(), user.isOwnerFlag(), user.getId()};
        String sql = "update user set password = ?, name = ?, owner_flag = ? where id = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(String id){
        Object[] params = new Object[] {id};
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql, params);
    }
//    public User get(String id) throws ClassNotFoundException, SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        User user = null;
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement("select id, password, name, owner_flag from user where id = ?");
//            preparedStatement.setString(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next() == false) {
//                return null;
//            }
//
//            user = new User();
//            user.setId(resultSet.getString("id"));
//            user.setPassword(resultSet.getString("password"));
//            user.setName(resultSet.getString("name"));
//            user.setOwnerFlag(resultSet.getBoolean("owner_flag"));
//        } finally {
//
//            try{
//                resultSet.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//
//            try{
//                preparedStatement.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//
//            try{
//                connection.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
//
//        return user;
//    }
//
//    public void insert(User user) throws ClassNotFoundException, SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement("insert into user (id, password, name, owner_flag) values(?, ?, ?, ?)");
//            preparedStatement.setString(1, user.getId());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setString(3, user.getName());
//            preparedStatement.setBoolean(4, user.isOwnerFlag());
//            preparedStatement.executeUpdate();
//        } finally {
//            try{
//                preparedStatement.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//
//            try{
//                connection.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void update(User user) throws SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement("update user set password = ?, name = ?, owner_flag = ? where id = ?");
//            preparedStatement.setString(1, user.getPassword());
//            preparedStatement.setString(2, user.getName());
//            preparedStatement.setBoolean(3, user.isOwnerFlag());
//            preparedStatement.setString(4, user.getId());
//            preparedStatement.executeUpdate();
//
//        } finally {
//            try{
//                preparedStatement.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//            try{
//                connection.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void delete(String id) throws SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = dataSource.getConnection();
//
//            preparedStatement = connection.prepareStatement("delete from user where id = ?");
//            preparedStatement.setString(1, id);
//            preparedStatement.executeUpdate();
//
//        } finally {
//            try{
//                preparedStatement.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//            try{
//                connection.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
}
