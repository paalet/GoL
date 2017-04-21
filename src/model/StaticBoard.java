package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Concrete class extending Board for implementation of the Game with a static board size.
 */
public class StaticBoard extends Board {

    private int width = 12;
    private int height = 8;
    private int[] visitedCellWithDrag = new int[2];
    private byte[][] currentBoard;
    private byte[][] nextBoard;

    public StaticBoard() {

        newBoard();
    }


    /**
     * A load function that simply creates new arrays for the live board, and the next board on which new values in each generation is put in.
     */
    public void newBoard() {

        currentBoard = new byte[height][width];
        nextBoard = new byte[height][width];
    }


    public void draw(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor) {

        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        gc.strokeRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        try {
            for (int y = 0; y < height; y++) {
                try {
                    for (int x = 0; x < width; x++) {
                        if (currentBoard[y][x] == 1) {
                            gc.setFill(aliveCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                            gc.strokeRect((x * size), (y * size), size, size);
                        } else {
                            gc.setFill(deadCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                            gc.strokeRect((x * size), (y * size), size, size);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

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
                if ((y - 1 >= 0 && y - 1 < currentBoard.length) && (x - 1 >= 0 && x - 1 < currentBoard[y].length)) {
                    if (currentBoard[y - 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (y - 1 >= 0 && y - 1 < currentBoard.length) {
                    if (currentBoard[y - 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentBoard.length) && (x + 1 >= 0 && x + 1 < currentBoard[y].length)) {
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

                if ((y + 1 >= 0 && y + 1 < currentBoard.length) && (x - 1 >= 0 && x - 1 < currentBoard[y].length)) {
                    if (currentBoard[y + 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (y + 1 >= 0 && y + 1 < currentBoard.length) {
                    if (currentBoard[y + 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.length) && (x + 1 >= 0 && x + 1 < currentBoard[y].length)) {
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

            // Draw new currentBoard
            draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
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


                draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());

                visitedCellWithDrag[0] = cellX;
                visitedCellWithDrag[1] = cellY;
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            //Do nothing
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
        byte[][] newBoard = new byte[newCellAmountHeight][newCellAmountWidth];


        if (newCellAmountHeight > height) {


            try {
                for (int y = 0; y < height; y++) {
                    try {
                        for (int x = 0; x < width; x++) {

                            newBoard[y][x] = currentBoard[y][x];
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                //
            }


            try {
                for (int i = 1; i < ((newCellAmountHeight - height) + 1); i++) {

                    try {
                        for (int y = 0; y < newCellAmountHeight; y++) {
                            for (int x = 0; x < newCellAmountWidth; x++) {
                                newBoard[y][newCellAmountWidth - i] = 0;
                                newBoard[newCellAmountHeight - i][x] = 0;

                            }


                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //
            }

            height = newCellAmountHeight;
            width = newCellAmountWidth;
            currentBoard = newBoard;
            nextBoard = newBoard;

        }
    }

    public byte[][] getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(byte[][] newBoard) {

    /*    for (int y = 0; y < height; y++) {

            System.arraycopy(newBoard[y], 0, currentBoard[y], 0, width);
        }
    */
    currentBoard = newBoard;
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
