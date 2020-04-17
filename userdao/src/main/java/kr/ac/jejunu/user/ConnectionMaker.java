package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    abstract public Connection getConnection() throws ClassNotFoundException, SQLException;
}
