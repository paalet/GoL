package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Object;
import java.awt.Graphics;

/**
 * Created by Pål on 09.02.2017.
 */
public class StaticBoard extends Board{

    public byte [][] nextBoard =
            {{1,0,0,1,0,0,1,1}
            ,{0,1,1,0,0,1,0,1}
            ,{0,1,1,0,1,0,1,0}
            ,{1,0,0,1,0,1,0,1}
            ,{1,0,0,1,0,1,0,1}
            ,{1,0,0,1,0,1,0,1}
            ,{1,0,0,1,0,1,0,1}
            ,{1,0,0,1,0,1,0,1}};

    public byte[][] board = {{0,0,0,0,0,0,0,0}
                            ,{0,0,0,0,0,1,1,0}
                            ,{0,0,0,0,0,1,1,0}
                            ,{0,0,0,0,0,0,0,0}
                            ,{0,0,0,0,0,0,0,0}
                            ,{0,1,1,1,0,0,1,0}
                            ,{0,0,0,0,0,0,1,1}
                            ,{0,0,0,0,0,1,1,0}};

    @Override
    public void draw(GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor){
        gc.clearRect(0, 0, 450, 450);
        gc.strokeRect(0,0, 450, 450);
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j] == 1) {
                    gc.setFill(aliveCellColor);
                    gc.fillRect((j*size), (i*size), size, size);
                    gc.strokeRect((j*size), (i*size), size, size);
                }
                else
                {
                    gc.setFill(deadCellColor);
                    gc.fillRect((j*size), (i*size), size, size);
                    gc.strokeRect((j*size), (i*size), size, size);
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

                //Checks the status of the cell, whether ist alive or dead.
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
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = nextBoard[i][j];

            }
        }

    }
}
