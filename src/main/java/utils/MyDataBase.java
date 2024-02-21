package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {

    private static final String URL = "jdbc:mysql://localhost:3306/choubikloubiki";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    private static MyDataBase instance;

    private MyDataBase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static synchronized MyDataBase getInstance() {
        if (instance == null) {
            instance = new MyDataBase();
        }
        return instance;
    }

    public static Connection getConnection() {
        try {
            // Always check if the connection is valid before returning it
            if (connection == null || connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("Connected to the database.");
                } catch (SQLException e) {
                    System.err.println("Database connection failed: " + e.getMessage());
                    throw new SQLException("Failed to connect to the database.", e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Ensure you declare 'connection' as non-static to avoid unintended sharing across instances


}
