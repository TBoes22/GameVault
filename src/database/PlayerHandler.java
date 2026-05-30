package database;

import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class PlayerHandler {
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS players(" + "playerId INTEGER PRIMARY KEY," + "username TEXT," + "email Text)";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
            System.out.println("Table has been created.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Player convertToObject(ResultSet rs) throws Exception{
        return new Player(
                rs.getInt("playerId"),
                rs.getString("username"),
                rs.getString("email")
        );
    }




    public void savePlayer(Player player){
        String sql = "INSERT INTO players VALUES (?,?,?)";
        try(Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, player.getPlayerId());
            pstmt.setString(2, player.getUsername());
            pstmt.setString(3, player.getEmail());
            pstmt.executeUpdate();
            System.out.println("Player saved.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deletePlayer(int playerId){
        String sql = "DELETE FROM players WHERE playerId = ?";
        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, playerId);
            pstmt.executeUpdate();
            System.out.println("Player has been deleted.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePlayer(Player player){
        String sql = "UPDATE players SET username = ?, email = ? WHERE playerId = ?";
        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, player.getUsername());
            pstmt.setString(2, player.getEmail());
            pstmt.setInt(3, player.getPlayerId());
            pstmt.executeUpdate();
            System.out.println("Playerdata has been updated.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Player findPlayerById(int playerId){
        String sql = "SELECT * FROM players WHERE playerId = ?";
        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return convertToObject(rs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
