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
import model.FileManagement;
import model.GoL;
import model.StaticBoard;

import java.io.*;


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


    private void draw() {

        staticBoard.draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
    }

    public double calculateCanvasWidth(int boardWidth) {
        double boardWidthDouble = (double) boardWidth;
        return GoL.getCellSize() * boardWidthDouble;
    }


    public void playEvent() {

        if (!GoL.getIsRunning()) {

            play();
        } else {

            pause();
        }
    }


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


    public void cellClickEvent(MouseEvent event) {

        staticBoard.cellClickDraw(event, gc, boardCanvas);
    }

    public void boardDragEvent(MouseEvent event) {
        staticBoard.cellDragDraw(event, gc, boardCanvas);

    }

    public void readFileFromDisk() throws IOException {

        File rleFile = FileManagement.loadFileFromDisk();
        if ( rleFile != null) {

            String[] fileData = FileManagement.readFile(new FileReader(rleFile));
            implementFileData(fileData);
        }
    }


    public void readFileFromURL() throws IOException {

        InputStream rleStream = FileManagement.loadFileFromURL();
        if (rleStream != null) {

            String[] fileData = FileManagement.readFile(new InputStreamReader(rleStream));
            implementFileData(fileData);
        }
    }

    private void implementFileData(String[] fileData) throws IOException {
        titleText.setText(fileData[0]);
        originText.setText(fileData[1]);
        commentText.setText(fileData[2]);
        int width = FileManagement.readDimension(fileData[3]);
        int height = FileManagement.readDimension(fileData[4]);
        staticBoard.setWIDTH(width);
        staticBoard.setHEIGHT(height);
        staticBoard.newBoard();
        FileManagement.readRules(fileData[5]);
        staticBoard.setBoard(FileManagement.readPattern(fileData[6], width, height));
        calculateCellSizeOnPatternLoad();
        draw();
    }


    public void calculateCellSizeOnPatternLoad() {

        double canvasHeightDouble = boardCanvas.getHeight();
        int boardHeightInt = staticBoard.getHEIGHT();
        double boardHeightDouble = (double) boardHeightInt;
        GoL.setCellSize(canvasHeightDouble / boardHeightDouble);
        cellSizeSlider.setValue(GoL.getCellSize());
    }


}

