package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import javax.swing.filechooser.FileSystemView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller class for gifCreator.fxml. Creates a GIF based on the current live game board which is deep copied upon
 * instantiating this class. Input fields let's the user manipulate parameters for the gif and a preview of the GIF
 * is displayed.
 */
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
    private Canvas previewCanvas;

    @FXML
    private Label inputFeedbackLbl;

    private Board gifBoard;
    private int width;
    private int height;

    private byte[][] currentStaticBoard;
    private byte[][] nextStaticBoard;
    private byte[][] firstStaticBoard;

    private ArrayList<ArrayList<Byte>> currentDynamicBoard = new ArrayList<>();
    private ArrayList<ArrayList<Byte>> nextDynamicBoard = new ArrayList<>();
    private ArrayList<ArrayList<Byte>> firstDynamicBoard = new ArrayList<>();

    private GraphicsContext gc;
    private double cellSize;
    private ObservableList<String> dimensions = FXCollections.observableArrayList("Height in pixels", "Width in pixels");
    private int genCount;
    private int loopCount;

    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            if(gifBoard instanceof StaticBoard) {

                nextGenerationStaticBoard();

            } else if (gifBoard instanceof DynamicBoard) {

                nextGenerationDynamicBoard();
            }
            drawPreviewCanvas(cellSize, GoL.getAliveCellColor(), GoL.getDeadCellColor(), GoL.getGridColor());
        }
    }));

    /**
     * Contructor does a deep copy of the game board. This board can be manipulated without affecting the live game board.
     * @param gameBoard the current live game board
     */
    GifCreatorController(Board gameBoard) {

        if (gameBoard instanceof StaticBoard) {

            gifBoard = new StaticBoard((StaticBoard) gameBoard);

        } else if (gameBoard instanceof DynamicBoard) {

            gifBoard = new DynamicBoard((DynamicBoard) gameBoard);
        }
        height = gifBoard.getHeight();
        width = gifBoard.getWidth();
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

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(GoL.getCurrRate());

        gc = previewCanvas.getGraphicsContext2D();
        cellSize = calculateCellSize();

        gpsTxtFld.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (oldPropertyValue) {
                    setPreviewRate();
                }
            }
        });

        genCountTxtFld.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (oldPropertyValue) {
                    setGenCount();
                }
            }
        });

        start();
    }

    public void start() {

        if(gifBoard instanceof DynamicBoard) {
            ArrayList<ArrayList<Byte>> dynBoard = ((DynamicBoard) gifBoard).getCurrentBoard();

            for (int y = 0; y < height; y++) {

                currentDynamicBoard.add(new ArrayList<>());
                nextDynamicBoard.add(new ArrayList<>());
                firstDynamicBoard.add(new ArrayList<>());
                for (int x = 0; x < width; x++) {

                    currentDynamicBoard.get(y).add(x, (byte) 0);
                    nextDynamicBoard.get(y).add(x, (byte) 0);
                    firstDynamicBoard.get(y).add(x, (byte) 0);

                }
            }

            for (int y = 0; y < dynBoard.size(); y++) {

                for (int x = 0; x < dynBoard.get(y).size(); x++) {

                    currentDynamicBoard.get(y).set(x, dynBoard.get(y).get(x));
                }
            }
            for (int y = 0; y < dynBoard.size(); y++) {

                for (int x = 0; x < dynBoard.get(y).size(); x++) {

                    nextDynamicBoard.get(y).set(x, dynBoard.get(y).get(x));
                }
            }

            for (int y = 0; y < dynBoard.size(); y++) {

                for (int x = 0; x < dynBoard.get(y).size(); x++) {

                    firstDynamicBoard.get(y).set(x, dynBoard.get(y).get(x));
                }
            }

        }
        else if(gifBoard instanceof StaticBoard) {

            currentStaticBoard = new byte[height][width];
            nextStaticBoard = new byte[height][width];
            firstStaticBoard = new byte[height][width];

            byte[][] statBoard = ((StaticBoard) gifBoard).getCurrentBoard();

            for (int y = 0; y < height; y++) {

                System.arraycopy(statBoard[y], 0, currentStaticBoard[y], 0, width);
            }

            for (int y = 0; y < height; y++) {

                System.arraycopy(statBoard[y], 0, nextStaticBoard[y], 0, width);
            }

            for (int y = 0; y < height; y++) {

                System.arraycopy(statBoard[y], 0, firstStaticBoard[y], 0, width);
            }

        }
        drawPreviewCanvas(cellSize, GoL.getAliveCellColor(), GoL.getDeadCellColor(), GoL.getDeadCellColor());

        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public void setPreviewRate() {
        boolean ok = true;
        double rate = 5;
        String genCount = gpsTxtFld.getText();
        try{
            rate = Double.parseDouble(genCount);
        }
        catch(NumberFormatException numError) {
            gpsTxtFld.setText("5");
            new CustomDialog("Wrong format", true, "<html><body><div style='text-align: center'>Wrong number format.<br>Only numeric values allowed.</div></body></html>");
            ok = false;
        }
        if(ok) {
            timeline.setRate(rate);
        }
    }

    public void setGenCount() {
        boolean ok = true;
        String countString = genCountTxtFld.getText();
        int countInt = 0;
        try{
            countInt = Integer.parseInt(countString);
        }
        catch(NumberFormatException numError) {
            genCountTxtFld.setText("20");
            genCount = 20;
            new CustomDialog("Wrong format", true, "<html><body><div style='text-align: center'>Wrong number format.<br>Only numeric values allowed.</div></body></html>");
            ok = false;
        }
        if(ok) {
            genCount = countInt;
        }
    }

    public double calculateCellSize() {

        if(gifBoard.getHeight() < gifBoard.getWidth()) {
            return previewCanvas.getWidth() / gifBoard.getWidth();

        }
        else if(gifBoard.getHeight() > gifBoard.getWidth()) {
            return previewCanvas.getHeight() / gifBoard.getHeight();

        }
        else {
            return previewCanvas.getHeight() / gifBoard.getHeight();
        }
    }

    public void drawPreviewCanvas(double size, Color aliveCellColor, Color deadCellColor, Color gridColor) {

        // Fill board with deadCellColor
        gc.setFill(deadCellColor);
        gc.fillRect(0, 0,width * size, height * size);
        // Draw boarder around the board
        gc.setStroke(gridColor);
        gc.strokeRect(0, 0, width * size, height * size);
        // Draw live cells
        gc.setFill(aliveCellColor);

        if (gifBoard instanceof StaticBoard) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    if (currentStaticBoard[y][x] == 1) {

                        gc.fillRect((x * size) + 0.5, (y * size) + 0.5, size - 1, size - 1);
                    }
                }
            }
        }

        if (gifBoard instanceof DynamicBoard) {
            for (int y = 0; y < height; y++) {

                for (int x = 0; x < width; x++) {

                    if (currentDynamicBoard.get(y).get(x) == 1) {

                        gc.fillRect((x * size) + 0.5, (y * size) + 0.5, size - 1, size - 1);
                    }
                }
            }
        }

    }

    public void nextGenerationDynamicBoard() {

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                if (currentDynamicBoard.get(y).get(x) == 1) {
                    aliveStatus = 1;
                } else if (currentDynamicBoard.get(y).get(x) == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < currentDynamicBoard.size()) && (x - 1 >= 0 && x - 1 < currentDynamicBoard.get(y - 1).size())) {
                    if (currentDynamicBoard.get(y - 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (y - 1 >= 0 && y - 1 < currentDynamicBoard.size()) {
                    if (currentDynamicBoard.get(y - 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentDynamicBoard.size()) && (x + 1 >= 0 && x + 1 < currentDynamicBoard.get(y - 1).size())) {
                    if (currentDynamicBoard.get(y - 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < currentDynamicBoard.get(y).size()) {
                    if (currentDynamicBoard.get(y).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < currentDynamicBoard.get(y).size()) {
                    if (currentDynamicBoard.get(y).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentDynamicBoard.size()) && (x - 1 >= 0 && x - 1 < currentDynamicBoard.get(y + 1).size())) {
                    if (currentDynamicBoard.get(y + 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (y + 1 >= 0 && y + 1 < currentDynamicBoard.size() && (x >= 0 && x < currentDynamicBoard.get(y + 1).size())) {
                    if (currentDynamicBoard.get(y + 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentDynamicBoard.size()) && (x + 1 >= 0 && x + 1 < currentDynamicBoard.get(y + 1).size())) {
                    if (currentDynamicBoard.get(y + 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                byte nextStatus = GoL.rules(neighbors, aliveStatus);
                nextDynamicBoard.get(y).set(x, nextStatus);
            }

        }

        // Update currentBoard with values from nextBoard
        for (int y = 0; y < currentDynamicBoard.size(); y++) {

            for (int x = 0; x < currentDynamicBoard.get(y).size(); x++) {

                currentDynamicBoard.get(y).set(x, nextDynamicBoard.get(y).get(x));
            }
        }
        loopCount++;
        if(loopCount > genCount) {
            for (int y = 0; y < currentDynamicBoard.size(); y++) {

                for (int x = 0; x < currentDynamicBoard.get(y).size(); x++) {

                    currentDynamicBoard.get(y).set(x, firstDynamicBoard.get(y).get(x));
                }
            }
            loopCount = 1;
        }
    }

    public void nextGenerationStaticBoard() {

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                //Check the status of the cell, whether it is alive or dead.
                if (currentStaticBoard[y][x] == 1) {
                    aliveStatus = 1;
                } else if (currentStaticBoard[y][x] == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < currentStaticBoard.length) && (x - 1 >= 0 && x - 1 < currentStaticBoard[y - 1].length)) {
                    if (currentStaticBoard[y - 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentStaticBoard.length) && (x >= 0 && x < currentStaticBoard[y - 1].length)) {
                    if (currentStaticBoard[y - 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentStaticBoard.length) && (x + 1 >= 0 && x + 1 < currentStaticBoard[y - 1].length)) {
                    if (currentStaticBoard[y - 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < currentStaticBoard[y].length) {
                    if (currentStaticBoard[y][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < currentStaticBoard[y].length) {
                    if (currentStaticBoard[y][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentStaticBoard.length) && (x - 1 >= 0 && x - 1 < currentStaticBoard[y + 1].length)) {
                    if (currentStaticBoard[y + 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentStaticBoard.length) && (x >= 0 && x < currentStaticBoard[y + 1].length)) {
                    if (currentStaticBoard[y + 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentStaticBoard.length) && (x + 1 >= 0 && x + 1 < currentStaticBoard[y + 1].length)) {
                    if (currentStaticBoard[y + 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                nextStaticBoard[y][x] = GoL.rules(neighbors, aliveStatus);
            }

        }

        for (int y = 0; y < height; y++) {

            System.arraycopy(nextStaticBoard[y], 0, currentStaticBoard[y], 0, width);
        }

        loopCount++;
        if(loopCount > genCount){
            for (int y = 0; y < height; y++) {

                System.arraycopy(firstStaticBoard[y], 0, currentStaticBoard[y], 0, width);
            }
            loopCount = 1;
        }
    }

    /**
     * Regex check genCount input for positive integer and apply it.
     * @param ok control variable
     * @return boolean as is if input passes regex, false if not
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
     * @param ok control variable
     * @return boolean as is if input passes regex, false if not
     */
    private boolean inputGenPerSec(boolean ok) {

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
     * @param ok control variable
     * @return boolean as is if input passes regex, false if not
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
     * Set the filepath for saving the GIF at users wanted location. User interaction through FileChooser.
     * @param ok control variable
     * @return boolean as is if file path is created, false if not
     */
    public static boolean inputPathFromFileChooser(boolean ok) {

        FileChooser pathChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif");
        pathChooser.getExtensionFilters().add(extFilter);
        pathChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        try {
            GifCreator.setPath(pathChooser.showSaveDialog(null).getPath());
        } catch(NullPointerException e) {
            new CustomDialog("GIF not created", true,
                    "GIF creation canceled. Choose a filename and target directory to create GIF.");
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
        ok = inputGenPerSec(ok);
        ok = inputSize(ok);
        if (!ok) {

            inputFeedbackLbl.setText("Input must be whole numbers greater than zero");

        } else {

            ok = inputPathFromFileChooser(ok);
            if (ok) {
                try {
                    // Create GIFWriter object and write to .gif
                    gwriter = new lieng.GIFWriter(GifCreator.getImageWidth(),
                            GifCreator.getImageHeight(),
                            GifCreator.getPath(),
                            GifCreator.getMilliSecPerGen());
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
        stop();
        Stage gifStage = (Stage) cancelGifBtn.getScene().getWindow();
        gifStage.close();
    }

}
