package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
            primaryStage.setTitle("Game of Life");
            primaryStage.setScene(new Scene(root, 1299, 872
            ));
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {

      launch(args);

    }
}
