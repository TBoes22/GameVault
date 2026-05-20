package model;
import java.util.Objects;
import java.time.LocalDate;

public class Review {
    private int rating;
    private String note;
    private LocalDate reviewTime;

    public Review(int rating, String note){
        this.rating = rating;
        this.note = note;
        this.reviewTime = LocalDate.now();
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
return
    @Override
    public String toString(){
        return note;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rating == review.rating && Objects.equals(note, review.note);
    }

    @Override
    public int hashCode(){
        return Objects.hash(rating, note);
    }
}
