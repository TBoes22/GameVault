package networks.dto;
import models.Developer;
import models.Genre;
import models.Platform;
import models.Status;

import java.io.Serializable;
import java.time.LocalDate;

public class AddGameDTO implements Serializable{
    private final int gameId;
    private final String title;
    private final Developer developer;
    private final LocalDate releaseDate;
    private final Genre genre;
    private final Platform platform;
    private final double price;
    private final Status status;

    public AddGameDTO(int gameId, String title, Developer developer, LocalDate releaseDate, Genre genre, Platform platform, double price, Status status){
        this.gameId = gameId;
        this.title = title;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.platform = platform;
        this.price = price;
        this.status = status;
    }

    public int getGameId(){
        return gameId;
    }
    public String getTitle(){
        return title;
    }
    public Developer getDeveloper() {
        return developer;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public Genre getGenre() {
        return genre;
    }
    public Platform getPlatform() {
        return platform;
    }
    public double getPrice() {
        return price;
    }
    public Status getStatus() {
        return status;
    }
}
