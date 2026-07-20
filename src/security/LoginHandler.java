package security;


import database.DatabaseConnection;

import javax.xml.crypto.Data;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginHandler {
    public String hashPW(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for(byte b : hash){
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public void registration(int playerId, String username, String email, String password) throws Exception{
        String hashedPW = hashPW(password);
        String sql = "INSERT INTO players VALUES (?,?,?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, playerId);
            pstmt.setString(2, username);
            pstmt.setString(3, email);
            pstmt.setString(4, hashedPW);
            pstmt.executeUpdate();
            System.out.println("Registration successful!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int login(String username, String password) throws Exception{
        String hashedPW = hashPW(password);
        String sql = "SELECT * FROM players WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPW);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt("playerId");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
