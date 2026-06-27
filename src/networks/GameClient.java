package networks;
import models.*;
import networks.dto.AddGameDTO;
import networks.dto.AddReviewDTO;
import networks.dto.LoginDTO;
import networks.dto.RegistrationDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class GameClient {
    public StatusCommand login(ObjectOutputStream out, ObjectInputStream in, String username, String password)throws Exception {
        out.writeObject(Commands.LOGIN);
        out.flush();

        out.writeObject(new LoginDTO(username, password));
        out.flush();

        Object response = in.readObject();
        if (response instanceof StatusCommand responseStatus) {
            return responseStatus;
        }
        return StatusCommand.INTERNAL_ERROR;
    }

    public List<Game> getGames(ObjectOutputStream out, ObjectInputStream in) throws Exception{
        out.writeObject(Commands.GET_GAMES);
        out.flush();

        Object response = in.readObject();
        if(response instanceof List<?> games){
            return(List<Game>) games;
        }
        return null;
    }

    public StatusCommand addGame(ObjectOutputStream out, ObjectInputStream in, int gameId, String title, Developer developer, LocalDate releaseDate, Genre genre, Platform platform, double price, Status status) throws Exception{
        out.writeObject(Commands.ADD_GAME);
        out.flush();

        out.writeObject(new AddGameDTO(gameId, title, developer, releaseDate, genre, platform, price, status));
        out.flush();

        Object response = in.readObject();
        if(response instanceof StatusCommand responseStatus){
            return responseStatus;
        }
        return StatusCommand.INTERNAL_ERROR;
    }

    public StatusCommand deleteGame(ObjectOutputStream out, ObjectInputStream in, int gameId)throws Exception {
        out.writeObject(Commands.DELETE_GAME);
        out.flush();
        out.writeObject(gameId);
        out.flush();
        Object response = in.readObject();
        if(response instanceof StatusCommand responseStatus){
            return responseStatus;
        }
        return StatusCommand.INTERNAL_ERROR;
    }

    public List<Game> getWishlist(ObjectOutputStream out, ObjectInputStream in, int playerId) throws Exception{
        out.writeObject(Commands.GET_WISHLIST);
        out.flush();
        out.writeObject(playerId);
        out.flush();
        Object response = in.readObject();
        if(response instanceof List<?> wishlist){
            return (List<Game>) wishlist;
        }
        return null;
    }
    public StatusCommand addToWishlist(ObjectOutputStream out, ObjectInputStream in, int playerId, int gameId, String title, Developer developer, LocalDate releaseDate, Genre genre, Platform platform, double price, Status status) throws Exception{
        out.writeObject(Commands.ADD_TO_WISHLIST);
        out.flush();

        out.writeObject(new AddGameDTO(gameId, title, developer, releaseDate, genre, platform, price, status));
        out.flush();
        out.writeObject(playerId);
        out.flush();
        Object response = in.readObject();
        if(response instanceof StatusCommand responseStatus){
            return responseStatus;
        }
        return StatusCommand.INTERNAL_ERROR;

    }

    public StatusCommand updateWishlist(ObjectOutputStream out, ObjectInputStream in,int playerId, int gameId, double newPrice) throws Exception{
        out.writeObject(Commands.UPDATE_WISHLIST);
        out.flush();
        out.writeObject(gameId);
        out.flush();
        out.writeObject(playerId);
        out.flush();
        out.writeObject(newPrice);
        out.flush();
        Object response = in.readObject();
        if(response instanceof StatusCommand responseStatus){
            return responseStatus;
        }else{
            return StatusCommand.INTERNAL_ERROR;
        }
    }

    public StatusCommand addReview(ObjectOutputStream out, ObjectInputStream in,int reviewId, int playerId, int gameId, int rating, String note) throws Exception{
        out.writeObject(Commands.ADD_REVIEW);
        out.flush();
        out.writeObject(new AddReviewDTO(reviewId, playerId, gameId, rating, note));
        out.flush();
        Object response = in.readObject();
        if(response instanceof StatusCommand responseStatus){
            return responseStatus;
        }else{
            return StatusCommand.INTERNAL_ERROR;
        }
    }

    public StatusCommand registration(ObjectOutputStream out, ObjectInputStream in, int playerId, String username, String email, String password) throws Exception{
        out.writeObject(Commands.REGISTRATION);
        out.flush();
        out.writeObject(new RegistrationDTO(playerId, username, email, password));
        out.flush();
        Object response = in.readObject();
        if(response instanceof StatusCommand responseStatus){
            return responseStatus;
        }else{
            return StatusCommand.INTERNAL_ERROR;
        }
    }

    public void start(){
        try(Socket socket = new Socket(InetAddress.getLocalHost(),8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

            System.out.println("Connection established!");

            //StatusCommand registrationResult = registration(out, in, 1, "Max Mustermann", "max@email.com","testpassword1");
            //System.out.println("Registration: " + registrationResult);

            StatusCommand loginResult = login(out, in, "Max Mustermann", "testpassword1");
            System.out.println("Login: " + loginResult);

            int testGameId = 1234;
            StatusCommand deletedGame = deleteGame(out, in, testGameId);
            if(deletedGame == StatusCommand.GAME_DELETED){
                System.out.println("Game " + testGameId + " successfully deleted!");
            }else{
                System.out.println("Could not delete game: " + deletedGame);
            }

            List<Game> games = getGames(out, in);
            if(games != null){
                for(Game game : games){
                    System.out.println(game.toString());
                }
            }

            out.writeObject(Commands.BYE);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Dient nur zum testen, kommt spaeter noch in Main.java
    public static void main(String[] args){
        new GameClient().start();
    }
}
