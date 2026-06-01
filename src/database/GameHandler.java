package database;

import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class GameHandler {
    public void saveGame(Game game){
        String sql = "INSERT OR REPLACE INTO games VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, game.getGameId());
            pstmt.setString(2, game.getTitle());
            pstmt.setString(3, game.getPlatform().toString());
            pstmt.setString(4, game.getDeveloper().toString());
            pstmt.setString(5, game.getGenre().toString());
            pstmt.setString(6, game.getReleaseDate().toString());
            pstmt.setDouble(7, game.getPrice());
            pstmt.setString(8, game.getStatus().toString());
            pstmt.executeUpdate();
            System.out.println("Game saved!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteGame(int gameId){
        String sql = "DELETE FROM games WHERE gameId = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, gameId);
            pstmt.executeUpdate();
            System.out.println("Game deleted.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS games (" + "gameId INTEGER PRIMARY KEY, " + "title TEXT, " +
                "platform TEXT, " + "developer TEXT, " + "genre TEXT, " + "releaseDate TEXT, " +
                "price REAL, " + "status TEXT)";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
            System.out.println("Table created.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateGame(Game game){
        String sql = "UPDATE games SET title = ?, platform = ?, developer = ?, genre = ?, releaseDate = ?, price = ?, status = ? WHERE gameId = ?";
        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, game.getTitle());
            pstmt.setString(2, game.getPlatform().toString());
            pstmt.setString(3, game.getDeveloper().toString());
            pstmt.setString(4, game.getGenre().toString());
            pstmt.setString(5, game.getReleaseDate().toString());
            pstmt.setDouble(6, game.getPrice());
            pstmt.setString(7, game.getStatus().toString());
            pstmt.setInt(8, game.getGameId());
            pstmt.executeUpdate();
            System.out.println("Entry has been Updated!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Game convertToObject(ResultSet rs) throws Exception{
        return new Game(
                rs.getInt("gameId"),
                rs.getString("title"),
                new Developer(rs.getString("developer")),
                LocalDate.parse(rs.getString("releaseDate")),
                new Genre(rs.getString("genre")),
                new Platform(rs.getString("platform")),
                rs.getDouble("price"),
                Status.valueOf(rs.getString("status"))
        );
    }

    public Game searchById(int gameId) {
        String sql = "SELECT * FROM games WHERE gameId = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return convertToObject(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Game> showFullCollection(){
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games";
        try (Connection conn = DatabaseConnection.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                games.add(convertToObject(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return games;
    }

}
