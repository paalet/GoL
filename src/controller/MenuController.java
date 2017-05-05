package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller class for menu.fxml. Let's user choose which board type to start a game with.
 */
public class MenuController {

    @FXML
    private Pane menuPane;
    @FXML
    private Button staticBoardLoadButton;
    @FXML
    private Button dynamicBoardLoadButton;

    /**
     * Actionevent for staticBoardLoadButton. Loads mainScreen.fxml as the scene of primaryStage and initializes a
     * static board through a MainScreenController object.
     * @throws Exception if mainScreen.fxml con not be loaded
     */
    public void loadStaticBoardEvent() throws Exception {

        // Set up stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainScreen.fxml"));
        Stage primaryStage = (Stage) menuPane.getScene().getWindow();
        Pane root = loader.load();
        Scene scene = new Scene(root, 1299, 872);
        primaryStage.setScene(scene);

        // Create controller, initialize board and show stage
        MainScreenController mainScreenController = loader.getController();
        mainScreenController.initializeStaticBoard();
        primaryStage.show();

    }

    /**
     * Actionevent for dynamicBoardLoadButton. Loads mainScreen.fxml as the scene of primaryStage and initializes a
     * dynamic board through a MainScreenController object.
     * @throws Exception if mainScreen.fxml can not be loaded
     */
    public void loadDynamicBoardEvent() throws Exception {

        // Set up stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainScreen.fxml"));
        Stage primaryStage = (Stage) menuPane.getScene().getWindow();
        Pane root = loader.load();
        Scene scene = new Scene(root, 1299, 872);
        primaryStage.setScene(scene);

        // Create controller, initialize board and show stage
        MainScreenController mainScreenController = loader.getController();
        mainScreenController.initializeDynamicBoard();
        primaryStage.show();
    }
}
