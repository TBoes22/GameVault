package database;

import java.sql.Connection;
import java.sql.Statement;

public class PlayerHandler {
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS games (" + "gameId INTEGER PRIMARY KEY, " + "title TEXT, " +
                "platform TEXT, " + "developer TEXT, " + "genre TEXT, " + "releaseDate REAL, " +
                "price TEXT, " + "status TEXT)";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
            System.out.println("Table created.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
