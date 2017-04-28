package model;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.swing.filechooser.FileSystemView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GifCreator {

    private static int milliSecondsPerGen;
    private static int cellSize;
    private static int imageHeight;
    private static int imageWidth;
    private static String path;
    private static java.awt.Color aliveCellColor;
    private static java.awt.Color deadCellColor;



    public static void writeGif(lieng.GIFWriter gwriter, Board gifBoard, int genCount) throws Exception {

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


    private static void drawGifImage(lieng.GIFWriter gwriter, Board gifBoard) {

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


    public static void calculateAndSetMilliSecondsPerGen(int gps) {

        milliSecondsPerGen = 1000 / gps;
    }


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


    public static boolean inputPathFromFileChooser(boolean ok) {

        FileChooser pathChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif");
                pathChooser.getExtensionFilters().add(extFilter);
                pathChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        try {
            path = pathChooser.showSaveDialog(null).getPath();
        } catch(NullPointerException e) {
            new CustomDialog("GIF not created", true,
                    "GIF creation canceled. Choose a filename and target directory to create GIF.");
            ok = false;
        }
        return ok;
    }


    public static void setAliveCellColor(java.awt.Color aliveCellColor) {
        GifCreator.aliveCellColor = aliveCellColor;
    }

    public static void setDeadCellColor(java.awt.Color deadCellColor) {
        GifCreator.deadCellColor = deadCellColor;
    }

    public static int getMilliSecondsPerGen() {
        return milliSecondsPerGen;
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
}
