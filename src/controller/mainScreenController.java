package controller;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import model.StaticBoard;
import model.GoL;
import model.FileManagement;
import controller.Main;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


/**
 * Created by Pål on 09.02.2017.
 */
public class mainScreenController implements Initializable {

    @FXML private Canvas boardCanvas;
    @FXML private Button playButton;
    @FXML private Slider cellSizeSlider;
    @FXML private ColorPicker aliveCellColorPicker;
    @FXML private ColorPicker deadCellColorPicker;
    @FXML private Label fpsLabel;
    @FXML private Button openFileButton;

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
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){

        //TODO Skjønner ikke hvorfor denne ikke kan initialiseres over (sammen med staticBoard)
        gc = boardCanvas.getGraphicsContext2D();

        // Initialise game values
        GoL.setIsRunning(false);
        GoL.setCellSize(boardCanvas.getHeight()/staticBoard.getCurrentBoard().length);
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


    private void draw(){

        staticBoard.draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
    }


    public void playEvent() {

        if (!GoL.getIsRunning()) {

            play();
        }
        else {

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


    public void setCellSizeEvent(){
        GoL.calculateCellSize(boardCanvas.getWidth(), cellSizeSlider);
        calculateBoardSize();
        draw();


    }

    private void calculateBoardSize () {

        staticBoard.calculateBoardSize();

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
        }
        else {

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
        }
        else {

            if (GoL.getCurrRate() < 20.0) {

                GoL.setCurrRate(GoL.getCurrRate() + 0.5);
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        }
    }


    public void cellClickEvent(MouseEvent event) {

        staticBoard.cellClickDraw(event, gc, boardCanvas);
    }
    public void readGameBoardFromDisk() throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Game of Life file");
        File returnFile = chooser.showOpenDialog(null);
        if (returnFile != null) {
            readGameBoard(new FileReader(returnFile));
        } else {
            System.out.println("User aborted");
        }

        //readGameBoard(new FileReader(returnVal));

        //
    }
    private void readGameBoard(Reader r)  throws IOException {
        StringBuilder fileString = new StringBuilder();
        int data = r.read();
        int i = 0;
        while (data != -1) {
            if (data == 35) {
                data = r.read();
                if (data == 78) {
                    FileManagement.readTitle(r);
                    data = r.read();
                } else if (data == 67) {
                    FileManagement.readComment(r);
                    data = r.read();
                } else {
                    char exitChar = (char) data;
                    System.out.println("False character followed by #:" + exitChar);
                }
            }
            else if (data == 120) {
                System.out.println(FileManagement.readDimension(r));
                data = r.read();
            }
            else if (data == 121) {
                System.out.println(FileManagement.readDimension(r));
                data = r.read();
            }
            else {
                char exitChar = (char) data;
                fileString.append(exitChar);
                data = r.read();
            }
        }
        String fileStringResult = new String(fileString);
        System.out.println("Data: " + fileStringResult);



    /*Scanner testScanner = new Scanner(r);
    System.out.println(testScanner.next());
    System.out.println(testScanner.next());
    System.out.println(testScanner.next());
    System.out.println(testScanner.next());
    */


    /*try {
        int cbuf = r.read();
        char x = (char) cbuf;
        System.out.println(x);
        r.
    }
    catch(IOException e) {
        e.printStackTrace();
    }
    @
    */
    }
}
