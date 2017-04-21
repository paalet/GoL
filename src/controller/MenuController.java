package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by simenperschandersen on 21.04.2017.
 */
public class MenuController {


    @FXML
    private Pane menuPane;
    @FXML
    private Button staticBoardLoadButton;
    @FXML
    private Button autoExpandBoardLoadButton;

    public void loadStaticBoardEvent() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainScreen.fxml"));
        Stage primaryStage = (Stage) menuPane.getScene().getWindow();
        Scene scene = new Scene(root, 825, 723);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void loadAutoExpandingEvent() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainScreen.fxml"));
        Stage primaryStage = (Stage) menuPane.getScene().getWindow();
        Scene scene = new Scene(root, 825, 723);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
