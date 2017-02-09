package controller;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.StaticBoard;

/**
 * Created by Pål on 09.02.2017.
 */
public class mainScreenController implements Initializable {

    @FXML private Canvas canvas;

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){
        StaticBoard staticBoard = new StaticBoard();
        draw(staticBoard);
    }

    private void draw(StaticBoard staticBoard){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double size = 50;
        staticBoard.draw(gc, size);
    }
}
