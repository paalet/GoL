package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * Class handling logic related to the contents, size and drawing of the game board. This class is based on collections to represent
 * the game board and is built to automatically increase in size if there are live cells at the edges to allow for growth beyond
 * the set size.
 */
public class DynamicBoard extends Board {

    private int width = 12;
    private int height = 8;
    private int[] visitedCellWithDrag;
    private ArrayList<ArrayList<Byte>> currentBoard;
    private ArrayList<ArrayList<Byte>> nextBoard;
    private Color white = Color.valueOf("ffffff");

    /**
     * Constructor sets initial board size and ensures correct size of currentBoard and nextBoard through newBoard().
     */
    public DynamicBoard() {

        width = 12;
        height = 8;
        visitedCellWithDrag = new int[2];
        newBoard();
    }

    /**
     * Constructor for creation of a deep copy of a DynamicBoard object. To be used when operations must be made on
     * a board object without changing the state of the original.
     * @param inputBoard DynamicBoard object to be deep copied
     */
    public DynamicBoard(DynamicBoard inputBoard) {

        width = inputBoard.width;
        height = inputBoard.height;
        visitedCellWithDrag = new int[2];
        newBoard();
        // Copy the currentBoard arraylist
        for (int y = 0; y < inputBoard.currentBoard.size(); y++) {

            for (int x = 0; x < inputBoard.currentBoard.get(y).size(); x++) {

                currentBoard.get(y).set(x, inputBoard.currentBoard.get(y).get(x));
            }
        }
    }

    /**
     * Creates new lists for currentBoard and nextBoard.
     */
    public void newBoard() {

        currentBoard = new ArrayList<>();
        nextBoard = new ArrayList<>();
        for (int y = 0; y < height; y++) {

            currentBoard.add(new ArrayList<>());
            nextBoard.add(new ArrayList<>());
            for (int x = 0; x < width; x++) {

                currentBoard.get(y).add(x, (byte) 0);
                nextBoard.get(y).add(x, (byte) 0);

            }
        }
    }

    /**
     * A draw function that loops through every cell of the board and draws a square of the appropriate color at the cells'
     * position on the canvas. Gap between squares is reduced the smaller the cell size to improve visual quality.
     * @param boardCanvas the canvas to draw onto
     * @param gc GraphicsContext of the canvas
     * @param size size of each cell
     * @param aliveCellColor color used to draw live cells
     * @param deadCellColor color used to draw dead cells
     * @param gridColor color used to draw edge around canvas
     */
    public void draw(Canvas boardCanvas, GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, Color gridColor) {

        double gapSize = 1;

        if (size < 4) {

            gapSize = 0;

        } else if (size < 10) {

            gapSize = .5;
        }

        // Clear canvas
        gc.setFill(white);
        gc.fillRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());

        // Fill board with deadCellColor
        gc.setFill(deadCellColor);
        gc.fillRect(0, 0,width * size, height * size);

        // Draw boarder around the board
        gc.setStroke(gridColor);
        gc.strokeRect(0, 0, width * size, height * size);

        // Draw live cells
        gc.setFill(aliveCellColor);
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                if (currentBoard.get(y).get(x) == 1) {

                    gc.fillRect((x * size) + (gapSize / 2), (y * size) + (gapSize / 2), size - gapSize, size - gapSize);
                }
            }
        }
    }

    /**
     * Draws a cell grid on the canvas. This method is only called when the game is paused to provide visual aid when user is drawing on the
     * game board or analysing the pattern. Grid line width is reduced the smaller the cell size to correspond with draw().
     * @param gc the GraphicsContent to draw onto the canvas with
     * @param size size of each cell
     * @param gridColor color used to draw the grid
     */
    public void drawGrid(GraphicsContext gc, double size, Color gridColor) {

        gc.setStroke(gridColor);

        // Reduce grid line thickness with smaller cell size
        if (size < 7) {

            gc.setLineWidth(.1);

        } else if (size < 10) {

            gc.setLineWidth(.2);

        } else if (size < 20) {

            gc.setLineWidth(.4);

        } else {

            gc.setLineWidth(.8);
        }

        // Draw grid
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                gc.strokeRect(x * size, y * size, size, size);
            }
        }
    }

    /**
     * Expands the board arraylist if there are live cells at the edge of the board. This is done by first iterating through
     * the board to find which sides have live cells and thus need to be expanded. Then rows are added to the board, and
     * cell values are moved if needed, to set a complete row of dead cells at the correct edges of the board.
     * @return
     */
    public boolean autoBoardExpansion() {

        boolean expOccurred = false;

        // Check for live cells at the edge of the board
        boolean upperEdge = false;
        boolean rightEdge = false;
        boolean lowerEdge = false;
        boolean leftEdge = false;

        for (int x = 0; x < currentBoard.get(0).size(); x++) {

            // Check the northern edge for live cells
            if (currentBoard.get(0).get(x) == 1) {

                upperEdge = true;
            }

            // Check the lower edge for live cells
            if (currentBoard.get(currentBoard.size() - 1).get(x) == 1) {

                lowerEdge = true;
            }
        }
        for (int y = 0; y < currentBoard.size(); y++) {

            // Check the left edge for live cells
            if (currentBoard.get(y).get(0) == 1) {

                leftEdge = true;
            }

            // Check the right edge for live cells
            if (currentBoard.get(y).get(currentBoard.get(y).size() - 1) == 1) {

                rightEdge = true;
            }
        }

        // Expand the board side for side if live cells have been found found at the edge of the board
        if (leftEdge && width < 1500) {

            for (int y = 0; y < currentBoard.size(); y++) {

                currentBoard.get(y).add((byte )0);
                nextBoard.get(y).add((byte) 0);

                for (int x = currentBoard.get(y).size() - 1; x > 0; x--) {

                    currentBoard.get(y).set(x, currentBoard.get(y).get(x - 1));
                }
                currentBoard.get(y).set(0, (byte) 0);
            }
            width++;
            expOccurred = true;
        }

        if (upperEdge && height < 1500) {

            currentBoard.add( new ArrayList<>());
            nextBoard.add( new ArrayList<>());
            for (int x = 0; x < currentBoard.get(0).size(); x++) {
                currentBoard.get(currentBoard.size() - 1).add(x, (byte) 0);
                nextBoard.get(nextBoard.size() - 1).add(x, (byte) 0);
            }
            for (int y = currentBoard.size() - 1; y > 0; y--) {

                for (int x = 0; x < currentBoard.get(y).size(); x++) {

                    currentBoard.get(y).set(x, currentBoard.get(y - 1).get(x));
                }
            }
            for (int  x = 0; x < currentBoard.get(0).size(); x++) {

                currentBoard.get(0).set(x, (byte) 0);
            }
            height++;
            expOccurred = true;
        }

        if (rightEdge && width < 1500) {

            for (int y = 0; y < currentBoard.size(); y++) {

                currentBoard.get(y).add((byte) 0);
                nextBoard.get(y).add((byte) 0);
            }
            width++;
            expOccurred = true;
        }
        if (lowerEdge && height < 1500) {

            currentBoard.add(new ArrayList<>());
            nextBoard.add(new ArrayList<>());
            for (int x = 0; x < currentBoard.get(0).size(); x++) {

                currentBoard.get(currentBoard.size() - 1).add(x, (byte) 0);
                nextBoard.get(currentBoard.size() - 1).add(x, (byte) 0);
            }
            height++;
            expOccurred = true;
        }
        return expOccurred;
    }
    /**
     * Loops through every cell and counts the amount of live neighbor cells in each direction.
     * The next status of each cell is put in the nextBoard array, and what this status should be is based on the rules currently in use in the GoL class.
     * At the end of the loops, the nextBoard is set to be the new currentBoard. This is done in order to avoid mix of data between the old, and the new state of the board, which would result in false patterns.
     */


    public void nextGeneration() {

        //Check the status of each cell of the board, whether it is alive or dead.
        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                if (currentBoard.get(y).get(x) == 1) {
                    aliveStatus = 1;
                } else if (currentBoard.get(y).get(x) == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < currentBoard.size()) && (x - 1 >= 0 && x - 1 < currentBoard.get(y - 1).size())) {
                    if (currentBoard.get(y - 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (y - 1 >= 0 && y - 1 < currentBoard.size()) {
                    if (currentBoard.get(y - 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentBoard.size()) && (x + 1 >= 0 && x + 1 < currentBoard.get(y - 1).size())) {
                    if (currentBoard.get(y - 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < currentBoard.get(y).size()) {
                    if (currentBoard.get(y).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < currentBoard.get(y).size()) {
                    if (currentBoard.get(y).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.size()) && (x - 1 >= 0 && x - 1 < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (y + 1 >= 0 && y + 1 < currentBoard.size() && (x >= 0 && x < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.size()) && (x + 1 >= 0 && x + 1 < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                byte nextStatus = GoL.rules(neighbors, aliveStatus);
                nextBoard.get(y).set(x, nextStatus);
            }

        }

        // Update currentBoard with values from nextBoard
        for (int y = 0; y < currentBoard.size(); y++) {

            for (int x = 0; x < currentBoard.get(y).size(); x++) {

                currentBoard.get(y).set(x, nextBoard.get(y).get(x));
            }
        }
    }

    /**
     * Similar method to nextGeneration, only adjusted for compatibility with thread seperation.
     * Splits up the board in x number of parts, each handled by a seperate thread, which results in a nextBoard arraylist which are copied to the currentBoard and later drawn with draw()
     * The rest of the cells which may not add up in a divition by the amount of threads(one for each core in the machine) is handled by a modulo calculation which are added to the last thread.
     * @param cores Represents the amount of cores in the active computers CPU. Used to calculate how many rows of cells each core/thread shall handle.
     * @param core Represents the thread with the corresponding core number calling the method. Used to decide what part of the board the active core/thread shall be allocated.
     */

    public void nextGenerationConcurrent(int cores, int core) {


        int widthPerCore = width / cores;
        int modulo = width % cores;

        int startWidth;
        int endWidth;
        // Make a number of threads equal to the modulo increase their workload by one column
        if (core <= modulo) {

            startWidth = (core - 1) * (widthPerCore + 1);
            endWidth = startWidth + widthPerCore + 1;

        // Set the workload for the remaining threads
        } else {

            startWidth = ((core - 1) * widthPerCore) + modulo;
            endWidth = startWidth + widthPerCore;
        }

        //Check the status of each cell of the board, whether it is alive or dead.
        for (int y = 0; y < height; y++) {

            for (int x = startWidth; x < endWidth; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                if (currentBoard.get(y).get(x) == 1) {
                    aliveStatus = 1;
                } else if (currentBoard.get(y).get(x) == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < currentBoard.size()) && (x - 1 >= 0 && x - 1 < currentBoard.get(y - 1).size())) {
                    if (currentBoard.get(y - 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (y - 1 >= 0 && y - 1 < currentBoard.size()) {
                    if (currentBoard.get(y - 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < currentBoard.size()) && (x + 1 >= 0 && x + 1 < currentBoard.get(y - 1).size())) {
                    if (currentBoard.get(y - 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < currentBoard.get(y).size()) {
                    if (currentBoard.get(y).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < currentBoard.get(y).size()) {
                    if (currentBoard.get(y).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.size()) && (x - 1 >= 0 && x - 1 < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (y + 1 >= 0 && y + 1 < currentBoard.size() && (x >= 0 && x < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < currentBoard.size()) && (x + 1 >= 0 && x + 1 < currentBoard.get(y + 1).size())) {
                    if (currentBoard.get(y + 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                byte nextStatus = GoL.rules(neighbors, aliveStatus);
                nextBoard.get(y).set(x, nextStatus);
            }

        }

    }

    /**
     * Copy the values from nextBoard to currentBoard. This moves the game to the next generation.
     */
    public void updateCurrentFromNextBoard() {

        for (int y = 0; y < currentBoard.size(); y++) {

            for (int x = 0; x < currentBoard.get(y).size(); x++) {

                currentBoard.get(y).set(x, nextBoard.get(y).get(x));
            }
        }
    }

    /**
     * Expand or shrink the board to fit new dimensions given in DimensionInputDialog.
     * @param board the current live game board
     * @param newHeight the target height of the board
     * @param newWidth the target width of the board
     */
    public void setBoardSizeToDimensions(Board board, int newHeight, int newWidth) {

        // Adjust board height
        if (newHeight < height) {

            for (int y = height - 1; y >= newHeight; y--) {

                currentBoard.remove(y);
            }
            height = newHeight;

        } else if (newHeight > height) {

            for (int y = height; y < newHeight; y++) {

                currentBoard.add(new ArrayList<>());
                nextBoard.add(new ArrayList<>());
                for (int x = 0; x < width; x++) {

                    currentBoard.get(y).add((byte) 0);
                    nextBoard.get(y).add((byte) 0);
                }
            }
            height = newHeight;
        }

        // Adjust board width
        if (newWidth < width) {

            for (int y = 0; y < height; y++) {

                for (int x = width - 1; x >= newWidth; x--) {

                    currentBoard.get(y).remove(x);
                    nextBoard.get(y).remove(x);
                }
            }
            width = newWidth;

        } else if (newWidth > width) {

            for (int y = 0; y < height; y++) {

                for (int x = width; x < newWidth; x++) {

                    currentBoard.get(y).add((byte) 0);
                    nextBoard.get(y).add((byte) 0);
                }
            }
        }
        width = newWidth;
    }

    /**
     * Adds new rows to the board array if the new cell sizes set makes the board not fully cover the canvas, in order to auto-fill the board.
     * @param canvasHeight height of canvas
     * @param canvasWidth width of canvas
     */
    public void calculateBoardSize(double canvasHeight, double canvasWidth) {

        double cellAmountDoubleWidth = Math.ceil(canvasWidth / GoL.getCellSize());
        int newCellAmountWidth = (int) cellAmountDoubleWidth;
        double roundedWidth = ((double) newCellAmountWidth * GoL.getCellSize());
        double cellAmountDoubleHeight = Math.ceil(canvasHeight / GoL.getCellSize());
        int newCellAmountHeight = (int) cellAmountDoubleHeight;

        // Expand board to fit new size
        if (newCellAmountWidth > width || newCellAmountHeight > height) {

            for (int y = 0; y < newCellAmountHeight; y++) {

                if (currentBoard.size() == y) {

                    currentBoard.add(new ArrayList<>());
                    nextBoard.add(new ArrayList<>());
                }
                for (int x = currentBoard.get(y).size(); x < newCellAmountWidth; x++) {

                    currentBoard.get(y).add(x, (byte) 0);
                    nextBoard.get(y).add(x, (byte) 0);
                }

            }

            height = newCellAmountHeight;
            width = newCellAmountWidth;
        }
    }

    /**
     * Change the alive status of each cell, and give this cell its new color based on this status.
     * Gets the coordinates of the click, calculates which cell is located in this coordinate based on the part of the board currently
     * visible on the canvas. visitedCellWithDrag is used in order to avoid giving the cell a new status whenever the mouse drag is exited,
     * and therefore this mouse release event is activated.
     * @param event of the mouse click on canvas
     * @param gc GraphicsContext of the canvas to draw with
     * @param boardCanvas the canvas clicked
     */
    public void cellClick(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) {

        try {
            // Calculate target cell from mouse position
            double posX = event.getX();
            double posY = event.getY();
            double yCellsInFrame = boardCanvas.getHeight() / GoL.getCellSize();
            double xCellsInFrame = boardCanvas.getWidth() / GoL.getCellSize();

            double cellPosX = posX / (boardCanvas.getWidth() / xCellsInFrame);
            double cellPosY = posY / (boardCanvas.getHeight() / yCellsInFrame);

            int cellX = (int) cellPosX;
            int cellY = (int) cellPosY;

            if (visitedCellWithDrag[0] == cellX && visitedCellWithDrag[1] == cellY) {
                visitedCellWithDrag[0] = 999999999;
                visitedCellWithDrag[1] = 999999999;
            } else {
                // Change cell status
                if (currentBoard.get(cellY).get(cellX) == 1) {

                    currentBoard.get(cellY).set(cellX, (byte) 0);
                } else {

                    currentBoard.get(cellY).set(cellX, (byte) 1);
                }

            }
        } catch (IndexOutOfBoundsException e) {

            // Do nothing as a click on the the edge of the canvas is harmless
        }
    }

    /**
     * Functionally the same as cellClick. visitedCellWithDrag is an array which contains the board coordinates of the
     * last visited cell, in order to avoid multiple re-calculations of the cell currently targeted by the mouse pointer.
     * @param event of the mouse click and drag on canvas
     * @param gc GraphicsContext of the canvas to draw with
     * @param boardCanvas the canvas clicked
     */
    public void cellDrag(MouseEvent event, GraphicsContext gc, Canvas boardCanvas) throws ArrayIndexOutOfBoundsException {

        // Calculate target cell from mouse position
        try {
            double posX = event.getX();
            double posY = event.getY();
            double yCellsInFrame = boardCanvas.getHeight() / GoL.getCellSize();
            double xCellsInFrame = boardCanvas.getWidth() / GoL.getCellSize();

            double cellPosX = posX / (boardCanvas.getWidth() / xCellsInFrame);
            double cellPosY = posY / (boardCanvas.getHeight() / yCellsInFrame);

            int cellX = (int) cellPosX;
            int cellY = (int) cellPosY;

            if (!(visitedCellWithDrag[0] == cellX && visitedCellWithDrag[1] == cellY)) {

                if (currentBoard.get(cellY).get(cellX) == 1) {

                    currentBoard.get(cellY).set(cellX, (byte) 0);
                } else {

                    currentBoard.get(cellY).set(cellX, (byte) 1);
                }

                visitedCellWithDrag[0] = cellX;
                visitedCellWithDrag[1] = cellY;
            }
        }
        catch(IndexOutOfBoundsException e) {

            //Do nothing as a mouse drag outside the canvas is harmless
        }
    }

    /**
     * Sets a new currentBoard from arraylistinput. Expands currentBoard if it is of smaller size than input.
     * @param newBoard input arraylist to copy values from
     */
    public void setCurrentBoard(ArrayList<ArrayList<Byte>> newBoard){

        for (int y = 0; y < newBoard.size(); y++) {

            if (currentBoard.size() == y) {

                currentBoard.add(new ArrayList<>());
            }
            for (int x = 0; x < newBoard.get(y).size(); x++) {

                if (currentBoard.get(y).size() == x) {

                    currentBoard.get(y).add(x, newBoard.get(y).get(x));
                } else {

                    currentBoard.get(y).set(x, newBoard.get(y).get(x));
                }
            }
        }
    }

    public ArrayList<ArrayList<Byte>> getCurrentBoard() {
        return currentBoard;
    }

    public int getWidth() {return width;}

    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }
}