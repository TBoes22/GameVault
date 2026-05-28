package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String url = "jdbc:sqlite:Mydatabase.db";

    public static Connection connect(){
        try {
            return DriverManager.getConnection(url);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
