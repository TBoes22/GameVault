package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Game;

import java.util.List;

public class GameListView extends BorderPane {

    // ─── Farben ───────────────────────────────────────────────
    private static final String BG_DARK =    "#1b2838";
    private static final String BG_CARD =    "#2a3f5f";
    private static final String BG_ROW =     "#1e3a52";
    private static final String TEXT =        "#c6d4df";
    private static final String ACCENT =      "#66c0f4";
    private static final String BUTTON_COL =  "#4d8ec4";
    private static final String DANGER =      "#e74c3c";

    // ─── UI Elemente — vom Controller erreichbar ──────────────
    private Button gamesButton;      // Navbar: zur Spieleliste
    private Button wishlistButton;   // Navbar: zur Wishlist
    private Button reviewsButton;    // Navbar: zu Reviews
    private Button logoutButton;     // Unten rechts: Logout
    private Button addGameButton;    // Unten rechts: Spiel hinzufügen
    private ListView<HBox> gameListView; // Die eigentliche Spieleliste
    private Label statusLabel;       // Statusmeldungen unten links

    public GameListView(){
        setupUI(); // UI beim Erstellen aufbauen
    }

    private void setupUI(){

        // Gesamter Hintergrund dunkel
        setStyle("-fx-background-color: " + BG_DARK + ";");

        // ─── TOP: Navigationsleiste ───────────────────────────
        // HBox = Elemente nebeneinander
        HBox navbar = new HBox(10);                       //10 ist der Abstand zwischen den Elementen in der Horizontal Box
        navbar.setPadding(new Insets(12, 20, 12, 20));    // Padding ist der Innenabstand -- der Abstand zwischen dem Rand eines Elements und seinem Inhalt
        navbar.setAlignment(Pos.CENTER_LEFT);
        navbar.setStyle("-fx-background-color: " + BG_CARD + ";");

        // Navbar Buttons erstellen
        gamesButton = createNavButton("🎮 Games");
        wishlistButton = createNavButton("⭐ Wishlist");
        reviewsButton = createNavButton("📝 Reviews");

        // Leeraum zwischen linken Buttons und rechtem Bereich
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // ← wächst so weit wie möglich

        // Titel rechts in der Navbar
        Label titleLabel = new Label("GameVault");
        titleLabel.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + ACCENT + ";"
        );

        navbar.getChildren().addAll(
            gamesButton, wishlistButton, reviewsButton,
            spacer, titleLabel
        );

        // Navbar oben platzieren (BorderPane TOP)
        setTop(navbar);

        // ─── CENTER: Spieleliste ──────────────────────────────
        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(20));

        // Überschrift
        Label listTitle = new Label("My Game Collection");
        listTitle.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + TEXT + ";"
        );

        // ListView — zeigt die Spiele an
        // HBox als Typ weil jede Zeile mehrere Elemente hat
        gameListView = new ListView<>();
        gameListView.setStyle(
            "-fx-background-color: " + BG_DARK + ";" +
            "-fx-border-color: #3d5a73;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );
        // Liste wächst mit dem Fenster
        VBox.setVgrow(gameListView, Priority.ALWAYS);

        centerBox.getChildren().addAll(listTitle, gameListView);
        setCenter(centerBox);

        // ─── BOTTOM: Status + Buttons ─────────────────────────
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding(new Insets(12, 20, 12, 20));
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.setStyle("-fx-background-color: " + BG_CARD + ";");

        // Statusmeldungen links unten
        statusLabel = new Label("");
        statusLabel.setStyle("-fx-text-fill: " + TEXT + "; -fx-font-size: 12px;");
        statusLabel.setVisible(false);

        // Leeraum zwischen Status und Buttons
        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        // Add Game Button
        addGameButton = new Button("+ Add Game");
        addGameButton.setStyle(
            "-fx-background-color: " + BUTTON_COL + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 8px 16px;" +
            "-fx-cursor: hand;"
        );

        // Logout Button
        logoutButton = new Button("Logout");
        logoutButton.setStyle(
            "-fx-background-color: " + DANGER + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 8px 16px;" +
            "-fx-cursor: hand;"
        );

        bottomBar.getChildren().addAll(
            statusLabel, bottomSpacer, addGameButton, logoutButton
        );
        setBottom(bottomBar);
    }

    // ─── Hilfsmethode: Navbar Button erstellen ────────────────
    // Damit wir nicht denselben Style dreimal schreiben
    private Button createNavButton(String text){
        Button btn = new Button(text);
        btn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: " + TEXT + ";" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 6px 12px;" +
            "-fx-background-radius: 6;"
        );
        // Hover Effekt
        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: " + BG_DARK + ";" +
            "-fx-text-fill: " + ACCENT + ";" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 6px 12px;" +
            "-fx-background-radius: 6;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: " + TEXT + ";" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 6px 12px;" +
            "-fx-background-radius: 6;"
        ));
        return btn;
    }

    // ─── Spieleliste befüllen ─────────────────────────────────
    // Wird vom Controller aufgerufen wenn Spiele geladen wurden
    public void displayGames(List<Game> games){
        gameListView.getItems().clear(); // ← alte Liste leeren

        for(Game game : games){
            // Jede Zeile ist eine HBox
            HBox row = new HBox(10);
            row.setPadding(new Insets(8, 12, 8, 12));
            row.setAlignment(Pos.CENTER_LEFT);
            row.setStyle("-fx-background-color: " + BG_ROW + "; -fx-background-radius: 6;");

            // Titel — nimmt so viel Platz wie möglich
            Label titleLabel = new Label(game.getTitle());
            titleLabel.setStyle("-fx-text-fill: " + TEXT + "; -fx-font-size: 13px;");
            HBox.setHgrow(titleLabel, Priority.ALWAYS);

            // Plattform
            Label platformLabel = new Label(game.getPlatform().toString());
            platformLabel.setStyle(
                "-fx-text-fill: " + ACCENT + ";" +
                "-fx-font-size: 12px;" +
                "-fx-background-color: #1b2838;" +
                "-fx-padding: 2px 8px;" +
                "-fx-background-radius: 4;"
            );

            // Status
            Label statusLabel = new Label(game.getStatus().toString());
            statusLabel.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-background-color: " + getStatusColor(game) + ";" +
                "-fx-padding: 2px 8px;" +
                "-fx-background-radius: 4;"
            );

            // Löschen Button
            Button deleteButton = new Button("🗑");
            deleteButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + DANGER + ";" +
                "-fx-cursor: hand;" +
                "-fx-font-size: 14px;"
            );
            // gameId für den Controller merken
            deleteButton.setUserData(game.getGameId());

            row.getChildren().addAll(titleLabel, platformLabel, statusLabel, deleteButton);
            gameListView.getItems().add(row);
        }
    }

    // ─── Statusfarbe je nach Spielstatus ─────────────────────
    private String getStatusColor(Game game){
        return switch(game.getStatus()){
            case COMPLETED -> "#2ecc71"; // grün
            case OWNED ->     "#3498db"; // blau
            case WISHLIST ->  "#f39c12"; // orange
            case SOLD ->      "#e74c3c"; // rot
        };
    }

    // ─── Status Methoden ──────────────────────────────────────
    public void showStatus(String message, boolean isError){
        statusLabel.setText(message);
        statusLabel.setStyle(
            "-fx-text-fill: " + (isError ? DANGER : "#2ecc71") + ";" +
            "-fx-font-size: 12px;"
        );
        statusLabel.setVisible(true);
    }

    // ─── Getter für Controller ────────────────────────────────
    public Button getGamesButton(){ return gamesButton; }
    public Button getWishlistButton(){ return wishlistButton; }
    public Button getReviewsButton(){ return reviewsButton; }
    public Button getAddGameButton(){ return addGameButton; }
    public Button getLogoutButton(){ return logoutButton; }
    public ListView<HBox> getGameListView(){ return gameListView; }
}