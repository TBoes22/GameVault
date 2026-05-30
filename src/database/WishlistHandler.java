package database;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class WishlistHandler {
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS wishlist (" + "playerId INTEGER," + "gameId INTEGER," + "title TEXT, " +
                "platform TEXT, " + "developer TEXT, " +
                "price REAL," + "PRIMARY KEY(playerId, gameId))";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
            System.out.println("Table created.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Game convertToObject(ResultSet rs) throws Exception{
        return new Game(
                rs.getInt("gameId"),
                rs.getString("title"),
                new Platform(rs.getString("platform")),
                new Developer(rs.getString("developer")),
                rs.getDouble("price")
        );
    }

    public void addGameToWishlist(int playerId, Game game){
        String sql = "INSERT INTO wishlist VALUES (?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, game.getGameId());
            pstmt.setString(3, game.getTitle());
            pstmt.setString(4, game.getPlatform().toString());
            pstmt.setString(5, game.getDeveloper().toString());
            pstmt.setDouble(6, game.getPrice());
            pstmt.executeUpdate();
            System.out.println("Game has been added to your Wishlist.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeGameFromWishlist(int gameId){
        String sql = "DELETE FROM wishlist WHERE gameId = ?";
        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, gameId);
            pstmt.executeUpdate();
            System.out.println("Game has been removed from your Wishlist.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<Game> showPlayerWishlist(int playerId){
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM wishlist WHERE playerId = ?";
        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                games.add(convertToObject(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return games;
    }

    public void updatePrice(int playerId, int gameId, double new_price){
        String sql = "UPDATE wishlist SET price = ? WHERE playerId = ? AND gameId = ?";
        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, new_price);
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, gameId);
            pstmt.executeUpdate();
            System.out.println("Wishlist has been updated.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
