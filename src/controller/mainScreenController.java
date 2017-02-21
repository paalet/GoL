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
//TODO make use of GoL.getMsPerGen instead of raw 1000 ms Durarion on Keyframe
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            nextGeneration();
            draw();
        }
    }));


    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){

        cellSizeSlider.setValue(50.0);
        aliveCellColorPicker.setValue(Color.BLACK);
        deadCellColorPicker.setValue(Color.WHITE);
        GoL.setAliveCellColor(Color.BLACK);
        GoL.setDeadCellColor(Color.WHITE);
        GoL.setCellSize(50.0);
        GoL.setMsPerGen(1);
        draw();

    }


    private void draw(){

        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        staticBoard.draw(gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());

    }

    public void nextGeneration() {


        for (int x = 0; x < staticBoard.board.length; x++) {
            for (int y = 0; y < staticBoard.board.length; y++) {

                //Counts the neighbors of the particular cell
                int neighbors = 0;
                int aliveStatus = 0;

                //Checks the status of the cell, whether ist alive or dead.
                //Tries and catches ArrayOutOfBoundsExceptions which may occur, like fex. if you
                //base your position from board[0[0] and you try to find x-1 which will result in board[-1][0] which is out of bounds.
                //Nothing happens if exception is caught.

                try  {
                    if (staticBoard.board[x][y] == 1) {
                        aliveStatus = 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (staticBoard.board[x][y] == 0) {
                        aliveStatus = 0;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (staticBoard.board[x - 1][y] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (staticBoard.board[x][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                try  {
                    if (staticBoard.board[x - 1][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (staticBoard.board[x + 1][y] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (staticBoard.board[x][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (staticBoard.board[x+1][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (staticBoard.board[x - 1][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                try  {
                    if (staticBoard.board[x + 1][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                //Returns a value to a temporary nextboard based on the rules method in the GoL class.
                staticBoard.nextBoard[x][y] = GoL.rules(neighbors, aliveStatus);

            }

        }
        for (int i = 0; i < staticBoard.board.length; i++) {
            for (int j = 0; j < staticBoard.board.length; j++) {
                staticBoard.board[i][j] = staticBoard.nextBoard[i][j];

            }
        }

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

    public void callNextGenerationEvent() {

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void pauseEvent() {

        timeline.pause();
    }

    public void decreaseSpeedEvent() {

        int currSpeed = GoL.getMsPerGen();
        if (currSpeed <= 2000) {
            GoL.setMsPerGen(currSpeed+250);
        }
    }

    public void increaseSpeedEvent() {

        int currSpeed = GoL.getMsPerGen();
        if (currSpeed > 250) {
            GoL.setMsPerGen(currSpeed-250);
        }
    }
}
