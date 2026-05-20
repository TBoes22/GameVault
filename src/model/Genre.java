package model;
import java.util.Objects;


public class Genre {

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
        if(o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(genreType, genre.genreType);
    }

    @Override
    public int hashCode(){
        return Objects.hash(genreType);
    }
}
