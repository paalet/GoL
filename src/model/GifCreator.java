package model;

import java.io.IOException;

/**
 * Class handling logic related to the creation of GIFs.
 */
public class GifCreator {

    private static int milliSecPerGen;
    private static int cellSize;
    private static int imageHeight;
    private static int imageWidth;
    private static String path;
    private static java.awt.Color aliveCellColor;
    private static java.awt.Color deadCellColor;

    /**
     * Creates a GIF with use of a lieng.GIFWriter object. Done by drawing images and inserting them in a recursive manner.
     * @param gwriter the writer object that handles drawing and inserting images to GIF stream
     * @param gifBoard copy of the current live game board
     * @param genCount number of generations to portray in GIF
     * @throws IOException if lieng.GIFWriter operations fail
     */
    public static void writeGif(lieng.GIFWriter gwriter, Board gifBoard, int genCount) throws IOException {

        if (genCount == 0) {

            drawGifImage(gwriter, gifBoard);
            gwriter.insertCurrentImage();
            gwriter.close();

        } else {

            drawGifImage(gwriter, gifBoard);
            gwriter.insertAndProceed();
            gifBoard.nextGeneration();
            writeGif(gwriter, gifBoard, genCount - 1);
        }
    }

    /**
     * Draw image representing the board with lieng.GIFWriter object.
     * @param gwriter the writer object that handles drawing
     * @param gifBoard copy of the current live game board
     */
    private static void drawGifImage(lieng.GIFWriter gwriter, Board gifBoard) {

        // Loop through a static board and draw cells with color depending on status
        if (gifBoard instanceof StaticBoard) {
            for (int y = 0; y < ((StaticBoard) gifBoard).getCurrentBoard().length; y++) {

                for (int x = 0; x < ((StaticBoard) gifBoard).getCurrentBoard()[0].length; x++) {

                    if (((StaticBoard) gifBoard).getCurrentBoard()[y][x] == 1) {

                        gwriter.fillRect((x * cellSize) + 1, ((x + 1) * cellSize) - 1, (y * cellSize) + 1, ((y + 1) * cellSize) - 1, aliveCellColor);

                    } else {

                        gwriter.fillRect((x * cellSize) + 1, ((x + 1) * cellSize) - 1, (y * cellSize) + 1, ((y + 1) * cellSize) - 1, deadCellColor);
                    }
                }
            }

        // Loop through a dynamic board and draw cells with color depending on status
        } else if (gifBoard instanceof DynamicBoard) {

            for (int y = 0; y < gifBoard.getHeight(); y++) {

                for (int x = 0; x < gifBoard.getWidth(); x++) {

                    if (((DynamicBoard) gifBoard).getCurrentBoard().get(y).get(x) == 1) {

                        gwriter.fillRect(x * cellSize, ((x + 1) * cellSize) - 2, y * cellSize, ((y + 1) * cellSize) - 2, aliveCellColor);

                    } else {

                        gwriter.fillRect(x * cellSize, ((x + 1) * cellSize) - 2, y * cellSize, ((y + 1) * cellSize) - 2, deadCellColor);
                    }
                }
            }
        }

    }

    /**
     * Sets cellsize and image dimensions for preview of GIF.
     * @param gifBoard copy of the current live game board
     * @param dim which dimension to base calculations on
     * @param inputSize height or width of the preview picture
     */
    public static void setImageSize(Board gifBoard, String dim, int inputSize) {

        if (gifBoard instanceof StaticBoard) {

            // Calculate image and cell sizes from height input
            if (dim == "height") {

                cellSize = inputSize / ((StaticBoard) gifBoard).getCurrentBoard().length;
                imageHeight = cellSize * ((StaticBoard) gifBoard).getCurrentBoard().length;
                imageWidth = cellSize * ((StaticBoard) gifBoard).getCurrentBoard()[0].length;

            // Calculate image and cell sizes from width input
            } else if (dim == "width") {

                cellSize = inputSize / ((StaticBoard) gifBoard).getCurrentBoard()[0].length;
                imageWidth = cellSize * ((StaticBoard) gifBoard).getCurrentBoard()[0].length;
                imageHeight = cellSize * ((StaticBoard) gifBoard).getCurrentBoard().length;
            }

        } else if (gifBoard instanceof DynamicBoard) {

            // Calculate image and cell sizes from height input
            if (dim == "height") {

                cellSize = inputSize / ((DynamicBoard) gifBoard).getCurrentBoard().size();
                imageHeight = cellSize * ((DynamicBoard) gifBoard).getCurrentBoard().size();
                imageWidth = cellSize * ((DynamicBoard) gifBoard).getCurrentBoard().get(0).size();

                // Calculate image and cell sizes from width input
            } else if (dim == "width") {

                cellSize = inputSize / ((DynamicBoard) gifBoard).getCurrentBoard().get(0).size();
                imageWidth = cellSize * ((DynamicBoard) gifBoard).getCurrentBoard().get(0).size();
                imageHeight = cellSize * ((DynamicBoard) gifBoard).getCurrentBoard().size();
            }
        }
    }

    /**
     * Converts genPerSec to milliseconds to work with lieng.GIFWriter before applying it to milliSecPerGen.
     * @param genPerSec the number of generations per second input in the GUI
     */
    public static void calculateAndSetMilliSecondsPerGen(int genPerSec) {

        milliSecPerGen = 1000 / genPerSec;
    }

    public static void setAliveCellColor(java.awt.Color aliveCellColor) {
        GifCreator.aliveCellColor = aliveCellColor;
    }

    public static void setDeadCellColor(java.awt.Color deadCellColor) {
        GifCreator.deadCellColor = deadCellColor;
    }

    public static int getMilliSecPerGen() {
        return milliSecPerGen;
    }

    public static int getImageHeight() {
        return imageHeight;
    }

    public static int getImageWidth() {
        return imageWidth;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        GifCreator.path = path;
    }
}
