package model;

/**
 *
 */
public class NextGenerationThread implements Runnable {

    public Board board;
    private int core;
    private int cores;

    /**
     * Runnable object class that each thread handles separately.
     * @param core Value representing the core # which handles the thread.
     * @param cores Value representing the amount of cores on the machine.
     * @param board The Board object the thread handles and calulates the nextGenerationConcurrent based on.
     */

    public NextGenerationThread(int core, int cores, Board board) {
        this.board = board;
        this.core = core;
        this.cores = cores;

    }

    /**
     * Runs the runnable, which only does the concurrent next generation method.
     */

    public void run() {

        board.nextGenerationConcurrent(cores, core);

    }
}
