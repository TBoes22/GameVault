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
                if(!(message instanceof Commands command)) {
                    out.writeObject(StatusCommand.INVALID_ARGUMENT);
                    continue;
                }
                if(command == Commands.BYE){
                    out.writeObject(StatusCommand.BYE);
                    break;

                }else if(command == Commands.GET_GAMES){
                    List<Game> games = gameHandler.showFullCollection();
                    if(games.isEmpty()){
                        out.writeObject(StatusCommand.NOT_FOUND);
                    }else{
                        out.writeObject(games);
                    }

                }else if(command == Commands.LOGIN){
                    Object data = in.readObject();
                    if(data instanceof LoginDTO dto){
                        boolean success = loginHandler.login(dto.getUsername(), dto.getPassword());
                        if(success){
                            out.writeObject(StatusCommand.LOGIN_SUCCESS);
                        }else{
                            out.writeObject(StatusCommand.INVALID_CREDENTIALS);
                        }
                    }
                }else if(command == Commands.ADD_GAME){
                    Object data = in.readObject();
                    if(data instanceof AddGameDTO dto){
                        Game game = new Game(dto.getGameId(), dto.getTitle(), dto.getDeveloper(), dto.getReleaseDate(), dto.getGenre(), dto.getPlatform(), dto.getPrice(), dto.getStatus());
                        gameHandler.saveGame(game);
                        out.writeObject(StatusCommand.GAME_ADDED);
                    }
                }else if(command == Commands.DELETE_GAME){
                    Object data = in.readObject();
                    if(data instanceof Integer gameId){
                        boolean deleted = gameHandler.deleteGame(gameId);
                        if(deleted){
                            out.writeObject(StatusCommand.GAME_DELETED);
                        }else {
                            out.writeObject(StatusCommand.NOT_FOUND);
                        }
                    }else{
                        out.writeObject(StatusCommand.NOT_FOUND);
                    }
                }else if(command == Commands.GET_WISHLIST){
                    Object data = in.readObject();
                    if(data instanceof Integer playerId){
                        List<Game> wishlist = wishlistHandler.showPlayerWishlist(playerId);
                        if(wishlist.isEmpty()){
                            out.writeObject(StatusCommand.NOT_FOUND);
                        }else{
                            out.writeObject(wishlist);
                        }
                    }
                }else if(command == Commands.ADD_TO_WISHLIST){
                    Object data = in.readObject();
                    if(data instanceof AddGameDTO dto){
                        Object playerData = in.readObject();
                        if(playerData instanceof Integer playerId){
                            Game game = new Game(dto.getGameId(), dto.getTitle(), dto.getDeveloper(), dto.getReleaseDate(), dto.getGenre(), dto.getPlatform(), dto.getPrice(), dto.getStatus());
                            wishlistHandler.addGameToWishlist(playerId, game);
                            out.writeObject(StatusCommand.GAME_ADDED_TO_WISHLIST);
                        }
                    }
                }else if(command == Commands.ADD_REVIEW){
                    Object data = in.readObject();
                    if(data instanceof AddReviewDTO dto){
                        Review review = new Review(dto.getReviewId(), dto.getPlayerId(), dto.getGameId(), dto.getRating(), dto.getNote());
                        reviewHandler.saveReview(review, dto.getPlayerId(), dto.getGameId());
                        out.writeObject(StatusCommand.REVIEW_ADDED);
                    }
                }else if(command == Commands.REGISTRATION){
                    Object data = in.readObject();
                    if(data instanceof RegistrationDTO dto){
                        try{
                            loginHandler.registration(dto.getPlayerId(), dto.getUsername(), dto.getEmail(), dto.getPassword());
                            out.writeObject(StatusCommand.REGISTRATION_SUCCESS);
                        }catch (Exception e){
                            out.writeObject(StatusCommand.INTERNAL_ERROR);
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
