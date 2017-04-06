package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Abstract lass containing data and logic related to the size, contents and drawing of the game board.
 */
public abstract class Board {

    private int WIDTH = 10;
    private int HEIGHT = 8;
    private int[] visitedCellWithDrag = new int[2];
    private byte[][] currentBoard;
    private byte[][] nextBoard;


    public Board() {

        newBoard();
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

        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        gc.strokeRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        try {
            for (int y = 0; y < HEIGHT; y++) {
                try {
                    for (int x = 0; x < WIDTH; x++) {
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
     * A load function that simply creates new arrays for the live board, and the next board on which new values in each generation is put in.
     */
    public void newBoard() {

        currentBoard = new byte[HEIGHT][WIDTH];
        nextBoard = new byte[HEIGHT][WIDTH];
    }

    /**
     * Loops through every cell and counts the amount of live neighbor cells in each direction.
     * The next status of each cell is put in the nextBoard array, and what this status should be is based on the rules currently in use in the GoL class.
     * At the end of the loops, the nextBoard is set to be the new currentBoard. This is done in order to avoid mix of data between the old, and the new state of the board, which would result in false patterns.
     */
    public void nextGeneration() {

        try {
            for (int y = 0; y < HEIGHT; y++) {
                try {
                    for (int x = 0; x < WIDTH; x++) {

                        //Counts the neighbors of the particular cell
                        int neighbors = 0;
                        int aliveStatus = 0;

                        //Checks the status of the cell, whether it is alive or dead.
                        //Tries and catches ArrayOutOfBoundsExceptions which may occur, like fex. if you
                        //base your position from currentBoard[0[0] and you try to find x-1 which will result in currentBoard[-1][0] which is out of bounds.
                        //Nothing happens if exception is caught.

                        try {
                            if (currentBoard[y][x] == 1) {
                                aliveStatus = 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                        try {
                            if (currentBoard[y][x] == 0) {
                                aliveStatus = 0;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                        try {
                            if (currentBoard[y - 1][x] == 1) {
                                neighbors++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                        try {
                            if (currentBoard[y][x - 1] == 1) {
                                neighbors++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }


                        try {
                            if (currentBoard[y - 1][x - 1] == 1) {
                                neighbors++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                        try {
                            if (currentBoard[y + 1][x] == 1) {
                                neighbors++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                        try {
                            if (currentBoard[y][x + 1] == 1) {
                                neighbors++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                        try {
                            if (currentBoard[y + 1][x + 1] == 1) {
                                neighbors++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                        try {
                            if (currentBoard[y - 1][x + 1] == 1) {
                                neighbors++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }


                        try {
                            if (currentBoard[y + 1][x - 1] == 1) {
                                neighbors++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }


                        //Returns a value to a temporary nextboard based on the rules method in the GoL class.
                        nextBoard[y][x] = GoL.rules(neighbors, aliveStatus);

                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    //
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //
        }

        try {
            for (int y = 0; y < HEIGHT; y++) {
                try {
                    for (int x = 0; x < WIDTH; x++) {
                        currentBoard[y][x] = nextBoard[y][x];

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //
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
            double ycellsInFrame = boardCanvas.getHeight() / GoL.getCellSize();
            double xcellsInFrame = boardCanvas.getWidth() / GoL.getCellSize();

            double cellPosX = posX / (boardCanvas.getWidth() / xcellsInFrame);
            double cellPosY = posY / (boardCanvas.getHeight() / ycellsInFrame);

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
                //TODO maybe only redraw clicked cell
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
            double ycellsInFrame = boardCanvas.getHeight() / GoL.getCellSize();
            double xcellsInFrame = boardCanvas.getWidth() / GoL.getCellSize();

            double cellPosX = posX / (boardCanvas.getWidth() / xcellsInFrame);
            double cellPosY = posY / (boardCanvas.getHeight() / ycellsInFrame);

            int cellX = (int) cellPosX;
            int cellY = (int) cellPosY;

            if (visitedCellWithDrag[0] == cellX && visitedCellWithDrag[1] == cellY) {
                //Do nothing
            } else {

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


        if (newCellAmountHeight > HEIGHT) {


            try {
                for (int y = 0; y < HEIGHT; y++) {
                    try {
                        for (int x = 0; x < WIDTH; x++) {

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
                for (int i = 1; i < ((newCellAmountHeight - HEIGHT) + 1); i++) {

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

            HEIGHT = newCellAmountHeight;
            WIDTH = newCellAmountWidth;
            currentBoard = newBoard;
            nextBoard = newBoard;

        }
    }


    public byte[][] getCurrentBoard() {

        return currentBoard;
    }


    public void setCurrentBoard(byte[][] newBoard) {

        currentBoard = newBoard;
    }


    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int newWidth) {
        this.WIDTH = newWidth;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int newHeight) {
        this.HEIGHT = newHeight;
    }

    /**
     * Converts the byte array currentBoard into a string of consecutive "1"s and "0"s.
     * @return the string made from currentBoard
     */
    @Override
    public String toString() {

        StringBuilder boardStringBuilder = new StringBuilder();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                byte b = currentBoard[y][x];
                boardStringBuilder.append(b);
            }
        }
        String boardString = new String(boardStringBuilder);
        return boardString;
    }
}
