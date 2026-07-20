package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class ViewRegister extends VBox{

    private static final String background_color1 = "#1e1e1e";
    private static final String background_color2 = "#1e1e1e";
    private static final String primary_text = "#c6d4df";
    private static final String accent_color = "#66c0f4";
    private static final String button_color = "#4d8ec4";

    private TextField usernameField;
    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button registerButton;
    private Button cancelButton;
    private Label statusLabel;

    private void generalUI(){
        setAlignment(Pos.CENTER);
        setSpacing(0);
        setPrefSize(900, 600);
        setStyle("-fx-background-color: " + background_color1 + ";");

        VBox card = new VBox(16);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(40));
        card.setMaxWidth(380);
        card.setStyle("-fx-background-color: " + background_color2 + ";" + "-fx-background-radius: 12;");

        Label title = new Label("GameCollection");
        title.setStyle("-fx-font-size: 28px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + accent_color + ";");
        Label subtitle = new Label("Create a new Account");
        subtitle.setStyle("-fx-font-size: 13px;" + "-fx-text-fill: " + primary_text + ";" + "-fx-opacity: 0.7;");

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: " + accent_color + "; -fx-opacity: 0.3;");

        Label usernameLabel = new Label("Username");
        usernameLabel.setStyle("-fx-text-fill: " + primary_text + "; -fx-font-size: 13px");

        usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle("-fx-background-color: #1b2838;" + "-fx-text-fill: " + primary_text + ";" + "-fx-prompt-text-fill: #5a7a8a;" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 10px;");

        Label emailLabel = new Label("Email");
        emailLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13;");

        emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setStyle("-fx-background-color: #1b2838;" + "-fx-text-fill: " + primary_text + ";" + "-fx-prompt-text-fill: #5a7a8a;" + "-fx-border-color: #3d5a71;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 10px;");

        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px");

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-background-color: #1b2838;" + "-fx-text-fill: " + primary_text + ";" + "-fx-prompt-text-fill: #5a7a8a;" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-padding: 10px;");

        Label confirmPasswordLabel = new Label("Confirm Password");
        confirmPasswordLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;");

        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm your Password");
        confirmPasswordField.setStyle("-fx-background-color: #1b2838;" + "-fx-text-fill: " + primary_text + ";" + "-fx-prompt-text-fill: #5a7a8a;" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-padding: 10px;");

        registerButton = new Button("Register");
        registerButton.setAlignment(Pos.CENTER_RIGHT);
        registerButton.setStyle("-fx-background-color: " + button_color + ";" + "-fx-text-fill: white;" + "-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 10px;" + "-fx-cursor: hand;");

        registerButton.setOnMouseEntered(e -> registerButton.setStyle("-fx-background-color: " + accent_color + ";" + "-fx-text-fill: white;" + "-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-cursor: hand;"));
        registerButton.setOnMouseExited(e -> registerButton.setStyle("-fx-background-color: " + accent_color + ";" + "-fx-text-fill: white;" + "-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-cursor: hand;"));

        cancelButton = new Button("Cancel");
        cancelButton.setAlignment(Pos.CENTER_LEFT);
        cancelButton.setStyle("-fx-background-color: " + button_color + ";" + "-fx-text-fill: white;" + "-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 10px;" + "-fx-cursor: hand;");

        cancelButton.setOnMouseEntered(e -> cancelButton.setStyle("-fx-background-color: " + accent_color + ";" + "-fx-text-fill: white;" + "-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-cursor: hand;"));
        cancelButton.setOnMouseExited(e -> cancelButton.setStyle("-fx-background-color: " + accent_color + ";" + "-fx-text-fill: white;" + "-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-cursor: hand;"));

        HBox buttonBox = new HBox(10, cancelButton, registerButton);
        buttonBox.setAlignment(Pos.CENTER);

        statusLabel = new Label("");
        statusLabel.setStyle("-fx-text-fill: #e74c3c;" + "-fx-font-size: 12px;");
        statusLabel.setVisible(false);

        card.getChildren().addAll(title, subtitle, separator, usernameLabel, usernameField,emailLabel, emailField, passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField, buttonBox, statusLabel);
        getChildren().add(card);
    }

    public String getUsername(){
        return usernameField.getText();
    }
    public String getEmail(){
        return emailField.getText();
    }
    public String getPassword(){
        return passwordField.getText();
    }
    public String getConfirmPassword(){
        return confirmPasswordField.getText();
    }
    public Button getRegisterButton(){
        return registerButton;
    }
    public Button getCancelButton(){
        return cancelButton;
    }

    public void errorMessage(String message){
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #e74c3c;" + "-fx-font-size: 12px;");
        statusLabel.setVisible(true);
    }
    public void successMessage(String message){
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #2ecc71;" + "-fx-font-size: 12px;");
        statusLabel.setVisible(true);
    }
    public void clearMessage(){
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        statusLabel.setVisible(false);
    }

    public ViewRegister(){
        generalUI();
    }
}
