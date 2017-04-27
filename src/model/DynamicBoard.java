package model;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DynamicBoard extends Board {

    private int width = 12;
    private int height = 8;
    private int[] visitedCellWithDrag;
    private ArrayList<ArrayList<Byte>> currentBoard = new ArrayList<>();
    private ArrayList<ArrayList<Byte>> nextBoard = new ArrayList<>();


    /**
     * Constructor sets initial board size and ensures correct size of currentBoard and nextBoard through newBoard().
     */
    public DynamicBoard() {

        width = 12;
        height = 8;
        visitedCellWithDrag = new int[2];
        newBoard();

    }


    /**
     * Constructor for creation of a deep copy of a DynamicBoard object. To be used when operations must be made on
     * a board object without changing the state of the original.
     * @param inputBoard DynamicBoard object to be deep copied.
     */
    public DynamicBoard(DynamicBoard inputBoard) {

        width = inputBoard.width;
        height = inputBoard.height;
        visitedCellWithDrag = new int[2];
        newBoard();
        // Copy the currentBoard arraylist
        for (int y = 0; y < inputBoard.currentBoard.size(); y++) {
            for (int x = 0; x < inputBoard.currentBoard.get(y).size(); x++) {
                currentBoard.get(y).set(x, inputBoard.currentBoard.get(y).get(x));
            }
        }
    }


    /**
     * A load function that simply creates new arrays for the live board, and the next board on which new values in each generation is put in.
     */
    public void newBoard() {

        for (int y = 0; y < height; y++) {

            currentBoard.add(new ArrayList<>());
            nextBoard.add(new ArrayList<>());
            for (int x = 0; x < width; x++) {
                currentBoard.get(y).add(x, (byte) 0);
                nextBoard.get(y).add(x, (byte) 0);
            }
        }
    }


    /**
     * A draw function that loops through every cell and sets the appropriate color based on its alive status.
     * ArrayOutOfBoundsExceptions to catch the cases when it exceeds values in the array.
     * @param boardCanvas
     * @param gc
     * @param size
     * @param aliveCellColor
     * @param deadCellColor
     */
    public void draw(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor) {

            for (int y = 0; y < height; y++) {

                    for (int x = 0; x < width; x++) {
                        if (currentBoard.get(y).get(x) == 1) {
                            gc.setFill(aliveCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                            gc.strokeRect((x * size), (y * size), size, size);
                        } else {
                            gc.setFill(deadCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                            gc.strokeRect((x * size), (y * size), size, size);
                        }
                    }

            }

    }

    public void drawConcurrent(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, int core, int cores) {
        //
    }


    /**
     * Loops through every cell and counts the amount of live neighbor cells in each direction.
     * The next status of each cell is put in the nextBoard array, and what this status should be is based on the rules currently in use in the GoL class.
     * At the end of the loops, the nextBoard is set to be the new currentBoard. This is done in order to avoid mix of data between the old, and the new state of the board, which would result in false patterns.
     */


    public void nextGeneration() {

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                //Check the status of the cell, whether it is alive or dead.
                if (currentBoard.get(y).get(x) == 1) {
                    aliveStatus = 1;
                } else if (currentBoard.get(y).get(x) == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < currentBoard.size()) && (x - 1 >= 0 && x - 1 < currentBoard.get(y - 1).size())) {
                    if (currentBoard.get(y - 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (y - 1 >= 0 && y - 1 < currentBoard.size()) {
                    if (currentBoard.get(y - 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentBoard.size()) && (x + 1 >= 0 && x + 1 < currentBoard.get(y - 1).size())) {
                    if (currentBoard.get(y - 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < currentBoard.get(y).size()) {
                    if (currentBoard.get(y).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < currentBoard.get(y).size()) {
                    if (currentBoard.get(y).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.size()) && (x - 1 >= 0 && x - 1 < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (y + 1 >= 0 && y + 1 < currentBoard.size() && (x >= 0 && x < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.size()) && (x + 1 >= 0 && x + 1 < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                byte nextStatus = GoL.rules(neighbors, aliveStatus);
                nextBoard.get(y).set(x, nextStatus);
            }

        }

        // Update currentBoard with values from nextBoard
        for (int y = 0; y < currentBoard.size(); y++) {

            for (int x = 0; x < currentBoard.get(y).size(); x++) {

                currentBoard.get(y).set(x, nextBoard.get(y).get(x));
            }
        }
    }

    public void nextGenerationConcurrent(int cores, int core) {
        //

    }

    public void copyBoard() {
        //
    }

    /**
     *A functions to change the alive status of each cell, and give this cell ist new color based on this status.
     * Gets the coordinates of the click, calculates which cell is located in this coordinate based on the part of the board currently visible on the canvas.
     * visitedCellWithDrag is used in order to avoid giving the cell a new status whenever the mouse drag is exited, and therefore this mouse release event is activated.
     * @param event
     * @param gc
     * @param boardCanvas
     * @throws ArrayIndexOutOfBoundsException
     */
    public void cellClickDraw(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException  {

        try {
            // Calculate target cell from mouse position
            double posX = event.getX();
            double posY = event.getY();
            double yCellsInFrame = boardCanvas.getHeight() / GoL.getCellSize();
            double xCellsInFrame = boardCanvas.getWidth() / GoL.getCellSize();

            double cellPosX = posX / (boardCanvas.getWidth() / xCellsInFrame);
            double cellPosY = posY / (boardCanvas.getHeight() / yCellsInFrame);

            int cellX = (int) cellPosX;
            int cellY = (int) cellPosY;

            if (visitedCellWithDrag[0] == cellX && visitedCellWithDrag[1] == cellY) {
                visitedCellWithDrag[0] = 999999999;
                visitedCellWithDrag[1] = 999999999;
            } else {
                // Change cell status
                if (currentBoard.get(cellY).get(cellX) == 1) {

                    currentBoard.get(cellY).set(cellX, (byte) 0);
                } else {

                    currentBoard.get(cellY).set(cellX, (byte) 1);
                }

                // Draw new currentBoard
                draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            // Do nothing as a click on the the edge of the canvas is harmless
        }
    }

    /**
     * Functionally the same as cellClickDraw.
     * visitedCellWithDrag is an array which contains the board coordinates of the last visited cell, in order to avoid multiple re-calculations your mouse is hovering in.
     * @param event
     * @param gc
     * @param boardCanvas
     */

    public void cellDragDraw(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException {

        // Calculate target cell from mouse position
        try {
            double posX = event.getX();
            double posY = event.getY();
            double yCellsInFrame = boardCanvas.getHeight() / GoL.getCellSize();
            double xCellsInFrame = boardCanvas.getWidth() / GoL.getCellSize();

            double cellPosX = posX / (boardCanvas.getWidth() / xCellsInFrame);
            double cellPosY = posY / (boardCanvas.getHeight() / yCellsInFrame);

            int cellX = (int) cellPosX;
            int cellY = (int) cellPosY;

            if (!(visitedCellWithDrag[0] == cellX && visitedCellWithDrag[1] == cellY)) {

                if (currentBoard.get(cellY).get(cellX) == 1) {

                    currentBoard.get(cellY).set(cellX, (byte) 0);
                } else {

                    currentBoard.get(cellY).set(cellX, (byte) 1);
                }


                draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());

                visitedCellWithDrag[0] = cellX;
                visitedCellWithDrag[1] = cellY;
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {

            //Do nothing as a mouse drag outside the canvas is harmless
        }
    }

    /**
     * Adds new rows to the board array if the new cellsizes set makes the board not fully cover the canvas, in order to fulfill this need.
     * @param canvasHeight
     * @param canvasWidth
     */
    public void calculateBoardSize(double canvasHeight, double canvasWidth) {

        double cellAmountDoubleWidth = Math.ceil(canvasWidth / GoL.getCellSize());
        int newCellAmountWidth = (int) cellAmountDoubleWidth;
        double roundedWidth = ((double) newCellAmountWidth * GoL.getCellSize());
        double cellAmountDoubleHeight = Math.ceil(canvasHeight / GoL.getCellSize());
        int newCellAmountHeight = (int) cellAmountDoubleHeight;

        // Expand board to fit new size
        if (newCellAmountWidth > width || newCellAmountHeight > height) {

            for (int y = 0; y < newCellAmountHeight; y++) {

                if (currentBoard.size() == y) {

                    currentBoard.add(new ArrayList<>());
                    nextBoard.add(new ArrayList<>());
                }
                for (int x = currentBoard.get(y).size(); x < newCellAmountWidth; x++) {

                    currentBoard.get(y).add(x, (byte) 0);
                    nextBoard.get(y).add(x, (byte) 0);
                }

            }

            height = newCellAmountHeight;
            width = newCellAmountWidth;
        }
    }

    public ArrayList<ArrayList<Byte>> getCurrentBoard() {
        return currentBoard;
    }

    /**
     * Sets a new currentBoard from arraylistinput. Expands currentBoard if it is of smaller size than input.
     * @param newBoard
     */
    public void setCurrentBoard(ArrayList<ArrayList<Byte>> newBoard){

        for (int y = 0; y < newBoard.size(); y++) {

            if (currentBoard.size() == y) {

                currentBoard.add(new ArrayList<>());
            }
            for (int x = 0; x < newBoard.get(y).size(); x++) {

                if (currentBoard.get(y).size() == x) {

                    currentBoard.get(y).add(x, newBoard.get(y).get(x));
                } else {

                    currentBoard.get(y).set(x, newBoard.get(y).get(x));
                }
            }
        }
    }

    public int getWidth() {return width;}

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