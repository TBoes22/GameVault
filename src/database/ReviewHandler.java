package database;

import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewHandler {
    public void createTable(){
            String sql = "CREATE TABLE IF NOT EXISTS reviews ("+ "reviewId INTEGER PRIMARY KEY, " + "playerId INTEGER, " + "gameId INTEGER, " + "rating INTEGER, " + "note TEXT," + "reviewTime TEXT)";
            try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement()){
                stmt.executeUpdate(sql);
                System.out.println("Table created.");
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public Review convertToObject(ResultSet rs) throws Exception{
        return new Review(
                rs.getInt("reviewId"),
                rs.getInt("playerId"),
                rs.getInt("gameId"),
                rs.getInt("rating"),
                rs.getString("note"),
                rs.getString("reviewTime")
        );
    }


    public synchronized void saveReview(Review review, int playerId, int gameId){
        String sql = "INSERT OR REPLACE INTO reviews VALUES (?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, review.getReviewId());
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, gameId);
            pstmt.setInt(4, review.getRating());
            pstmt.setString(5, review.getNote());
            pstmt.setString(6, review.getReviewTime().toString());
            pstmt.executeUpdate();
            System.out.println("Review saved.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized boolean deleteReview(int reviewId){
        String sql = "DELETE FROM reviews WHERE reviewId = ?";
        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, reviewId);
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                System.out.println("Review has been deleted!");
                return true;
            }else{
                System.out.println("Review not found!");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Review> findGameReview(int gameId){
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE gameId = ?";
        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                reviews.add(convertToObject(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reviews;
    }

    public synchronized void updateReview(Review review){
        String sql = "UPDATE reviews SET rating = ?, note = ?, reviewTime = ? WHERE reviewId = ?";
        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, review.getRating());
            pstmt.setString(2, review.getNote());
            pstmt.setString(3, review.getReviewTime().toString());
            pstmt.setInt(4, review.getReviewId());
            pstmt.executeUpdate();
            System.out.println("Review has been updated.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
