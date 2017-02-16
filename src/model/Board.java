package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Pål on 09.02.2017.
 */
public abstract class Board {

    public abstract void draw(GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor);



}
