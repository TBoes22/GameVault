package model;
import java.util.Objects;

public class Player {

    private final int playerId;
    private String username;
    private String email;

    public Player(int playerId, String username, String email){
        this.playerId = playerId;
        this.username = username;
        this.email = email;
    }

    public int getPlayerId(){
        return playerId;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString(){
        return username;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Player player)) return false;
        return playerId == player.playerId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(playerId);
    }
}
