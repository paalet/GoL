package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;



/**
 * Created by Pål on 09.02.2017.
 */
public class StaticBoard extends Board{

    private byte[][] board = {{0,0,0,0,0,0,0,0},
                              {0,0,0,0,0,1,1,0},
                              {0,0,0,0,0,1,1,0},
                              {0,0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0,1},
                              {0,1,1,1,0,0,1,0},
                              {0,0,0,0,0,0,1,1},
                              {0,0,0,0,0,1,1,0}};

    private byte[][] nextBoard = {{0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0}};


    @Override
    public void draw(GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor){

        gc.clearRect(0, 0, 450, 450);
        gc.strokeRect(0,0, 450, 450);
        for (int y = 0; y < board.length; y++){
            for (int x = 0; x < board.length; x++){
                if (board[y][x] == 1) {
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

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {

                //Counts the neighbors of the particular cell
                int neighbors = 0;
                int aliveStatus = 0;

                //Checks the status of the cell, whether it is alive or dead.
                //Tries and catches ArrayOutOfBoundsExceptions which may occur, like fex. if you
                //base your position from board[0[0] and you try to find x-1 which will result in board[-1][0] which is out of bounds.
                //Nothing happens if exception is caught.

                try  {
                    if (board[x][y] == 1) {
                        aliveStatus = 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (board[x][y] == 0) {
                        aliveStatus = 0;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (board[x - 1][y] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (board[x][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                try  {
                    if (board[x - 1][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (board[x + 1][y] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (board[x][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (board[x+1][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }

                try  {
                    if (board[x - 1][y+1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                try  {
                    if (board[x + 1][y-1] == 1) {
                        neighbors = neighbors + 1;
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                }


                //Returns a value to a temporary nextboard based on the rules method in the GoL class.
                nextBoard[x][y] = GoL.rules(neighbors, aliveStatus);

            }

        }
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[y][x] = nextBoard[y][x];

            }
        }

    }

    public void cellClickDraw(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) {

        // Calculate target cell from mouse position
        double posX = event.getX();
        double posY = event.getY();

        double cellPosX = posX/(450.0/board.length);
        double cellPosY = posY/(450.0/board.length);

        int cellX = (int) cellPosX;
        int cellY = (int) cellPosY;

        // Change cell status
        if (board[cellY][cellX] == 1) {
            board[cellY][cellX] = 0;
        }
        else {
            board[cellY][cellX] = 1;
        }

        // Draw new board
        //TODO maybe only redraw clicked cell
        draw(gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
    }
}
