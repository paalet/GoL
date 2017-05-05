package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Concrete class extending Board for implementation of the Game with a static board size.
 */
public class StaticBoard extends Board {

    private int width;
    private int height;
    private int[] visitedCellWithDrag;
    private byte[][] currentBoard;
    private byte[][] nextBoard;


    /**
     * Constructor sets initial board size and ensures correct size of currentBoard and nextBoard through newBoard().
     */
    public StaticBoard() {

        width = 12;
        height = 8;
        visitedCellWithDrag = new int[2];
        newBoard();
    }


    /**
     * Constructor for creation of a deep copy of a StaticBoard object. To be used when operations must be made on
     * a board object without changing the state of the original.
     * @param inputBoard StaticBoard object to be deep copied.
     */
    public StaticBoard(StaticBoard inputBoard) {

        width = inputBoard.width;
        height = inputBoard.height;
        visitedCellWithDrag = new int[2];
        newBoard();
        // Copy the currentBoard array
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                currentBoard[y][x] = inputBoard.currentBoard[y][x];
            }
        }

    }


    /**
     * A load function that simply creates new arrays for the live board, and the next board on which new values in each generation is put in.
     */
    public void newBoard() {

        currentBoard = new byte[height][width];
        nextBoard = new byte[height][width];
    }


    public void draw(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, Color gridColor) {


        double gapSize = 1;

        if (size < 4) {

            gapSize = 0;
        } else if (size < 10) {

            gapSize = .5;
        }
        // Fill board with deadCellColor
        gc.setFill(deadCellColor);
        gc.fillRect(0, 0,width * size, height * size);
        // Draw boarder around the board
        gc.setStroke(gridColor);
        gc.strokeRect(0, 0, width * size, height * size);
        // Draw live cells
        gc.setFill(aliveCellColor);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if (currentBoard[y][x] == 1) {

                    gc.fillRect((x * size) + (gapSize / 2), (y * size) + (gapSize / 2), size - gapSize, size - gapSize);
                }
            }
        }
    }

    /**
     * Draws a cell grid on the cnavas
     * @param gc
     * @param size
     * @param gridColor
     */
    public void drawGrid(GraphicsContext gc, double size, Color gridColor) {

        gc.setStroke(gridColor);

        // Reduce grid line thickness with smaller cell size
        if (size < 7) {

            gc.setLineWidth(.1);

        } else if (size < 10) {

            gc.setLineWidth(.2);

        } else if (size < 20) {

            gc.setLineWidth(.4);

        } else {

            gc.setLineWidth(.8);
        }

        // Draw grid
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                gc.strokeRect(x * size, y * size, size, size);
            }
        }
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
                if (currentBoard[y][x] == 1) {
                    aliveStatus = 1;
                } else if (currentBoard[y][x] == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < currentBoard.length) && (x - 1 >= 0 && x - 1 < currentBoard[y - 1].length)) {
                    if (currentBoard[y - 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentBoard.length) && (x >= 0 && x < currentBoard[y - 1].length)) {
                    if (currentBoard[y - 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentBoard.length) && (x + 1 >= 0 && x + 1 < currentBoard[y - 1].length)) {
                    if (currentBoard[y - 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < currentBoard[y].length) {
                    if (currentBoard[y][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < currentBoard[y].length) {
                    if (currentBoard[y][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.length) && (x - 1 >= 0 && x - 1 < currentBoard[y + 1].length)) {
                    if (currentBoard[y + 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.length) && (x >= 0 && x < currentBoard[y + 1].length)) {
                    if (currentBoard[y + 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.length) && (x + 1 >= 0 && x + 1 < currentBoard[y + 1].length)) {
                    if (currentBoard[y + 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                nextBoard[y][x] = GoL.rules(neighbors, aliveStatus);
            }

        }

        for (int y = 0; y < height; y++) {

            System.arraycopy(nextBoard[y], 0, currentBoard[y], 0, width);
        }
    }

    public void nextGenerationConcurrent(int cores , int core) {

        int widthPerCore = width / cores;
        int modulo = width % cores;

        int startWidth;
        int endWidth;
        // Make a number of threads equal to the modulo increase their workload by one column
        if (core <= modulo) {

            startWidth = (core - 1) * (widthPerCore + 1);
            endWidth = startWidth + widthPerCore + 1;

            // Set the workload for the remaining threads
        } else {

            startWidth = ((core - 1) * widthPerCore) + modulo;
            endWidth = startWidth + widthPerCore;
        }

        //Check the status of each cell of the board, whether it is alive or dead.
        for (int y = 0; y < height; y++) {

            for (int x = startWidth; x < endWidth; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                //Check the status of the cell, whether it is alive or dead.
                if (currentBoard[y][x] == 1) {
                    aliveStatus = 1;
                } else if (currentBoard[y][x] == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < currentBoard.length) && (x - 1 >= 0 && x - 1 < currentBoard[y - 1].length)) {
                    if (currentBoard[y - 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentBoard.length) && (x >= 0 && x < currentBoard[y - 1].length)) {
                    if (currentBoard[y - 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentBoard.length) && (x + 1 >= 0 && x + 1 < currentBoard[y - 1].length)) {
                    if (currentBoard[y - 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < currentBoard[y].length) {
                    if (currentBoard[y][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < currentBoard[y].length) {
                    if (currentBoard[y][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.length) && (x - 1 >= 0 && x - 1 < currentBoard[y + 1].length)) {
                    if (currentBoard[y + 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.length) && (x >= 0 && x < currentBoard[y + 1].length)) {
                    if (currentBoard[y + 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.length) && (x + 1 >= 0 && x + 1 < currentBoard[y + 1].length)) {
                    if (currentBoard[y + 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                nextBoard[y][x] = GoL.rules(neighbors, aliveStatus);
            }

        }

    }

    public void updateCurrentFromNextBoard() {

        for (int y = 0; y < height; y++) {

            System.arraycopy(nextBoard[y], 0, currentBoard[y], 0, width);
        }
    }

    public void setBoardSizeToDimensions(Board board, int newHeight, int newWidth) {

        // Create new board arrays according to new size
        byte[][] newCurrentBoard = new byte[newHeight][newWidth];
        byte[][] newNextBoard = new byte[newHeight][newWidth];

        // Copy cell status from currentBoard
        for (int y = 0; y < newHeight && y < height; y++) {

            for (int x = 0; x < newWidth && x < width; x++) {

                newCurrentBoard[y][x] = currentBoard[y][x];
            }
        }

        // Set new board and dimensions
        height = newHeight;
        width = newWidth;
        currentBoard = newCurrentBoard;
        nextBoard = newNextBoard;
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
    public void cellClick(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException  {

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
                if (currentBoard[cellY][cellX] == 1) {

                    currentBoard[cellY][cellX] = 0;
                } else {

                    currentBoard[cellY][cellX] = 1;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Do nothing as a click on the edge of the canvas is harmless
        }
    }

    /**
     * Functionally the same as cellClick.
     * visitedCellWithDrag is an array which contains the board coordinates of the last visited cell, in order to avoid multiple re-calculations your mouse is hovering in.
     * @param event
     * @param gc
     * @param boardCanvas
     */

    public void cellDrag(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException {

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

                if (currentBoard[cellY][cellX] == 1) {

                    currentBoard[cellY][cellX] = 0;

                } else {

                    currentBoard[cellY][cellX] = 1;
                }
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

        double cellAmountDoubleHeight = Math.ceil(canvasHeight / GoL.getCellSize());
        int newCellAmountHeight = (int) cellAmountDoubleHeight;

        byte[][] newBoard = new byte[newCellAmountHeight][newCellAmountWidth];


        if (newCellAmountHeight > height) {

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    newBoard[y][x] = currentBoard[y][x];
                }
            }
            for (int y = height; y < newCellAmountHeight; y++) {
                for (int x = width; x < newCellAmountWidth; x++) {
                    newBoard[y][x] = 0;
                }
            }

            height = newCellAmountHeight;
            width = newCellAmountWidth;
            newBoard();
            for (int y = 0; y < newCellAmountHeight; y++) {

                for (int x = 0; x < newCellAmountWidth; x++) {

                    currentBoard[y][x] = newBoard[y][x];
                    nextBoard[y][x] = newBoard[y][x];
                }
            }

        }
    }

    public byte[][] getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(byte[][] newBoard) {

        currentBoard = new byte[height][width];
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                currentBoard[y][x] = newBoard[y][x];
            }
        }
    }


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

    /**
     * Converts the byte array currentBoard into a string of consecutive "1"s and "0"s.
     * @return the string made from currentBoard
     */
    @Override
    public String toString() {

        StringBuilder boardStringBuilder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                byte b = currentBoard[y][x];
                boardStringBuilder.append(b);
            }
        }
        String boardString = new String(boardStringBuilder);
        return boardString;
    }
}
