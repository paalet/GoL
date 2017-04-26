package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GifCreatorController implements Initializable {


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


    private Board gifBoard;
    private ObservableList<String> dimensions = FXCollections.observableArrayList("Height in pixels", "Width in pixels");
    private int genCount;


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

    /**
     * Regex check genCount input for positive integer and apply it.
     * @param ok
     * @return
     */
    private boolean inputGenCount(boolean ok) {

        Pattern intPattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = intPattern.matcher(genCountTxtFld.getText());
        if (matcher.matches()) {

            genCount = Integer.parseInt(genCountTxtFld.getText());

        } else {

            ok = false;
        }
        return ok;
    }

    /**
     * Regex check GPS input input for positive integer and apply it.
     * @param ok
     * @return
     */
    private boolean inputGps(boolean ok) {

        Pattern intPattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = intPattern.matcher(gpsTxtFld.getText());
        if (matcher.matches()) {
            GifCreator.calculateAndSetMilliSecondsPerGen(Integer.parseInt(gpsTxtFld.getText()));

        } else {

            ok = false;
        }
        return ok;
    }

    /**
     * Regex check size input for positive integer and apply it.
     * @param ok
     * @return
     */
    private boolean inputSize(boolean ok) {

        Pattern intPattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = intPattern.matcher(gpsTxtFld.getText());
        if (matcher.matches()) {

            int size = Integer.parseInt(sizeTxtFld.getText());
            if (dimensionChoiceBox.getValue() == dimensions.get(0)) {

                GifCreator.setImageSize(gifBoard, "height", size);
            } else {

                GifCreator.setImageSize(gifBoard, "width", size);
            }
        } else {

            ok = false;
        }
        return ok;
    }

    /**
     * Create a GIF. This is done by applying input data from GUI, creating a GIFWriter object,
     * writing to .gif file through use of GIFWriter and logic in model.GifCreator. Finally feedback is provided
     * to user and stage closed.
     */
    public void createGifEvent() {

        lieng.GIFWriter gwriter;
        boolean ok = true;


        // Input variables from GUI. ok set to false if either can not be set
        ok = inputGenCount(ok);
        ok = inputGps(ok);
        ok = inputSize(ok);
        if (!ok) {

            inputFeedbackLbl.setText("Input must be whole numbers greater than zero");

        } else {

            ok = GifCreator.inputPathFromFileChooser(ok);
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
    }

    /**
     * Close the GIF creator without making a GIF
     */
    public void cancelGifEvent() {

        Stage gifStage = (Stage) cancelGifBtn.getScene().getWindow();
        gifStage.close();
    }

}
