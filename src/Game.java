public class Game {

    private final int gameID;
    private String title;
    private String genre;
    private String platform;
    private double price;
    private String status;

    public Game(int gameID, String title, String genre, String platform, double price, String status){
        this.gameID = gameID;
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.price = price;
        this.status = status;
    }

    public int getGameID(){
        return gameID;
    }

    public String title(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getGenre(){
        return this.genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getPlatform(){
        return this.platform;
    }
    public void setPlatform(String platform){
        this.platform = platform;
    }

    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String toString(){
        return title + ", " + platform + ", " + status;
    }



}
