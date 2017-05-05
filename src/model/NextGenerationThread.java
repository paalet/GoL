package model;

/**
 *
 */
public class NextGenerationThread implements Runnable {

    public Board board;
    private int core;
    private int cores;

    public NextGenerationThread(int core, int cores, Board board) {
        this.board = board;
        this.core = core;
        this.cores = cores;

    }

    public void run() {

        board.nextGenerationConcurrent(cores, core);

    }
}
