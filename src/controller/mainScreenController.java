package controller;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.StaticBoard;
import model.GoL;

/**
 * Created by Pål on 09.02.2017.
 */
public class mainScreenController implements Initializable {

    @FXML private Canvas boardCanvas;
    @FXML private Slider cellSizeSlider;
    @FXML private ColorPicker aliveCellColorPicker;
    @FXML private ColorPicker deadCellColorPicker;
    @FXML private Label fpsLabel;



    private StaticBoard staticBoard = new StaticBoard();
    private boolean hasStarted = false;
    private boolean isRunning = false;

    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            staticBoard.nextGeneration();
            draw();
        }
    }));





    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){

        timeline.setCycleCount(Timeline.INDEFINITE);
        cellSizeSlider.setValue(50.0);
        aliveCellColorPicker.setValue(Color.BLACK);
        deadCellColorPicker.setValue(Color.WHITE);

        fpsLabel.setText("Paused");

        GoL.setAliveCellColor(Color.BLACK);
        GoL.setDeadCellColor(Color.WHITE);
        GoL.setCellSize(450.0/8.0);
        draw();

    }


    private void draw(){

        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        staticBoard.draw(gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());

    }

    public void setCellSizeEvent(){

        GoL.setCellSize(cellSizeSlider.getValue());
        draw();

    }

    public void setAliveCellColorEvent() {

        GoL.setAliveCellColor(aliveCellColorPicker.getValue());
        draw();

    }

    public void setDeadCellColorEvent() {

        GoL.setDeadCellColor(deadCellColorPicker.getValue());
        draw();

    }


    public void startEvent() {
        if (!isRunning) {

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            isRunning = true;
            System.out.println("Start");
            if (!hasStarted) {
                timeline.setRate(5.0);
                hasStarted = true;

            }
            fpsLabel.setText(timeline.getCurrentRate() + " gen/s");

        }

    }

    public void pauseGameEvent() {

        if (isRunning) {
            timeline.pause();
            isRunning = false;
            System.out.println("Pause");
            fpsLabel.setText("Paused");
        }

    }

    public void decreaseSpeedEvent() {
        double currSpeed = timeline.getCurrentRate();
        if (currSpeed > 0.6 && (currSpeed != 0.0)) {

            timeline.setRate(currSpeed - 0.5);
            System.out.println(timeline.getCurrentRate());
            fpsLabel.setText(timeline.getCurrentRate() + " gen/s");

        }

    }

    public void increaseSpeedEvent() {


        double currSpeed = timeline.getCurrentRate();
        if (currSpeed < 20.0 && (currSpeed != 0.0)) {

            timeline.setRate(currSpeed + 0.5);
            System.out.println(timeline.getCurrentRate());
            fpsLabel.setText(timeline.getCurrentRate() + " gen/s");

        }
    }
}
