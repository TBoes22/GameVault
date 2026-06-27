package database;

import java.sql.*;

public class DatabaseConnection {
    private static final String url = "jdbc:sqlite:Mydatabase.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
