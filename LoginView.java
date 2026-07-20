package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class LoginView extends VBox {

    // Farben
    private static final String BG_DARK = "#1b2838";
    private static final String BG_CARD = "#2a3f5f";
    private static final String TEXT_PRIMARY = "#c6d4df";
    private static final String ACCENT = "#66c0f4";
    private static final String BUTTON_COLOR = "#4d8ec4";

    // UI Elemente — von Controller erreichbar
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Hyperlink registerLink;
    private Label statusLabel;

    public LoginView(){
        setupUI();
    }

    private void setupUI(){
        // Hauptcontainer
        setAlignment(Pos.CENTER);
        setSpacing(0);
        setPrefSize(900, 600);
        setStyle("-fx-background-color: " + BG_DARK + ";");

        // Karte in der Mitte
        VBox card = new VBox(16);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(40));
        card.setMaxWidth(380);
        card.setStyle(
            "-fx-background-color: " + BG_CARD + ";" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 20, 0, 0, 4);"
        );

        // Titel
        Label title = new Label("GameVault");
        title.setStyle(
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + ACCENT + ";"
        );

        Label subtitle = new Label("Your personal game collection");
        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: " + TEXT_PRIMARY + ";" +
            "-fx-opacity: 0.7;"
        );

        // Trennlinie
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: " + ACCENT + "; -fx-opacity: 0.3;");

        // Username Feld
        Label usernameLabel = new Label("Username");
        usernameLabel.setStyle("-fx-text-fill: " + TEXT_PRIMARY + "; -fx-font-size: 13px;");

        usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle(
            "-fx-background-color: #1b2838;" +
            "-fx-text-fill: " + TEXT_PRIMARY + ";" +
            "-fx-prompt-text-fill: #5a7a8a;" +
            "-fx-border-color: #3d5a73;" +
            "-fx-border-radius: 6;" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 10px;"
        );

        // Password Feld
        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-text-fill: " + TEXT_PRIMARY + "; -fx-font-size: 13px;");

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle(
            "-fx-background-color: #1b2838;" +
            "-fx-text-fill: " + TEXT_PRIMARY + ";" +
            "-fx-prompt-text-fill: #5a7a8a;" +
            "-fx-border-color: #3d5a73;" +
            "-fx-border-radius: 6;" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 10px;"
        );

        // Login Button
        loginButton = new Button("Sign in");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setStyle(
            "-fx-background-color: " + BUTTON_COLOR + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 10px;" +
            "-fx-cursor: hand;"
        );

        // Hover Effekt
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
            "-fx-background-color: " + ACCENT + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 10px;" +
            "-fx-cursor: hand;"
        ));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(
            "-fx-background-color: " + BUTTON_COLOR + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 10px;" +
            "-fx-cursor: hand;"
        ));

        // Register Link
        HBox registerBox = new HBox(4);
        registerBox.setAlignment(Pos.CENTER);
        Label registerText = new Label("Don't have an account?");
        registerText.setStyle("-fx-text-fill: " + TEXT_PRIMARY + "; -fx-opacity: 0.7; -fx-font-size: 12px;");
        registerLink = new Hyperlink("Create one here");
        registerLink.setStyle("-fx-text-fill: " + ACCENT + "; -fx-font-size: 12px; -fx-border-color: transparent;");
        registerBox.getChildren().addAll(registerText, registerLink);

        // Status Label für Fehlermeldungen
        statusLabel = new Label("");
        statusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 12px;");
        statusLabel.setVisible(false);

        // Alles zur Karte hinzufügen
        card.getChildren().addAll(
            title, subtitle, separator,
            usernameLabel, usernameField,
            passwordLabel, passwordField,
            loginButton, registerBox, statusLabel
        );

        getChildren().add(card);     //Jeder Javafx Container hat eine Liste von Kind-Elementen getChildren() gibt diese Liste zurueck -> card ist die VBox(Verticle Box) und ist Parent von title, subtitle,... 
    }

    // Getter für Controller
    public String getUsername(){ return usernameField.getText(); }
    public String getPassword(){ return passwordField.getText(); }
    public Button getLoginButton(){ return loginButton; }
    public Hyperlink getRegisterLink(){ return registerLink; }

    // Status Methoden
    public void showError(String message){
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 12px;");
        statusLabel.setVisible(true);
    }

    public void showSuccess(String message){
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-size: 12px;");
        statusLabel.setVisible(true);
    }

    public void clearFields(){
        usernameField.clear();
        passwordField.clear();
        statusLabel.setVisible(false);  // setzt die View wieder auf den Anfang. Wir brauchen hier setVisible, weil statusLabel durch showError oder showSuccess auf visible gesetzt wird
    }
