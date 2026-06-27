package networks.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class AddReviewDTO implements Serializable {
    private final int reviewId;
    private final int playerId;
    private final int gameId;
    private final int rating;
    private final String note;


    public AddReviewDTO(int reviewId, int playerId, int gameId, int rating, String note){
        this.reviewId = reviewId;
        this.playerId = playerId;
        this.gameId = gameId;
        this.rating = rating;
        this.note = note;
    }

    public int getReviewId() {
        return reviewId;
    }
    public int getPlayerId() {
        return playerId;
    }
    public int getGameId() {
        return gameId;
    }
    public int getRating() {
        return rating;
    }
    public String getNote() {
        return note;
    }
}
