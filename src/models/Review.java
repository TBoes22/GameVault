package models;
import java.util.Objects;
import java.time.LocalDate;

public class Review {
    private final int reviewId;
    private final int playerId;
    private final int gameId;
    private int rating;
    private String note;
    private LocalDate reviewTime;

    public Review(int reviewId,int playerId, int gameId, int rating, String note){
        this.reviewId = reviewId;
        this.playerId = playerId;
        this.gameId = gameId;
        this.rating = rating;
        this.note = note;
        this.reviewTime = LocalDate.now();
    }

    //Folgender Konstruktor nur fuer ReviewHandler
    public Review (int reviewId, int playerId, int gameId, int rating, String note, String reviewTime){
        this.reviewId = reviewId;
        this.playerId = playerId;
        this.gameId = gameId;
        this.rating = rating;
        this.note = note;

        if(reviewTime != null && !reviewTime.isEmpty()){
            try{
                this.reviewTime = LocalDate.parse(reviewTime);
            }catch (Exception e){
                System.out.println("Invalid Date!");
                this.reviewTime = LocalDate.now();
            }
        }else{
            this.reviewTime = LocalDate.now();
        }
    }


    public int getPlayerId(){
        return playerId;
    }
    public int getGameId(){
        return gameId;
    }
    public int getReviewId(){
        return reviewId;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }

    public LocalDate getReviewTime(){
        return reviewTime;
    }
    public void setReviewTime(LocalDate reviewTime){
        this.reviewTime = reviewTime;
    }

    @Override
    public String toString(){
        return note;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Review review)) return false;
        return reviewId == review.reviewId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(reviewId);
    }
}
