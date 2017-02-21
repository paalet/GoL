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
                            ,{0,1,1,1,0,0,0,0}
                            ,{0,0,0,0,0,0,0,0}
                            ,{0,0,0,0,0,0,0,0}};

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
}
