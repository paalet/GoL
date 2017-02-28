package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Created by Pål on 09.02.2017.
 */
public abstract class Board {

    private byte [][] currentBoard = {{0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1}};

    private byte [][] nextBoard = {{0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1},
            {0,0,0,1,1,0,0,1}};

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

        // Change cell status
        if (currentBoard[cellY][cellX] == 1) {

            currentBoard[cellY][cellX] = 0;
        }

        else {

            currentBoard[cellY][cellX] = 1;
        }

        // Draw new currentBoard
        //TODO maybe only redraw clicked cell
        draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
    }

    public void calculateBoard() {
        double canvasSize = 450.0;
        double cellAmountDouble = canvasSize / GoL.getCellSize();
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



}
