package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application class that launches the application and sets the primary Stage.
 */
public class Main extends Application {

    /**
     * Loads menu.fxml and sets it as the Scene of primary Stage before showing it.
     * @param primaryStage is the default starting Stage of the application
     * @throws Exception if menu.fxml can not be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
    try {
        Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(new Scene(root, 1299, 872
        ));
        primaryStage.setResizable(false);
        primaryStage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    /**
     * Ensures exit of the application.
     */
    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * The entry point of the application.
     * @param args the command line arguments passed to the application
     */
    public static void main(String[] args) {

      launch(args);

    }
}
