package models;
import interfaces.SearchCollection;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Game implements SearchCollection, Serializable {

    private final int gameId;
    private String title;
    private Developer developer;
    private LocalDate releaseDate;
    private Genre genre;
    private Platform platform;
    private double price;
    private Status status;

    public Game(int gameId,String title, Developer developer, LocalDate releaseDate, Genre genre, Platform platform, double price, Status status){
        this.gameId = gameId;
        this.title = title;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.platform = platform;
        this.price = price;
        this.status = status;
    }

    //Folgender Konstruktor dient NUR der WIshlistHandler-Klasse. Anders hat es leider nicht geklappt. Bin offen fuer Feedback ob und wie ich es anders loesen kann.
    public Game(int gameId, String title, Developer developer, Platform platform, double price){
        this.gameId = gameId;
        this.title = title;
        this.developer = developer;
        this.platform = platform;
        this.price = price;
    }

    public int getGameId(){
        return gameId;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Developer getDeveloper(){
        return developer;
    }
    public void setDeveloper(Developer developer){
        this.developer = developer;
    }

    public LocalDate getReleaseDate(){
        return releaseDate;
    }

    public Genre getGenre(){
        return genre;
    }
    public void setGenre(Genre genre){
        this.genre = genre;
    }

    public Platform getPlatform(){
        return platform;
    }
    public void setPlatform(Platform platform){
        this.platform = platform;
    }

    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public Status getStatus(){
        return status;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public boolean matchingKeyword(String keyword){
        return title.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString(){
        return title + ", " + platform + ", " + status;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;
        return gameId == game.gameId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(gameId);
    }



}
