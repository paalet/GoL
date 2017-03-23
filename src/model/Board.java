package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class Board {

    private int WIDTH = 10;

    private int HEIGHT = 10;

    private int [] visitedCellWithDrag = new int[2];


    private byte[][] currentBoard;

    private byte[][] nextBoard;


    public void draw(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor){

        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        gc.strokeRect(0,0, boardCanvas.getWidth(), boardCanvas.getHeight());
        for (int y = 0; y < currentBoard.length; y++){
            for (int x = 0; x < currentBoard.length; x++){
                if (currentBoard[y][x] == 1) {
                    gc.setFill(aliveCellColor);
                    gc.fillRect((x*size), (y*size), size, size);
                    gc.strokeRect((x*size), (y*size), size, size);
                }
                else
                {
                    gc.setFill(deadCellColor);
                    gc.fillRect((x*size), (y*size), size, size);
                    gc.strokeRect((x*size), (y*size), size, size);
                }
            }
        }
    }

    public void newBoard() {
        byte[][] currentBoard = new byte[WIDTH][HEIGHT];

        byte[][] nextBoard = new byte[WIDTH][HEIGHT];
        this.currentBoard = currentBoard;
        this.nextBoard = nextBoard;

    }

    public void nextGeneration() {

        for (int x = 0; x < currentBoard.length; x++) {
            for (int y = 0; y < currentBoard.length; y++) {

                //Counts the neighbors of the particular cell
                int neighbors = 0;
                int aliveStatus = 0;

                //Checks the status of the cell, whether it is alive or dead.
                //Tries and catches ArrayOutOfBoundsExceptions which may occur, like fex. if you
                //base your position from currentBoard[0[0] and you try to find x-1 which will result in currentBoard[-1][0] which is out of bounds.
                //Nothing happens if exception is caught.

                try  {
                    if (currentBoard[x][y] == 1) {
                        aliveStatus = 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (currentBoard[x][y] == 0) {
                        aliveStatus = 0;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (currentBoard[x - 1][y] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (currentBoard[x][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                try  {
                    if (currentBoard[x - 1][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (currentBoard[x + 1][y] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (currentBoard[x][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (currentBoard[x+1][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (currentBoard[x - 1][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                try  {
                    if (currentBoard[x + 1][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                //Returns a value to a temporary nextboard based on the rules method in the GoL class.
                nextBoard[x][y] = GoL.rules(neighbors, aliveStatus);

            }

        }
        for (int x = 0; x < currentBoard.length; x++) {
            for (int y = 0; y < currentBoard.length; y++) {
                currentBoard[y][x] = nextBoard[y][x];

            }
        }

    }

    public void cellClickDraw(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) {

        // Calculate target cell from mouse position
        double posX = event.getX();
        double posY = event.getY();

        double cellPosX = posX/(boardCanvas.getWidth()/ currentBoard.length);
        double cellPosY = posY/(boardCanvas.getHeight()/ currentBoard.length);

        int cellX = (int) cellPosX;
        int cellY = (int) cellPosY;

        if(visitedCellWithDrag[0] == cellX && visitedCellWithDrag[1] == cellY) {
            visitedCellWithDrag[0] = 999999999;
            visitedCellWithDrag[1] = 999999999;
        }
        else {
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

    public void cellDragDraw(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) {
        double posX = event.getX();
        double posY = event.getY();

        double cellPosX = posX/(boardCanvas.getWidth()/ currentBoard.length);
        double cellPosY = posY/(boardCanvas.getHeight()/ currentBoard.length);

        int cellX = (int) cellPosX;
        int cellY = (int) cellPosY;

        if(visitedCellWithDrag[0] == cellX && visitedCellWithDrag[1] == cellY) {
            //Do nothing
        }
        else {

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

    public void calculateBoardSize(double canvasSize) {
        double cellAmountDouble = Math.ceil(canvasSize / GoL.getCellSize());
        int cellAmount = (int) cellAmountDouble;
        byte[][] newBoard = new byte[cellAmount][cellAmount];

        if(newBoard.length > currentBoard.length) {



            for (int x = 0; x < currentBoard.length; x++) {
                for (int y = 0; y < currentBoard.length; y++) {

                    newBoard[x][y] = currentBoard[x][y];
                }
            }

            for (int i = 1; i < ((newBoard.length - currentBoard.length) + 1); i++) {

                for (int x = 0; x < newBoard.length; x++) {
                    newBoard[newBoard.length - i][x] = 0;
                    newBoard[x][newBoard.length - i] = 0;

                }
            }


            currentBoard = newBoard;
            nextBoard = newBoard;
        }
    }


    public byte[][] getCurrentBoard() {

        return currentBoard;
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


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < currentBoard.length; y++) {
            for (int x = 0; x < currentBoard.length; x++) {
                byte b = currentBoard[y][x];
                sb.append(b);
            }
        }
        String boardString = new String(sb);
        return boardString;
    }


 /*   public void setBoard(byte[][] newBoard) {

        currentBoard = newBoard;
    }
 */   public void setTestBoard(byte[][] testBoard) {

        currentBoard = testBoard;
    }

}
