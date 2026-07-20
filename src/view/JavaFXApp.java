package view;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ClientModel;

public class JavaFXApp extends Application {

    @Override
    public void start(Stage stage){

        ClientModel  model = new ClientModel();

        ViewLogin viewLogin = new ViewLogin();

        Scene scene = new Scene(viewLogin, 900, 600);
        scene.setFill(javafx.scene.paint.Color.web("#1e1e1e"));

        stage.setTitle("Game Collection");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

        GameController controller = new GameController(model, stage);
        controller.initializeViewLogin(viewLogin);

    }

    public static void main(String[] args){
        launch(args);
    }

}
