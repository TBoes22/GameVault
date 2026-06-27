package models;
import java.io.Serializable;
import java.util.Objects;


public class Genre implements Serializable {

    private String genreType;

    public Genre(String genreType){
        this.genreType = genreType;
    }

    public String getGenreType(){
        return genreType;
    }
    public void setGenreType(String genreType){
        this.genreType = genreType;
    }

    @Override
    public String toString(){
        return genreType;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Genre genre)) return false;
        return Objects.equals(genreType, genre.genreType);
    }

    @Override
    public int hashCode(){
        return Objects.hash(genreType);
    }
}
