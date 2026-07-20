package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Game;


import java.util.List;


public class ViewWishlist extends BorderPane{

    private static final String background_color1 = "#1e1e1e";
    private static final String background_color2 = "#2a3f5f";
    private static final String primary_text = "#c6d4df";
    private static final String accent_color = "#66c0f4";
    private static final String button_color = "#4d8ec4";
    private static final String caution = "#e74c3c";

    private Button gamesButton;
    private Button wishlistButton;
    private Button reviewsButton;
    private Button logoutButton;
    private Button addGameButton;
    private ListView<HBox> viewWishlist;
    private Label statusLabel;

    private Button createNavButton(String text){
        Button navButton1 = new Button(text);
        navButton1.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-cursor: hand;" + "-fx-padding: 6px 12px;" + "-fx-background-radius: 6;");
        navButton1.setOnMouseEntered(e -> navButton1.setStyle("-fx-background-color: " + background_color1 + ";" + "-fx-text-fill: " + accent_color + ";" + "-fx-font-size: 13px;" + "-fx-cursor: hand;" + "-fx-padding: 6px 12px;" + "-fx-background-radius: 6"));
        navButton1.setOnMouseExited(e -> navButton1.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-cursor: hand;" + "-fx-padding: 6px 12px;" + "-fx-background-radius: 6"));
        return navButton1;
    }

    private void generalUI(){
        setStyle("-fx-background-color: " + background_color1 + ";");

        HBox navbar = new HBox(10);
        navbar.setPadding(new Insets(12, 20, 12, 20));
        navbar.setAlignment(Pos.CENTER_LEFT);
        navbar.setStyle("-fx-background-color: " + background_color2 + ";");

        gamesButton = createNavButton("Games");
        wishlistButton = createNavButton("Wishlist");
        reviewsButton = createNavButton("Reviews");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label titleLabel = new Label("Wishlist");
        titleLabel.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + accent_color + ";");

        navbar.getChildren().addAll(gamesButton, wishlistButton, reviewsButton, spacer, titleLabel);

        setTop(navbar);

        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(20));

        Label listTitle = new Label("My Wishlist");
        listTitle.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + primary_text + ";");

        viewWishlist = new ListView<>();
        viewWishlist.setStyle("-fx-background-color: " + background_color1 + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8;");

        VBox.setVgrow(viewWishlist, Priority.ALWAYS);

        centerBox.getChildren().addAll(listTitle, viewWishlist);
        setCenter(centerBox);

        HBox bottomBar = new HBox(10);
        bottomBar.setPadding(new Insets(12, 20, 12, 20));
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.setStyle("-fx-background-color: " + background_color2 + ";");

        statusLabel = new Label("");
        statusLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 12px;");
        statusLabel.setVisible(false);

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        addGameButton = new Button(" + Add Game");
        addGameButton.setStyle("-fx-background-color: " + button_color + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 8px 16px;" + "-fx-cursor: hand;");

        logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: " + caution + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 8px 16px;" + "-fx-cursor: hand;");
        bottomBar.getChildren().addAll(statusLabel, bottomSpacer, addGameButton, logoutButton);
        setBottom(bottomBar);

    }

    public void displayWishlist(List<Game> wishlist){
        viewWishlist.getItems().clear();

        for(Game game:wishlist){
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            Label title = new Label(game.getTitle());
            Label platform = new Label(game.getPlatform().toString());
            Button removeButton = new Button("Remove");
            removeButton.setUserData(game.getGameId());
            row.getChildren().addAll(title, platform, removeButton);
            viewWishlist.getItems().add(row);
        }
    }

    public void showStatus(String message, boolean isError){
        statusLabel.setText(message);
        if(isError){
            statusLabel.setStyle("-fx-text-fill: " + caution + ";" + "-fx-font-size: 12px;");
        }else{
            statusLabel.setStyle("-fx-text-fill: #2ecc71;" + "-fx-font-size: 12px;");
        }
        statusLabel.setVisible(true);
    }

    public Button getGamesButton(){
        return gamesButton;
    }
    public Button getWishlistButton(){
        return wishlistButton;
    }
    public Button getReviewsButton(){
        return reviewsButton;
    }
    public Button getAddGameButton(){
        return addGameButton;
    }
    public Button getLogoutButton(){
        return logoutButton;
    }
    public ListView<HBox> getViewWishlist(){
        return viewWishlist;
    }

    public ViewWishlist(){
        generalUI();
    }
}
