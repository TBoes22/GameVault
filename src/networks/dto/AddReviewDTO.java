package networks.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class AddReviewDTO implements Serializable {
    private final int reviewId;
    private final int playerId;
    private final String gameTitle;
    private final int rating;
    private final String note;


    public AddReviewDTO(int reviewId, int playerId, String gameTitle, int rating, String note){
        this.reviewId = reviewId;
        this.playerId = playerId;
        this.gameTitle = gameTitle;
        this.rating = rating;
        this.note = note;
    }

    public int getReviewId() {
        return reviewId;
    }
    public int getPlayerId() {
        return playerId;
    }
    public String getGameTitle(){
        return gameTitle;
    }
    public int getRating() {
        return rating;
    }
    public String getNote() {
        return note;
    }
}
