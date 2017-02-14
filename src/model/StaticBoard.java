package model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Pål on 09.02.2017.
 */
public class StaticBoard extends Board{


    public byte[][] board = {{1,0,0,1,0,0,1,1}
                            ,{0,1,1,0,0,1,0,1}
                            ,{0,1,1,0,1,0,1,0}
                            ,{1,0,0,1,0,1,0,1}
                            ,{1,0,0,1,0,1,0,1}
                            ,{1,0,0,1,0,1,0,1}
                            ,{1,0,0,1,0,1,0,1}
                            ,{1,0,0,1,0,1,0,1}};

    @Override
    public void draw(GraphicsContext gc, double size){
        gc.clearRect(0, 0, 440, 500);
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j] == 1) {
                    gc.fillRect((i*size), (j*size), size, size);
                }
            }
        }
    }
}
