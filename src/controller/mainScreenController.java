package controller;


import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.ActionEvent;
import javafx.scene.control.Slider;
import model.StaticBoard;
import model.GoL;

/**
 * Created by Pål on 09.02.2017.
 */
public class mainScreenController implements Initializable {

    @FXML private Canvas boardCanvas;
    @FXML private Slider cellSizeSlider;
    StaticBoard staticBoard = new StaticBoard();

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){

        cellSizeSlider.setValue(10.0);
        draw();

    }


    private void draw(){

        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        staticBoard.draw(gc, GoL.getCellSize());



    }

    public void setCellSizeEvent(){


        GoL.setCellSize(cellSizeSlider.getValue());
        draw();

    }
}
