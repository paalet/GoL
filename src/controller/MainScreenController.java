package controller;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jdk.nashorn.internal.scripts.JO;
import model.FileManagement;
import model.GoL;
import model.StaticBoard;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;


/**
 * Created by Pål on 09.02.2017.
 */
public class MainScreenController implements Initializable {

    @FXML
    private Canvas boardCanvas;
    @FXML
    private Button playButton;
    @FXML
    private Slider cellSizeSlider;
    @FXML
    private ColorPicker aliveCellColorPicker;
    @FXML
    private ColorPicker deadCellColorPicker;
    @FXML
    private Label fpsLabel;
    @FXML
    private Button openFileButton;
    @FXML
    private TextArea titleText;
    @FXML
    private TextArea originText;
    @FXML
    private TextArea commentText;

    private StaticBoard staticBoard = new StaticBoard();
    private GraphicsContext gc;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            staticBoard.nextGeneration();
            draw();
        }
    }));


    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        //TODO Skjønner ikke hvorfor denne ikke kan initialiseres over (sammen med staticBoard)
        gc = boardCanvas.getGraphicsContext2D();
        // Initialise game values
        GoL.setIsRunning(false);
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2, 3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        GoL.setCellSize(boardCanvas.getHeight() / staticBoard.getHEIGHT());
        boardCanvas.setWidth(calculateCanvasWidth(staticBoard.getWIDTH()));
        GoL.setAliveCellColor(Color.valueOf("0x344c50ff"));
        GoL.setDeadCellColor(Color.valueOf("0xe1effdff"));
        GoL.setCurrRate(5.0);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(GoL.getCurrRate());

        // Display values
        aliveCellColorPicker.setValue(GoL.getAliveCellColor());
        deadCellColorPicker.setValue(GoL.getDeadCellColor());
        cellSizeSlider.setValue(GoL.getCellSize());
        fpsLabel.setText(GoL.getCurrRate() + " gen/s");

        draw();
    }

    /**
     * A simple function that calles the draw function in the Board class.
     */
    private void draw() {

        staticBoard.draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
    }

    /**
     * A function used to calculate the appropriate canvas size in case the dimensions of the board changes.
     * Used to avoid empty spaces on the canvas, which looks ugly, and can result in some nasty exceptions.
     * @param boardWidth
     * @return
     */
    public double calculateCanvasWidth(int boardWidth) {
        double boardWidthDouble = (double) boardWidth;
        return GoL.getCellSize() * boardWidthDouble;
    }

    /**
     * An event listener that gets called upon pressing start. Sets the animation into play if the animation isnt running, pauses otherwise.
     */
    public void playEvent() {

        if (!GoL.getIsRunning()) {

            play();
        } else {

            pause();
        }
    }

    /**
     * Resumes timeline.
     */
    private void play() {

        timeline.setRate(GoL.getCurrRate());
        timeline.play();
        GoL.setIsRunning(true);
        playButton.setText("Pause");
    }


    private void pause() {

        timeline.pause();
        GoL.setIsRunning(false);
        playButton.setText("Resume");
    }

    /**
     * Calculates a cellsize and snaps it, and the slider to the nearest possible value that would fit in the canvas perfectly based on the horizontal size.
     */
    public void setCellSizeEvent() {
        GoL.calculateCellSize(boardCanvas.getHeight(), boardCanvas.getWidth(), cellSizeSlider);
        calculateBoardSize(boardCanvas.getHeight(), boardCanvas.getWidth());
        draw();


    }

    private void calculateBoardSize(double canvasHeight, double canvasWidth) {

        staticBoard.calculateBoardSize(canvasHeight, canvasWidth);

    }


    public void setAliveCellColorEvent() {

        GoL.setAliveCellColor(aliveCellColorPicker.getValue());
        draw();
    }


    public void setDeadCellColorEvent() {

        GoL.setDeadCellColor(deadCellColorPicker.getValue());
        draw();
    }


    public void decreaseSpeedEvent() {

        if (GoL.getIsRunning()) {

            if (GoL.getCurrRate() > 0.6) {

                GoL.setCurrRate(GoL.getCurrRate() - 0.5);
                timeline.setRate(GoL.getCurrRate());
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        } else {

            if (GoL.getCurrRate() > 0.6) {

                GoL.setCurrRate(GoL.getCurrRate() - 0.5);
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        }
    }

    /**
     * Increases speed incrementally with 0.5 fps, and sets the label to display the new value.
     */
    public void increaseSpeedEvent() {

        if (GoL.getIsRunning()) {

            if (GoL.getCurrRate() < 20.0) {

                GoL.setCurrRate(GoL.getCurrRate() + 0.5);
                timeline.setRate(GoL.getCurrRate());
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        } else {

            if (GoL.getCurrRate() < 20.0) {

                GoL.setCurrRate(GoL.getCurrRate() + 0.5);
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        }
    }

    /**
     * Kills or gives birth to a cell on a simple click.
     * @param event
     */
    public void cellClickEvent(MouseEvent event) {

        staticBoard.cellClickDraw(event, gc, boardCanvas);
    }

    /**
     * Functionally the same as cellClickEvent, only with the possibility of dragging across mutiple cells to kill/give birth to cells quickly.
     * @param event
     */
    public void boardDragEvent(MouseEvent event) {
        staticBoard.cellDragDraw(event, gc, boardCanvas);

    }

    public void readFileFromDisk() throws IOException {

        File rleFile = FileManagement.loadFileFromDisk();
        if ( rleFile != null) {

            HashMap<String, String> fileData = FileManagement.readFile(new FileReader(rleFile));
            applyFileData(fileData);
        }
    }


    public void readFileFromURL() throws IOException {

        InputStream rleStream = FileManagement.loadFileFromURL();
        if (rleStream != null) {

            HashMap<String, String> fileData = FileManagement.readFile(new InputStreamReader(rleStream));
            applyFileData(fileData);
        }
    }

    /**
     * Applies data obtained from the readFile method in FileManagement into the running program, such as title, origin, comments, board dimensions, rules, and the board.
     * @param fileData
     * @throws IOException
     */
    private void applyFileData(HashMap<String, String> fileData) throws IOException {

        // Show metadata in GUI
        titleText.setText(fileData.get("title"));
        originText.setText(fileData.get("origin"));
        commentText.setText(fileData.get("comments"));

        // Apply board size
        int width = FileManagement.readDimension(fileData.get("width"));
        int height = FileManagement.readDimension(fileData.get("height"));
        staticBoard.setWIDTH(width);
        staticBoard.setHEIGHT(height);
        staticBoard.newBoard();

        // Apply rules
        int rules[][] = FileManagement.readRules(fileData.get("rules"));
        GoL.setBornAmount(rules[0]);
        GoL.setSurviveAmount(rules[1]);

        // Apply pattern
        staticBoard.setBoard(FileManagement.readPattern(fileData.get("pattern"), width, height));
        calculateCellSizeOnPatternLoad();
        draw();
    }

    /**
     * Re-evaluates an appropriate cellsize if a new board pattern is loaded.
     */
    public void calculateCellSizeOnPatternLoad() {

        double canvasHeightDouble = boardCanvas.getHeight();
        int boardHeightInt = staticBoard.getHEIGHT();
        double boardHeightDouble = (double) boardHeightInt;
        GoL.setCellSize(canvasHeightDouble / boardHeightDouble);
        cellSizeSlider.setValue(GoL.getCellSize());
    }


}

