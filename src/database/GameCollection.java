package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import model.Game;
import model.Status;
import model.Platform;
import model.Developer;
import model.Genre;

public class GameCollection {
    private static final String url = "jdbc:sqlite:Mydatabase.db";

    private Connection connect(){
        try {
            return DriverManager.getConnection(url);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS games (" + "gameId INTEGER PRIMARY KEY, " + "title TEXT, " +
                "platform TEXT, " + "developer TEXT, " + "genre TEXT, " + "releaseDate REAL, " +
                "price TEXT, " + "status TEXT)";
        try (Connection conn = connect();
        Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
            System.out.println("Table created.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveGame(Game game){
        String sql = "INSERT INTO games VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, game.getGameId());
            pstmt.setString(2, game.getTitle());
            pstmt.setString(3, game.getPlatform());
            pstmt.setString(4, game.getDeveloper());
            pstmt.setString(5, game.getGenre());
            pstmt.setString(6, game.getReleaseDate().toString());
            pstmt.setDouble(7, game.getPrice());
            pstmt.setString(8, game.getStatus());
            System.out.println("Game saved!");
        }

    }

    public void deleteGame(int gameId){
        String sql = "DELETE FROM games WHERE gameId = ?";
        try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, gameId);
            pstmt.executeUpdate();
            System.out.println("Game deleted.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showFullCollection(){
        // wird noch implementiert
    }

}
