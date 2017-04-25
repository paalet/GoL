package model;


public class GifCreator {

    private static String path;
    private static int timePerMilliSecond;
    private static int cellSize;
    private static int imageWidth;
    private static int imageHeight;
    private static java.awt.Color aliveCellColor;
    private static java.awt.Color deadCellColor;

    public static void writeGif(lieng.GIFWriter gwriter, Board gifBoard, int genCounter) throws Exception {

        if (genCounter == 0) {

            drawGifImage(gwriter, gifBoard);
            gwriter.insertCurrentImage();
            gwriter.close();

        } else {

            drawGifImage(gwriter, gifBoard);
            gwriter.insertAndProceed();
            gifBoard.nextGeneration();
            writeGif(gwriter, gifBoard, genCounter - 1);
        }
    }


    private static void drawGifImage(lieng.GIFWriter gwriter, Board gifBoard) {

        if (gifBoard instanceof StaticBoard) {
            for (int y = 0; y < ((StaticBoard) gifBoard).getCurrentBoard().length; y++) {

                for (int x = 0; x < ((StaticBoard) gifBoard).getCurrentBoard()[0].length; x++) {

                    if (((StaticBoard) gifBoard).getCurrentBoard()[y][x] == 1) {

                        gwriter.fillRect(x * cellSize, ((x + 1) * cellSize) - 3, y * cellSize, ((y + 1) * cellSize) - 3, aliveCellColor);

                    } else {

                        gwriter.fillRect(x * cellSize, ((x + 1) * cellSize) - 3, y * cellSize, ((y + 1) * cellSize) - 3, deadCellColor);
                    }
                }
            }
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


    public static void calculateImageSize(Board gifBoard) {

        if (gifBoard instanceof StaticBoard) {

            imageHeight = ((StaticBoard) gifBoard).getCurrentBoard().length * cellSize;
            imageWidth = ((StaticBoard) gifBoard).getCurrentBoard()[0].length * cellSize;

        } else if (gifBoard instanceof DynamicBoard) {

            imageHeight = ((DynamicBoard) gifBoard).getCurrentBoard().size() * cellSize;
            imageWidth = ((DynamicBoard) gifBoard).getCurrentBoard().get(0).size() * cellSize;
        }
    }


    public static String getPath() {
        return path;
    }

    public static int getTimePerMilliSecond() {
        return timePerMilliSecond;
    }

    public static int getCellSize() {
        return cellSize;
    }

    public static int getImageWidth() {
        return imageWidth;
    }

    public static int getImageHeight() {
        return imageHeight;
    }

    public static void setPath(String path) {
        GifCreator.path = path;
    }

    public static void setTimePerMilliSecond(int timePerMilliSecond) {
        GifCreator.timePerMilliSecond = timePerMilliSecond;
    }

    public static void setCellSize(int cellSize) {
        GifCreator.cellSize = cellSize;
    }

    public static void setAliveCellColor(java.awt.Color aliveCellColor) {
        GifCreator.aliveCellColor = aliveCellColor;
    }

    public static void setDeadCellColor(java.awt.Color deadCellColor) {
        GifCreator.deadCellColor = deadCellColor;
    }
}
