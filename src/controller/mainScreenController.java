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

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){
        GoL gol = new GoL();
        StaticBoard staticBoard = new StaticBoard();
        draw(staticBoard);
    }

    private void draw(StaticBoard staticBoard){
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        staticBoard.draw(gc, gol.getCelllSize);
    }

    public void setCellSizeEvent(){
        gol.setCellSize(cellSizeSlider.getValue());

    }
}
