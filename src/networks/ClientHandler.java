package networks;
import database.GameHandler;
import database.PlayerHandler;
import database.ReviewHandler;
import database.WishlistHandler;
import models.*;
import networks.dto.AddGameDTO;
import networks.dto.AddReviewDTO;
import networks.dto.LoginDTO;
import networks.dto.RegistrationDTO;
import security.LoginHandler;

import java.io.*;
import java.net.Socket;
import java.util.List;



public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final GameHandler gameHandler;
    private final PlayerHandler playerHandler;
    private final LoginHandler loginHandler;
    private final WishlistHandler wishlistHandler;
    private final ReviewHandler reviewHandler;

    public ClientHandler(Socket clientSocket, GameHandler gameHandler, PlayerHandler playerHandler, LoginHandler loginHandler, WishlistHandler wishlistHandler, ReviewHandler reviewHandler) {
        this.clientSocket = clientSocket;
        this.gameHandler = gameHandler;
        this.playerHandler = playerHandler;
        this.loginHandler = loginHandler;
        this.wishlistHandler = wishlistHandler;
        this.reviewHandler = reviewHandler;



    }

    @Override
    public void run() {
        try(ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())){
            Object message;

            while((message = in.readObject()) != null){
                System.out.println("Received :" + message);
                if(!(message instanceof Commands command)) {
                    out.writeObject(StatusCommand.INVALID_ARGUMENT);
                    out.flush();
                    continue;
                }
                if(command == Commands.BYE){
                    out.writeObject(StatusCommand.BYE);
                    out.flush();
                    break;

                }else if(command == Commands.GET_GAMES){
                    Object data = in.readObject();
                    if(data instanceof Integer playerId){
                        List<Game> games = gameHandler.showPlayerCollection(playerId);
                        if(games.isEmpty()){
                            out.writeObject(StatusCommand.NOT_FOUND);
                            out.flush();
                        }else{
                            out.writeObject(games);
                            out.flush();
                        }
                    }

                }else if(command == Commands.LOGIN){
                    Object data = in.readObject();
                    if(data instanceof LoginDTO dto){
                        int playerId = loginHandler.login(dto.getUsername(), dto.getPassword());
                        if(playerId != -1){
                            out.writeObject(playerId);
                            out.flush();
                        }else{
                            out.writeObject(StatusCommand.INVALID_CREDENTIALS);
                            out.flush();
                        }
                    }
                }else if(command == Commands.ADD_GAME){
                    Object data = in.readObject();
                    if(data instanceof AddGameDTO dto){
                        Object playerData = in.readObject();
                        if(playerData instanceof Integer playerId) {
                            Game game = new Game(dto.getGameId(), dto.getTitle(), dto.getDeveloper(), dto.getReleaseDate(), dto.getGenre(), dto.getPlatform(), dto.getPrice(), dto.getStatus());
                            gameHandler.saveGame(game, playerId);
                            out.writeObject(StatusCommand.GAME_ADDED);
                            out.flush();
                        }
                    }
                }else if(command == Commands.DELETE_GAME){
                    Object data = in.readObject();
                    if(data instanceof Integer gameId){
                        boolean deleted = gameHandler.deleteGame(gameId);
                        if(deleted){
                            out.writeObject(StatusCommand.GAME_DELETED);
                            out.flush();
                        }else {
                            out.writeObject(StatusCommand.NOT_FOUND);
                            out.flush();
                        }
                    }else{
                        out.writeObject(StatusCommand.NOT_FOUND);
                        out.flush();
                    }
                }else if(command == Commands.GET_WISHLIST){
                    Object data = in.readObject();
                    if(data instanceof Integer playerId){
                        List<Game> wishlist = wishlistHandler.showPlayerWishlist(playerId);
                        if(wishlist.isEmpty()){
                            out.writeObject(StatusCommand.NOT_FOUND);
                            out.flush();
                        }else{
                            out.writeObject(wishlist);
                            out.flush();
                        }
                    }
                }else if(command == Commands.ADD_TO_WISHLIST) {
                    Object data = in.readObject();
                    if (data instanceof AddGameDTO dto) {
                        Object playerData = in.readObject();
                        if (playerData instanceof Integer playerId) {
                            Game game = new Game(dto.getGameId(), dto.getTitle(), dto.getDeveloper(), dto.getReleaseDate(), dto.getGenre(), dto.getPlatform(), dto.getPrice(), dto.getStatus());
                            wishlistHandler.addGameToWishlist(playerId, game);
                            out.writeObject(StatusCommand.GAME_ADDED_TO_WISHLIST);
                            out.flush();
                        }
                    }
                }else if(command == Commands.REMOVE_FROM_WISHLIST) {
                    Object data = in.readObject();
                    if (data instanceof Integer gameId) {
                        boolean removed = wishlistHandler.removeGameFromWishlist(gameId);
                        if (removed) {
                            out.writeObject(StatusCommand.GAME_REMOVED_FROM_WISHLIST);
                            out.flush();
                        } else {
                            out.writeObject(StatusCommand.NOT_FOUND);
                            out.flush();
                        }
                    }
                }else if(command == Commands.GET_REVIEWS){
                    System.out.println("4. server received get reviews");
                    Object data = in.readObject();
                    if(data instanceof String title){
                        List<Review> reviews = reviewHandler.findGameReview(title);
                        if(reviews.isEmpty()){
                            out.writeObject(StatusCommand.NOT_FOUND);
                            out.flush();
                        }else{
                            out.writeObject(reviews);
                            out.flush();
                        }
                    }
                }else if(command == Commands.ADD_REVIEW) {
                    Object data = in.readObject();
                    if (data instanceof AddReviewDTO dto) {
                        Review review = new Review(dto.getReviewId(), dto.getPlayerId(), dto.getGameTitle(), dto.getRating(), dto.getNote());
                        reviewHandler.saveReview(review, dto.getPlayerId());
                        out.writeObject(StatusCommand.REVIEW_ADDED);
                        out.flush();
                    }
                }else if(command == Commands.DELETE_REVIEW){
                    Object data = in.readObject();
                    if(data instanceof Integer reviewId){
                        boolean deleted = reviewHandler.deleteReview(reviewId);
                        if(deleted){
                            out.writeObject(StatusCommand.REVIEW_DELETED);
                            out.flush();
                        }else{
                            out.writeObject(StatusCommand.NOT_FOUND);
                            out.flush();
                        }
                    }
                }else if(command == Commands.REGISTRATION){
                    Object data = in.readObject();
                    if(data instanceof RegistrationDTO dto){
                        try{
                            loginHandler.registration(dto.getPlayerId(), dto.getUsername(), dto.getEmail(), dto.getPassword());
                            out.writeObject(StatusCommand.REGISTRATION_SUCCESS);
                            out.flush();
                        }catch (Exception e){
                            out.writeObject(StatusCommand.INTERNAL_ERROR);
                            out.flush();
                        }
                    }
                }else if(command == Commands.UPDATE_WISHLIST){
                    Object data = in.readObject();
                    if(data instanceof Integer gameId){
                        Object playerData = in.readObject();
                        Object priceData = in.readObject();
                        if(playerData instanceof Integer playerId && priceData instanceof Double newPrice){
                            wishlistHandler.updatePrice(playerId, gameId, newPrice);
                            out.writeObject(StatusCommand.WISHLIST_UPDATED);
                            out.flush();
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                clientSocket.close();
                System.out.println("Client disconnected!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
