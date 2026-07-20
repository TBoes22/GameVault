package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Game;
import java.util.List;

public class ViewGameCollection extends BorderPane{
    private static final String background_color1 = "#1e1e1e";
    private static final String background_color2 = "#2a3f5f";
    private static final String Background_color3 = "#1e3a52";
    private static final String primary_text = "#c6d4df";
    private static final String accent_color = "#66c0f4";
    private static final String button_color = "#4d8ec4";
    private static final String caution = "#e74c3c";

    private Button gamesButton;
    private Button wishlistButton;
    private Button reviewsButton;
    private Button logoutButton;
    private Button addGameButton;
    private ListView<HBox> viewGameCollection;
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

        Label titleLabel = new Label("Game Collection");
        titleLabel.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + accent_color + ";");

        navbar.getChildren().addAll(gamesButton, wishlistButton, reviewsButton, spacer, titleLabel);

        setTop(navbar);

        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(20));

        Label listTitle = new Label("My Game Collection");
        listTitle.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + primary_text + ";");

        viewGameCollection = new ListView<>();
        viewGameCollection.setStyle("-fx-background-color: " + background_color1 + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8;");

        VBox.setVgrow(viewGameCollection, Priority.ALWAYS);

        centerBox.getChildren().addAll(listTitle, viewGameCollection);
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

    public void displayGamesList(List<Game> games){
        viewGameCollection.getItems().clear();
        for(Game game: games){
            HBox row = new HBox(10);
            row.setPadding(new Insets(8, 12, 8, 12));
            row.setAlignment(Pos.CENTER_LEFT);
            row.setStyle("-fx-background-color: " + Background_color3 + ";" + "-fx-font-size: 6;");

            Label titleLabel = new Label(game.getTitle());
            titleLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;");
            HBox.setHgrow(titleLabel, Priority.ALWAYS);

            Label platformLabel = new Label(game.getPlatform().toString());
            platformLabel.setStyle("-fx-text-fill: " + accent_color + ";" + "-fx-font-size: 12px;" + "-fx-background-color: #1b2838;" + "-fx-padding: 2px 8px;" + "-fx-background-radius: 4;");

            Label statusLabel = new Label(game.getStatus().toString());
            statusLabel.setStyle("-fx-text-fill: white;" + "-fx-font-size: 12px;" + "-fx-background-color: " + getStatusColor(game) + ";" + "-fx-padding: 2px 8px;" + "-fx-background-radius: 4;");

            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: " + caution + ";" + "-fx-cursor: hand;" + "-fx-font-size: 14px;");
            deleteButton.setUserData(game.getGameId());

            row.getChildren().addAll(titleLabel, platformLabel, statusLabel, deleteButton);
            viewGameCollection.getItems().add(row);

        }
    }

    private String getStatusColor(Game game){
        return switch(game.getStatus()){
            case COMPLETED -> "#2ecc71";
            case OWNED -> "#3498db";
            case WISHLIST -> "#f39c12";
            case SOLD -> "#e74c3c";
        };
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
    public Button getWishlistButton() {
        return wishlistButton;
    }
    public Button getReviewsButton() {
        return reviewsButton;
    }
    public Button getAddGameButton() {
        return addGameButton;
    }
    public Button getLogoutButton() {
        return logoutButton;
    }
    public ListView<HBox> getViewGameCollection() {
        return viewGameCollection;
    }

    public ViewGameCollection(){
        generalUI();
    }
}
