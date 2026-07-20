package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ViewAddReview extends VBox {

    private static final String background_color1 = "#1e1e1e";
    private static final String background_color2 = "#2a3f5f";
    private static final String background_color3 = "#1e3a52";
    private static final String primary_text = "#c6d4df";
    private static final String accent_color = "#66c0f4";
    private static final String button_color = "#4d8ec4";
    private static final String caution = "#e74c3c";

    private TextField titleField;
    private TextField ratingField;
    private TextArea noteArea;

    private Button confirmButton;
    private Button cancelButton;

    private void generalUI(){

        setPadding(new Insets(30));
        setSpacing(8);
        setAlignment(Pos.CENTER);
        setMaxWidth(400);
        setStyle("-fx-background-color: " + background_color1 + ";");

        Label title = new Label("Add new Review");
        title.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: " + accent_color + ";");

        titleField = new TextField();
        titleField.setPromptText("Enter Game Title");
        titleField.setStyle("-fx-background-color: " + background_color3 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 8px;");

        ratingField = new TextField();
        ratingField.setPromptText("1 - 10");
        ratingField.setStyle("-fx-background-color: " + background_color3 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 8px;");

        noteArea = new TextArea();
        noteArea.setPromptText("Write your Review!");
        noteArea.setStyle("-fx-background-color: " + background_color3 + ";" + "-fx-text-fill: " + primary_text + ";" + "-fx-border-color: #3d5a73;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 8px;");

        confirmButton = new Button("Add Review");
        confirmButton.setStyle("-fx-background-color: " + button_color + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 8px 16px;" + "-fx-cursor: hand;");

        cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: " + caution + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-padding: 8px 16px;" + "-fx-cursor: hand;");

        Label titleLabel = new Label("Game Title");
        titleLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        Label ratingLabel = new Label("Rating");
        ratingLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        Label noteLabel = new Label("Note");
        noteLabel.setStyle("-fx-text-fill: " + primary_text + ";" + "-fx-font-size: 13px;" + "-fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);

        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(column1, column2);

        grid.add(titleLabel, 0,0);
        grid.add(titleField, 1,0);
        grid.add(ratingLabel, 0, 1);
        grid.add(ratingField, 1, 1);
        grid.add(noteLabel, 0, 2);
        grid.add(noteArea, 1,2);

        HBox buttons = new HBox(10, cancelButton, confirmButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(
                title, grid, buttons
        );
    }

    public ViewAddReview(){
        generalUI();
    }

    public String getGameTitle(){
        return titleField.getText();
    }
    public String getRating(){
        return ratingField.getText();
    }
    public String getNote(){
        return noteArea.getText();
    }
    public Button getConfirmButton(){
        return confirmButton;
    }
    public Button getCancelButton(){
        return cancelButton;
    }
}
