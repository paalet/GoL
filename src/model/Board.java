package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Abstract lass containing data and logic related to the size, contents and drawing of the game board.
 */
public abstract class Board {

    private int width, height;
    int a = 0;




    /**
     * A draw function that loops through every cell and sets the appropriate color based on its alive status.
     * ArrayOutOfBoundsExceptions to catch the cases when it exceeds values in the array.
     *
     * @param boardCanvas
     * @param gc
     * @param size
     * @param aliveCellColor
     * @param deadCellColor

     */
    public abstract void draw(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, Color gridColor);

    public abstract void drawGrid(GraphicsContext gc, double size, Color gridColor);


    /**
     * A load function that simply creates new arrays for the live board, and the next board on which new values in each generation is put in.
     */
    public abstract void newBoard();

    /**
     * Loops through every cell and counts the amount of live neighbor cells in each direction.
     * The next status of each cell is put in the nextBoard array, and what this status should be is based on the rules currently in use in the GoL class.
     * At the end of the loops, the nextBoard is set to be the new currentBoard. This is done in order to avoid mix of data between the old, and the new state of the board, which would result in false patterns.
     */
    public abstract void nextGeneration();


    public abstract void nextGenerationConcurrent(int cores, int core);

    public abstract void copyBoard();



    /**
     *A functions to change the alive status of each cell, and give this cell ist new color based on this status.
     * Gets the coordinates of the click, calculates which cell is located in this coordinate based on the part of the board currently visible on the canvas.
     * visitedCellWithDrag is used in order to avoid giving the cell a new status whenever the mouse drag is exited, and therefore this mouse release event is activated.
     * @param event
     * @param gc
     * @param boardCanvas
     * @throws ArrayIndexOutOfBoundsException
     */
    public abstract void cellClickDraw(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException;

    /**
     * Functionally the same as cellClickDraw.
     * visitedCellWithDrag is an array which contains the board coordinates of the last visited cell, in order to avoid multiple re-calculations your mouse is hovering in.
     * @param event
     * @param gc
     * @param boardCanvas
     */

    public abstract void cellDragDraw(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException;


    /**
     * Adds new rows to the board array if the new cellsizes set makes the board not fully cover the canvas, in order to fulfill this need.
     * @param canvasHeight
     * @param canvasWidth
     */
    public abstract void calculateBoardSize(double canvasHeight, double canvasWidth);

    public abstract <T> T getCurrentBoard();

    public int getWidth() {
        return width;
    }

    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }
}
