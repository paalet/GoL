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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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

    StaticBoard staticBoard = new StaticBoard();
    Timeline timeline;


    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){

        timeline = new Timeline(new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameLoop();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        cellSizeSlider.setValue(50.0);
        aliveCellColorPicker.setValue(Color.BLACK);
        deadCellColorPicker.setValue(Color.WHITE);
        GoL.setAliveCellColor(Color.BLACK);
        GoL.setDeadCellColor(Color.WHITE);
        GoL.setCellSize(50.0);
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

    private void gameLoop() {

        staticBoard.nextGeneration();
        draw();
    }

    public void startGameEvent() {

        timeline.play();
    }

    public void pauseGameEvent() {

        timeline.pause();
    }

    public void decreaseSpeedEvent() {

        if (timeline.getRate() > 0.2 ) {
            timeline.setRate(timeline.getRate()-0.2);
            System.out.println(timeline.getRate());
        }

    }

    public void increaseSpeedEvent() {

        if (timeline.getRate() < 20 ) {
            timeline.setRate(timeline.getRate()+0.2);
            System.out.println(timeline.getRate());
        }
    }
}
