package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Abstract class ensuring logic related to the contents, size and drawing of the game board.
 */
public abstract class Board {

    private int width, height;

    /**
     * Creates new instances of currentBoard and nextBoard.
     */
    public abstract void newBoard();

    /**
     * A draw function that loops through every cell of the board and draws a square of the appropriate color at the cells'
     * position on the canvas. Gap between squares is reduced the smaller the cell size to improve visual quality.
     * @param boardCanvas the canvas to draw onto
     * @param gc GraphicsContext of the canvas
     * @param size size of each cell
     * @param aliveCellColor color used to draw live cells
     * @param deadCellColor color used to draw dead cells
     * @param gridColor color used to draw edge around canvas
     */
    public abstract void draw(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, Color gridColor);

    /**
     * Draws a cell grid on the canvas. This method is only called when the game is paused to provide visual aid when user is drawing on the
     * game board or analysing the pattern. Grid line width is reduced the smaller the cell size to correspond with draw().
     * @param gc the GraphicsContent to draw onto the canvas with
     * @param size size of each cell
     * @param gridColor color used to draw the grid
     */
    public abstract void drawGrid(GraphicsContext gc, double size, Color gridColor);

    /**
     * Loops through every cell and counts the amount of live neighbor cells in each direction.
     * The next status of each cell is put in the nextBoard array, and what this status should be is based on the rules currently in use in the GoL class.
     * At the end of the loops, the nextBoard is set to be the new currentBoard. This is done in order to avoid mix of data between the old, and the new state of the board, which would result in false patterns.
     */
    public abstract void nextGeneration();

    /**
     * Similar method to nextGeneration, only adjusted for compatibility with thread seperation.
     * Splits up the board in x number of parts, each handled by a seperate thread, which results in a nextBoard array/arraylist which are copied to the currentBoard and later drawn with draw()
     * The rest of the cells which may not add up in a divition by the amount of threads(one for each core in the machine) is handled by a modulo calculation which are added to the last thread.
     * @param cores Represents the amount of cores in the active computers CPU. Used to calculate how many rows of cells each core/thread shall handle.
     * @param core Represents the thread with the corresponding core number calling the method. Used to decide what part of the board the active core/thread shall be allocated.
     */


    public abstract void nextGenerationConcurrent(int cores, int core);

    /**
     * Copy the values from nextBoard to currentBoard. This moves the game to the next generation.
     */
    public abstract void updateCurrentFromNextBoard();


    /**
     * Expand or shrink the board to fit new dimensions given in DimensionInputDialog.
     * @param board the current live game board
     * @param newHeight the target height of the board
     * @param newWidth the target width of the board
     */
    public abstract void setBoardSizeToDimensions(Board board, int newHeight, int newWidth);

    /**
     * Adds new rows to the board array if the new cell sizes set makes the board not fully cover the canvas, in order to auto-fill the board.
     * @param canvasHeight height of canvas
     * @param canvasWidth width of canvas
     */
    public abstract void calculateBoardSize(double canvasHeight, double canvasWidth);

    /**
     * Change the alive status of each cell, and give this cell its new color based on this status.
     * Gets the coordinates of the click, calculates which cell is located in this coordinate based on the part of the board currently
     * visible on the canvas. visitedCellWithDrag is used in order to avoid giving the cell a new status whenever the mouse drag is exited,
     * and therefore this mouse release event is activated.
     * @param event of the mouse click on canvas
     * @param gc GraphicsContext of the canvas to draw with
     * @param boardCanvas the canvas clicked
     */
    public abstract void cellClick(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException;

    /**
     * Functionally the same as cellClick. visitedCellWithDrag is an array which contains the board coordinates of the
     * last visited cell, in order to avoid multiple re-calculations of the cell currently targeted by the mouse pointer.
     * @param event of the mouse click and drag on canvas
     * @param gc GraphicsContext of the canvas to draw with
     * @param boardCanvas the canvas clicked
     */
    public abstract void cellDrag(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException;

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
