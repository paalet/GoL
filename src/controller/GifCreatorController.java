package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

import javax.swing.filechooser.FileSystemView;
import java.net.URL;
import java.util.ResourceBundle;

public class GifCreatorController implements Initializable {

    private Board gifBoard;

    @FXML
    private Button cancelGifBtn;

    @FXML
    private Button createGifBtn;

    @FXML
    private ChoiceBox dimensionChoiceBox;

    @FXML
    private TextField genCountTxtFld;

    @FXML
    private TextField gpsTxtFld;

    @FXML
    private TextField sizeTxtFld;

    @FXML
    private Label inputFeedbackLbl;


    ObservableList<String> dimensions = FXCollections.observableArrayList("Height in pixels", "Width in pixels");
    int genCount;

    public GifCreatorController(Board gameBoard) {

        if (gameBoard instanceof StaticBoard) {

            gifBoard = new StaticBoard((StaticBoard) gameBoard);
        } else if (gameBoard instanceof DynamicBoard) {

            gifBoard = new DynamicBoard((DynamicBoard) gameBoard);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialize GIF creation input values
        dimensionChoiceBox.setItems(dimensions);
        dimensionChoiceBox.setValue(dimensions.get(0));
        genCount = 20;
        genCountTxtFld.setText("20");
        GifCreator.calculateAndSetMilliSecondsPerGen(5);
        gpsTxtFld.setText("5");
        GifCreator.setImageSize(gifBoard, "height", 600);
        sizeTxtFld.setText("600");
        GifCreator.setAliveCellColor(new java.awt.Color((float) GoL.getAliveCellColor().getRed(),
                (float) GoL.getAliveCellColor().getGreen(),
                (float) GoL.getAliveCellColor().getBlue(),
                (float) GoL.getAliveCellColor().getOpacity()));
        GifCreator.setDeadCellColor(new java.awt.Color((float) GoL.getDeadCellColor().getRed(),
                (float) GoL.getDeadCellColor().getGreen(),
                (float) GoL.getDeadCellColor().getBlue(),
                (float) GoL.getDeadCellColor().getOpacity()));

    }

    public void inputGenCountEvent() {

        try {
            genCount = Integer.parseInt(genCountTxtFld.getText());

        } catch (NumberFormatException e) {

            inputFeedbackLbl.setText("Input must be whole numbers greater than zero");
        }
    }

    public void inputGpsEvent() {

        try {
            GifCreator.calculateAndSetMilliSecondsPerGen(Integer.parseInt(gpsTxtFld.getText()));

        } catch (NumberFormatException e) {

            inputFeedbackLbl.setText("Input must be whole numbers greater than zero");
        }
    }

    public void inputSizeEvent() {

        try {
            int size = Integer.parseInt(sizeTxtFld.getText());
            if (dimensionChoiceBox.getValue() == dimensions.get(0)) {

                GifCreator.setImageSize(gifBoard, "height", size);
            } else {

                GifCreator.setImageSize(gifBoard, "width", size);
            }
        } catch (NumberFormatException e) {

            inputFeedbackLbl.setText("Input must be whole numbers greater than zero");
        }
    }

    /**
     *
     */
    public void createGifEvent(){

        lieng.GIFWriter gwriter;
        boolean ok = true;

        
        // ok set to false if path is not set
         ok = GifCreator.inputPathfromFileChooser(ok);

        if (ok) {
            try {
                // Create GIFWriter object and write to .gif
                gwriter = new lieng.GIFWriter(GifCreator.getImageWidth(),
                        GifCreator.getImageHeight(),
                        GifCreator.getPath(),
                        GifCreator.getMilliSecondsPerGen());
                gwriter.setBackgroundColor(java.awt.Color.black);
                gwriter.flush();
                GifCreator.writeGif(gwriter, gifBoard, genCount);
            } catch (Exception e) {
                new CustomDialog("GIF not created", true,
                        "GIF creation could not complete.");
                ok = false;

            }
            if (ok) {
                // Give feedback to user and close stage
                Stage gifStage = (Stage) createGifBtn.getScene().getWindow();
                gifStage.close();
                new CustomDialog("GIF created", true,
                        "Your GIF has been created and saved to " + GifCreator.getPath());
            }
        }
    }


    public void cancelGifEvent() {

        Stage gifStage = (Stage) cancelGifBtn.getScene().getWindow();
        gifStage.close();
    }

}
