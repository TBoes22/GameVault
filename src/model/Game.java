package model;

import java.util.Objects;

public class Game {

    private final int gameId;
    private String title;
    private Developer developer;
    private Genre genre;
    private Platform platform;
    private double price;
    private Status status;

    public Game(int gameId, String title,Developer developer, Genre genre, Platform platform, double price, Status status){
        this.gameId = gameId;
        this.title = title;
        this.developer = developer;
        this.genre = genre;
        this.platform = platform;
        this.price = price;
        this.status = status;
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
    public String toString(){
        return title + ", " + platform + ", " + status;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId == game.gameId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(gameId);
    }



}
