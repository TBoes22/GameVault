package librarys;
import models.Game;
import java.util.Comparator;

public class compareGamesBy {

    public static class comparePrice implements Comparator<Game>{
        @Override
        public int compare(Game game1, Game game2) {
            return Double.compare(game1.getPrice(), game2.getPrice());
        }
    }

    public static class compareTitle implements Comparator<Game>{
        @Override
        public int compare(Game game1, Game game2){
            return game1.getTitle().compareTo(game2.getTitle());
        }
    }

    public static class compareReleaseDate implements Comparator<Game>{
        @Override
        public int compare(Game game1, Game game2){
            return game1.getReleaseDate().compareTo(game2.getReleaseDate());
        }
    }

    public static class compareDeveloper implements Comparator<Game>{
        @Override
        public int compare(Game game1, Game game2){
            return game1.getDeveloper().getDeveloperName().compareTo(game2.getDeveloper().getDeveloperName());
        }
    }

}
