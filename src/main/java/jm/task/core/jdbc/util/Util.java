package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/task?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "3668829";
    private  final Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

    public Connection getConnection() {
        return connection;
    }

    public Util() throws SQLException {
    }
}
