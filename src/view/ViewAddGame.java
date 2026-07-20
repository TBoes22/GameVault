package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import models.Status;

public class ViewAddGame extends VBox {

    private static final String background_color1 = "#1e1e1e";
    private static final String background_color2 = "#2a3f5f";
    private static final String background_color3 = "#1e3a52";
    private static final String primary_text = "#c6d4df";
    private static final String accent_color = "#66c0f4";
    private static final String button_color = "#4d8ec4";
    private static final String caution = "#e74c3c";

    private TextField titleField;
    private TextField platformField;
    private TextField genreField;
    private TextField developerField;
    private TextField priceField;
    private ComboBox<Status> statusDropdown;

    private Button confirmButton;
    private Button cancelButton;

    private void generalUI(){

        setPadding(new Insets(30));
        setSpacing(8);
        setAlignment(Pos.CENTER);
        setMaxWidth(400);
        setStyle("-fx-background-color: " + background_color1 + ";");

        Label title = new Label("Add new Game");
        title.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + accent_color + ";");

        titleField = new TextField();
        titleField.setStyle("-fx-background-color: " + background_color3 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 8px;");

        platformField = new TextField();
        platformField.setStyle("-fx-background-color: " + background_color3 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 8px;");

        genreField = new TextField();
        genreField.setStyle("-fx-background-color: " + background_color3 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 8px;");

        developerField = new TextField();
        developerField.setStyle("-fx-background-color: " + background_color3 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 8px;");

        priceField = new TextField();
        priceField.setStyle("-fx-background-color: " + background_color3 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 8px;");

        statusDropdown = new ComboBox<>();
        statusDropdown.getItems().addAll(Status.values());
        statusDropdown.setValue(Status.OWNED);
        statusDropdown.setStyle("-fx-background-color: " + background_color1 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6");
        statusDropdown.setMaxWidth(Double.MAX_VALUE);

        confirmButton = new Button("Add Game");
        confirmButton.setStyle("-fx-background-color: " + button_color + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 8px 16px;" + "-fx-cursor: hand;");

        cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: " + caution + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 8px 16px;" + "-fx-cursor: hand;");

        Label titleLabel = new Label("Title");
        titleLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        Label platformLabel = new Label("Platform");
        platformLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        Label genreLabel = new Label("Genre");
        genreLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        Label developerLabel = new Label("Developer");
        developerLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        Label priceLabel = new Label("Price");
        priceLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        Label statusLabel = new Label("Status");
        statusLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);

        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(column1, column2);

        grid.add(titleLabel, 0,0);
        grid.add(titleField, 1,0);
        grid.add(platformLabel, 0, 1);
        grid.add(platformField, 1, 1);
        grid.add(genreLabel, 0, 2);
        grid.add(genreField, 1,2);
        grid.add(developerLabel, 0,3);
        grid.add(developerField, 1,3);
        grid.add(priceLabel, 0,4);
        grid.add(priceField, 1,4);
        grid.add(statusLabel, 0,5);
        grid.add(statusDropdown, 1,5);

        HBox buttons = new HBox(10, cancelButton, confirmButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(
                title, grid, buttons
        );
    }

    public ViewAddGame(){
        generalUI();
    }

    public String getTitle(){
        return titleField.getText();
    }
    public String getPlatform(){
        return platformField.getText();
    }
    public String getGenre(){
        return genreField.getText();
    }
    public String getDeveloper(){
        return developerField.getText();
    }
    public String getPrice(){
        return priceField.getText();
    }
    public Status getStatus(){
        return statusDropdown.getValue();
    }
    public Button getConfirmButton(){
        return confirmButton;
    }
    public Button getCancelButton(){
        return cancelButton;
    }
}
