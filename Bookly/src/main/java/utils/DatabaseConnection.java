package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bookly";  // Update with your database URL
    private static final String USER = "root";  // Update with your database user
    private static final String PASSWORD = "";  // Update with your database password

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            // Load the JDBC driver (optional if using a modern JDK with auto-loading)
            Class.forName("com.mysql.cj.jdbc.Driver");  // For MySQL
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
