package model;
import java.util.Comparator;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;


public class Wishlist {

    private final int wishlistId;
    private Player player;
    private List<Game> games;

    public Wishlist(int wishlistId, Player player, List<Game> games){
        this.wishlistId = wishlistId;
        this.player = player;
        this.games = games;
    }

    public int getWishlistId(){
        return wishlistId;
    }

    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player){
        this.player = player;
    }

    public List<Game> getGames(){
        return new ArrayList<>(games);
    }
    public void setGames(List<Game> games){
        this.games = games;
    }

    public void addGame(Game game){
        games.add(game);
    }

    public void removeGame(Game game){
        games.remove(game);
    }


    @Override
    public String toString(){
        return player.getUsername() + "'s Wishlist: " + games.toString();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Wishlist wishlist)) return false;
        return wishlistId == wishlist.wishlistId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(wishlistId);
    }
}
