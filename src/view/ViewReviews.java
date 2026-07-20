package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Game;
import models.Review;


import java.util.List;


public class ViewReviews extends BorderPane{

    private static final String background_color1 = "#1e1e1e";
    private static final String background_color2 = "#2a3f5f";
    private static final String primary_text = "#c6d4df";
    private static final String accent_color = "#66c0f4";
    private static final String button_color = "#4d8ec4";
    private static final String caution = "#e74c3c";

    private TextField titleField;
    private Button searchButton;
    private Button gamesButton;
    private Button wishlistButton;
    private Button reviewsButton;
    private Button logoutButton;
    private Button addReviewButton;
    private ListView<HBox> viewReviews;
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

        Label titleLabel = new Label("Reviews");
        titleLabel.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + accent_color + ";");

        navbar.getChildren().addAll(gamesButton, wishlistButton, reviewsButton, spacer, titleLabel);

        setTop(navbar);

        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(20));

        Label listTitle = new Label("Game Reviews");
        listTitle.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + primary_text + ";");

        viewReviews = new ListView<>();
        viewReviews.setStyle("-fx-background-color: " + background_color1 + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8;");

        titleField = new TextField();
        titleField.setPromptText("Enter Game Title");

        searchButton = new Button("Search");
        HBox searchBox = new HBox(10, titleField, searchButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        VBox.setVgrow(viewReviews, Priority.ALWAYS);

        centerBox.getChildren().addAll(listTitle, searchBox, viewReviews);
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

        addReviewButton = new Button(" + Add Review");
        addReviewButton.setStyle("-fx-background-color: " + button_color + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 8px 16px;" + "-fx-cursor: hand;");

        logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: " + caution + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 8px 16px;" + "-fx-cursor: hand;");
        bottomBar.getChildren().addAll(statusLabel, bottomSpacer, addReviewButton, logoutButton);
        setBottom(bottomBar);

    }

    public void displayReviews(List<Review> reviews){
        viewReviews.getItems().clear();

        for(Review review:reviews){
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            Label rating = new Label("⭐ " + review.getRating() + "/10");
            Label note = new Label("📝" + review.getNote());
            Button deleteButton = new Button("Delete Review");

            deleteButton.setUserData(review.getReviewId());

            row.getChildren().addAll(rating, note, deleteButton);
            viewReviews.getItems().add(row);
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

    public Button getSearchButton(){
        return searchButton;
    }
    public TextField getTitleField(){
        return titleField;
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
    public Button getAddReviewButton(){
        return addReviewButton;
    }
    public Button getLogoutButton(){
        return logoutButton;
    }
    public ListView<HBox> getViewReviews(){
        return viewReviews;
    }

    public ViewReviews(){
        generalUI();
    }
}

