package bankSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        // Database connection details
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "bank_db";
        String dbUsername = "karthik";
        String dbPassword = "krithika";

        Class.forName(dbDriver);
        Connection conn = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
        return conn;
    }
}
