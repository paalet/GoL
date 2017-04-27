package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Created by simenperschandersen on 27.04.2017.
 */
public class DrawThread implements Runnable {
    private Canvas boardCanvas;
    private GraphicsContext gc;
    private double size;
    private Color aliveCellColor;
    private Color deadCellColor;
    private int cores;
    private int core;
    public Board board;

    public DrawThread(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, Board  board, int core, int cores) {
        this.boardCanvas = boardCanvas;
        this.gc = gc;
        this.size = size;
        this.aliveCellColor = aliveCellColor;
        this.deadCellColor = deadCellColor;
        this.cores = cores;
        this.core = core;
        this.board = board;
    }

    public void run() {

        board.drawConcurrent(boardCanvas, gc, size, aliveCellColor, deadCellColor, core, cores);
    }
}
