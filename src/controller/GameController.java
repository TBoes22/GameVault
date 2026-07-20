package controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import model.ClientModel;
import models.*;
import networks.GameClient;
import networks.StatusCommand;
import view.*;
import view.ViewWishlist;
import javafx.scene.control.Button;
import java.time.LocalDate;
import java.util.List;


public class GameController {

    private final ClientModel model;
    private final GameClient gameClient;
    private final Stage stage;

    private ViewLogin viewLogin;
    private ViewGameCollection viewGameCollection;
    private ViewWishlist viewWishlist;
    private ViewReviews viewReviews;

    public GameController(ClientModel model, Stage stage){
        this.model = model;
        this.stage = stage;
        this.gameClient = new GameClient();
    }

    public void initializeViewLogin(ViewLogin viewLogin){
        this.viewLogin = viewLogin;

        viewLogin.getLoginButton().setOnAction(event -> {
            String username = viewLogin.getUsername();
            String password = viewLogin.getPassword();

            if(username.isEmpty() || password.isEmpty()){
                viewLogin.errorMessage("Please enter username and password!");
                return;
            }
            new Thread(()-> {
                try{
                    boolean connected = model.connect();
                    if(!connected){
                        Platform.runLater(()->
                                viewLogin.errorMessage("Could not connect to Server!"));
                        return;
                    }
                    int playerId = gameClient.login(model.getOut(), model.getIn(), username, password);

                    if(playerId != -1){
                        model.setPlayerId(playerId);
                        model.setLoggedIn(true);

                        Platform.runLater(()-> {
                            viewLogin.clearMessages();
                            showGameCollection();
                        });
                    }else{
                        Platform.runLater(()-> {
                            viewLogin.errorMessage("Login failed! Wrong username or password!");
                        });
                        model.disconnect();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Platform.runLater(()->
                            viewLogin.errorMessage("An error occurred: " + e.getMessage()));
                }
            }).start();
        });

        viewLogin.getRegisterButton().setOnAction(e -> {
            ViewRegister viewRegister = new ViewRegister();
            initializeViewRegister(viewRegister);
            stage.getScene().setRoot(viewRegister);
        });
    }

    private void initializeViewRegister(ViewRegister viewRegister) {
        viewRegister.getCancelButton().setOnAction(e -> {
            stage.getScene().setRoot(viewLogin);
        });

        viewRegister.getRegisterButton().setOnAction(e -> {
            String username = viewRegister.getUsername();
            String email = viewRegister.getEmail();
            String password = viewRegister.getPassword();
            String confirmPassword = viewRegister.getConfirmPassword();

            if(username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                viewRegister.errorMessage("Registration failed! Please fill in all fields.");
                return;
            }

            if(!password.equals(confirmPassword)){
                viewRegister.errorMessage("Passwords do not match!");
                return;
            }

            new Thread(()-> {
                try{
                    boolean connected = model.connect();
                    if(!connected){
                        Platform.runLater(()->
                                viewRegister.errorMessage("Could not connect to Server!"));
                        return;
                    }
                    int playerId = generateId();

                    StatusCommand result = gameClient.registration(model.getOut(), model.getIn(), playerId, username, email, password);

                    Platform.runLater(()->{
                        if(result == StatusCommand.REGISTRATION_SUCCESS){
                            viewLogin.successMessage("Registration successfull!");
                            viewRegister.clearMessage();
                            stage.getScene().setRoot(viewLogin);
                        }else{
                            viewRegister.errorMessage("Registration failed! Please try again.");
                        }
                    });
                }catch (Exception f){
                    Platform.runLater(()->
                            viewRegister.errorMessage("Error: " + f.getMessage()));
                }finally{
                    model.disconnect();
                }
            }).start();
        });
    }

    private void showGameCollection() {
        viewGameCollection = new ViewGameCollection();
        initializeViewGameCollection();
        stage.getScene().setRoot(viewGameCollection);
        loadGames();
    }

    private void showWishlist(){
        viewWishlist = new ViewWishlist();
        initializeViewWishlist();
        stage.getScene().setRoot(viewWishlist);
        loadWishlist();
    }

    private void loadWishlist(){
        new Thread(()-> {
            try{
                List<Game> wishlist = gameClient.getWishlist(model.getOut(), model.getIn(), model.getPlayerId());
                Platform.runLater(()-> {
                    if(wishlist != null && !wishlist.isEmpty()){
                        viewWishlist.displayWishlist(wishlist);
                        setupWishlistButtons();
                    }else{
                        viewWishlist.showStatus("Wishlist is empty!", false);
                    }
                });
            }catch (Exception e){
                Platform.runLater(()-> {
                    viewWishlist.showStatus("Error loading Wishlist!", true);
                });
            }
        }).start();
    }

    private void initializeViewWishlist(){
        viewWishlist.getGamesButton().setOnAction(e -> {
            showGameCollection();
        });
        viewWishlist.getLogoutButton().setOnAction(e -> {
            model.setLoggedIn(false);
            model.disconnect();
            stage.getScene().setRoot(viewLogin);
        });

        viewWishlist.getReviewsButton().setOnAction(e -> {
            showReviews();
        });

        viewWishlist.getAddGameButton().setOnAction(actionEvent -> {
            ViewAddGame addViewGame = new ViewAddGame();
            stage.getScene().setRoot(addViewGame);
            addViewGame.getCancelButton().setOnAction(e-> {
                stage.getScene().setRoot(viewWishlist);
            });
            addViewGame.getConfirmButton().setOnAction(e-> {
                String title = addViewGame.getTitle();
                String platform = addViewGame.getPlatform();
                String genre = addViewGame.getGenre();
                String developer = addViewGame.getDeveloper();
                double price = Double.parseDouble(addViewGame.getPrice());
                Status status = addViewGame.getStatus();

                new Thread(()-> {
                    try{
                        StatusCommand result = gameClient.addToWishlist(model.getOut(), model.getIn(), model.getPlayerId(), generateId(), title, new Developer(developer), LocalDate.now(), new Genre(genre), new models.Platform(platform), price, status);
                        Platform.runLater(()-> {
                            if(result == StatusCommand.GAME_ADDED_TO_WISHLIST){
                                stage.getScene().setRoot(viewWishlist);
                                loadWishlist();
                            }else{
                                viewWishlist.showStatus("Could not add Game!", true);
                            }
                        });
                    }catch (Exception f){
                        Platform.runLater(()-> {
                            viewWishlist.showStatus("Error adding game!", true);
                        });
                    }
                }).start();
            });
        });
    }

    private void removeFromWishlist(int gameId){
        System.out.println("Removing Game: " + gameId);
        new Thread(()-> {
            try{
                StatusCommand result = gameClient.removeFromWishlist(model.getOut(), model.getIn(), gameId);
                Platform.runLater(()-> {
                    if(result == StatusCommand.GAME_REMOVED_FROM_WISHLIST){
                        viewWishlist.showStatus("Game has been removed from the Wishlist!", false);
                        loadWishlist();
                    }else{
                        viewWishlist.showStatus("Game not found!", true);
                    }
                });
            }catch (Exception e){
                Platform.runLater(()-> {
                    viewWishlist.showStatus("Error while trying to remove Game!", true);
                });
            }
        }).start();
    }

    private void loadGames() {
        new Thread(()->{
            try{
                List<Game> games = gameClient.getGames(model.getOut(), model.getIn(), model.getPlayerId());
                Platform.runLater(()-> {
                    if(games != null && !games.isEmpty()){
                        viewGameCollection.displayGamesList(games);
                        setupDeleteButtons();
                    }else{
                        viewGameCollection.showStatus("No games found!", true);
                    }
                });
            }catch (Exception e){
                Platform.runLater(()->{
                    viewGameCollection.showStatus("Error loading games!", true);
                });
            }
        }).start();
    }

    private void initializeViewGameCollection() {

        viewGameCollection.getLogoutButton().setOnAction(actionEvent -> {
            model.setLoggedIn(false);
            model.disconnect();
            stage.getScene().setRoot(viewLogin);
        });

        viewGameCollection.getWishlistButton().setOnAction(e -> {
            showWishlist();
        });

        viewGameCollection.getReviewsButton().setOnAction(e -> {
            showReviews();
        });

        viewGameCollection.getAddGameButton().setOnAction(actionEvent -> {
            ViewAddGame viewAddGame = new ViewAddGame();
            stage.getScene().setRoot(viewAddGame);
            viewAddGame.getCancelButton().setOnAction(e -> {
                stage.getScene().setRoot(viewGameCollection);
            });
            viewAddGame.getConfirmButton().setOnAction(e -> {
                String title = viewAddGame.getTitle();
                String platform = viewAddGame.getPlatform();
                String genre = viewAddGame.getGenre();
                String developer = viewAddGame.getDeveloper();
                double price = Double.parseDouble(viewAddGame.getPrice());
                Status status = viewAddGame.getStatus();

                new Thread(()-> {
                    try{
                        StatusCommand result = gameClient.addGame(model.getOut(), model.getIn(), generateId(), title, new Developer(developer), LocalDate.now(), new Genre(genre), new models.Platform(platform), price, status, model.getPlayerId());
                        Platform.runLater(()-> {
                            stage.getScene().setRoot(viewGameCollection);
                            loadGames();
                        });
                    }catch (Exception f){
                        f.printStackTrace();
                    }
                }).start();
            });
        });
    }

    private void initializeViewReviews(){
          viewReviews.getGamesButton().setOnAction(e -> {
              showGameCollection();
          });

          viewReviews.getWishlistButton().setOnAction(e -> {
              showWishlist();
          });

          viewReviews.getAddReviewButton().setOnAction(actionEvent-> {
              ViewAddReview viewAddReview = new ViewAddReview();
              stage.getScene().setRoot(viewAddReview);
              viewAddReview.getCancelButton().setOnAction(e-> {
                  stage.getScene().setRoot(viewReviews);
              });
              viewAddReview.getConfirmButton().setOnAction(e-> {
                  String title = viewAddReview.getGameTitle();
                  if(title.isEmpty()){
                      viewReviews.showStatus("Please enter a game title!", true);
                      return;
                  }
                  Integer rating = Integer.parseInt(viewAddReview.getRating());
                  String note = viewAddReview.getNote();

                  new Thread(()-> {
                      try{
                          StatusCommand result = gameClient.addReview(model.getOut(), model.getIn(), generateId(), model.getPlayerId(), title, rating, note);
                          Platform.runLater(()-> {
                              if(result == StatusCommand.REVIEW_ADDED){
                                  stage.getScene().setRoot(viewReviews);
                                  viewReviews.showStatus("Review successfully added!", false);
                              }else{
                                  viewReviews.showStatus("Could not add review!", true);
                              }
                          });
                      }catch (Exception f){
                          f.printStackTrace();
                      }
                  }).start();
              });
          });

          viewReviews.getLogoutButton().setOnAction(e -> {
              model.setLoggedIn(false);
              model.disconnect();
              stage.getScene().setRoot(viewLogin);
          });

          viewReviews.getSearchButton().setOnAction(e-> {
              String title = viewReviews.getTitleField().getText();

              if(title.isEmpty()){
                  viewReviews.showStatus("Please enter a valid Game title: ", true);
                  return;
              }
              loadReviews(title);
          });
    }
    private void loadReviews(String title){
        new Thread(()-> {
            try{
                List<Review> reviews = gameClient.getReviews(model.getOut(), model.getIn(), title);
                Platform.runLater(()-> {
                    if(reviews != null && !reviews.isEmpty()){
                        viewReviews.displayReviews(reviews);
                        setupReviewButton();
                    }else{
                        viewReviews.showStatus("No reviews found!", true);
                    }
                });
            }catch (Exception e){
                Platform.runLater(()-> {
                    viewReviews.showStatus("Error loading Reviews!", true);
                });
            }
        }).start();
    }

    private void showReviews(){
        viewReviews = new ViewReviews();
        initializeViewReviews();
        stage.getScene().setRoot(viewReviews);
    }

    private int generateId(){
        return (int)(Math.random() * 1000000);
    }

    private void setupWishlistButtons(){
        viewWishlist.getViewWishlist().getItems().forEach(row ->{
            row.getChildren().forEach(node -> {
                if(node instanceof Button btn){
                    if(btn.getUserData() instanceof Integer gameId){
                        btn.setOnAction(e -> removeFromWishlist(gameId));
                    }
                }
            });
        });
    }
    private void setupReviewButton(){
        viewReviews.getViewReviews().getItems().forEach(row -> {
            row.getChildren().forEach(node -> {
                if(node instanceof javafx.scene.control.Button btn){
                    if(btn.getUserData() instanceof  Integer reviewId){
                        btn.setOnAction(e -> deleteReview(reviewId));
                    }
                }
            });
        });
    }

    private void deleteReview(int reviewId){
            new Thread(()-> {
                try{
                    StatusCommand result = gameClient.deleteReview(model.getOut(), model.getIn(), reviewId);
                    Platform.runLater(()-> {
                        if(result == StatusCommand.REVIEW_DELETED){
                            viewReviews.showStatus("Review successfully deleted!", false);
                        }else{
                            viewReviews.showStatus("Review not found!", true);
                        }
                    });
                }catch (Exception e){
                    Platform.runLater(()-> {
                        viewReviews.showStatus("Error deleting Review!", true);
                    });
                }
            }).start();
    }

    private void setupDeleteButtons(){
        viewGameCollection.getViewGameCollection().getItems().forEach(row -> {
            row.getChildren().forEach(node -> {
                if(node instanceof javafx.scene.control.Button btn){
                    if(btn.getUserData() instanceof Integer gameId){
                        btn.setOnAction(event -> deleteGame(gameId));
                    }
                }
            });
        });
    }

    private void deleteGame(int gameId){
        new Thread(()-> {
            try{
                StatusCommand result = gameClient.deleteGame(model.getOut(), model.getIn(), gameId);
                Platform.runLater(()-> {
                    if(result == StatusCommand.GAME_DELETED){
                        viewGameCollection.showStatus("Game successfully deleted!", false);
                        loadGames();
                    }else{
                        viewGameCollection.showStatus("Game not found!", true);
                    }
                });
            }catch (Exception e){
                Platform.runLater(()-> {
                    viewGameCollection.showStatus("Error deleting game!", true);
                });
            }
        }).start();
    }
}
