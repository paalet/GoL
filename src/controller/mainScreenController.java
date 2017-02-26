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
import javafx.util.Duration;
import model.StaticBoard;
import model.GoL;


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


    private StaticBoard staticBoard = new StaticBoard();
    private GraphicsContext gc;
    private boolean isRunning;
    private double currRate;
    private Timeline timeline;


    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){

        GoL.setAliveCellColor(Color.valueOf("0x344c50ff"));
        GoL.setDeadCellColor(Color.valueOf("0xe1effdff"));
        GoL.setCellSize(450.0/8.0);

        cellSizeSlider.setValue(GoL.getCellSize());
        aliveCellColorPicker.setValue(GoL.getAliveCellColor());
        deadCellColorPicker.setValue(GoL.getDeadCellColor());

        timeline = new Timeline(new KeyFrame(Duration.millis(1000.0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                staticBoard.nextGeneration();
                draw();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(5.0);
        currRate = 5.0;
        fpsLabel.setText(currRate + " gen/s");
        isRunning = false;
        gc = boardCanvas.getGraphicsContext2D();
        draw();
    }


    private void draw(){

        staticBoard.draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
    }


    public void playEvent() {

        if (!isRunning) {

            play();
        }
        else {

            pause();
        }
    }


    private void play() {

        timeline.setRate(currRate);
        timeline.play();
        isRunning = true;
        playButton.setText("Pause");
    }


    private void pause() {

        timeline.pause();
        isRunning = false;
        playButton.setText("Resume");
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


    public void decreaseSpeedEvent() {

        if (isRunning) {

            if (currRate > 0.6 && (currRate != 0.0)) {

                currRate -= 0.5;
                timeline.setRate(currRate);
                fpsLabel.setText(currRate + " gen/s");
            }
        }
        else {

            if (currRate > 0.6 && (currRate != 0.0)) {

                currRate -= 0.5;
                fpsLabel.setText(currRate + " gen/s");
            }
        }
    }


    public void increaseSpeedEvent() {

        if (isRunning) {

            if (currRate < 20.0 && (currRate != 0.0)) {

                currRate += 0.5;
                timeline.setRate(currRate);
                fpsLabel.setText(currRate + " gen/s");
            }
        }
        else {

            if (currRate < 20.0 && (currRate != 0.0)) {

                currRate += 0.5;
                fpsLabel.setText(currRate + " gen/s");
            }
        }
    }


    public void cellClickEvent(MouseEvent event) {

        staticBoard.cellClickDraw(event, gc, boardCanvas);
    }
}
