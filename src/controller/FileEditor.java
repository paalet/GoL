package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import model.GoL;

import java.util.ArrayList;


/**
 * Created by simenperschandersen on 19.04.2017.
 */
public class FileEditor implements Initializable {
    @FXML
    private TextArea previewTitleArea;
    @FXML
    private TextArea previewOriginArea;
    @FXML
    private TextArea previewCommentsArea;
    @FXML
    private TextField previewBornAmountField;
    @FXML
    private TextField previewSurviveAmountField;

    //Initiates main and all preview canvas and GraphicContexts
    @FXML
    private Canvas mainCanvas;
    @FXML
    private Canvas previewOne;
    @FXML
    private Canvas previewTwo;
    @FXML
    private Canvas previewThree;
    @FXML
    private Canvas previewFour;
    @FXML
    private Canvas previewFive;
    @FXML
    private Canvas previewSix;
    @FXML
    private Canvas previewSeven;
    @FXML
    private Canvas previewEight;
    @FXML
    private Canvas previewNine;
    @FXML
    private Canvas previewTen;
    @FXML
    private Canvas previewEleven;
    @FXML
    private Canvas previewTwelve;
    @FXML
    private Canvas previewThirteen;
    @FXML
    private Canvas previewFourteen;
    @FXML
    private Canvas previewFifteen;


    private GraphicsContext mainGc;
    private GraphicsContext gcOne;
    private GraphicsContext gcTwo;
    private GraphicsContext gcThree;
    private GraphicsContext gcFour;
    private GraphicsContext gcFive;
    private GraphicsContext gcSix;
    private GraphicsContext gcSeven;
    private GraphicsContext gcEight;
    private GraphicsContext gcNine;
    private GraphicsContext gcTen;
    private GraphicsContext gcEleven;
    private GraphicsContext gcTwelve;
    private GraphicsContext gcThirteen;
    private GraphicsContext gcFourteen;
    private GraphicsContext gcFifteen;

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        mainGc = mainCanvas.getGraphicsContext2D();
        gcOne = previewOne.getGraphicsContext2D();
        gcTwo = previewTwo.getGraphicsContext2D();
        gcThree = previewThree.getGraphicsContext2D();
        gcFour = previewFour.getGraphicsContext2D();
        gcFive = previewFive.getGraphicsContext2D();
        gcSix = previewSix.getGraphicsContext2D();
        gcSeven = previewSeven.getGraphicsContext2D();
        gcEight = previewEight.getGraphicsContext2D();
        gcNine = previewNine.getGraphicsContext2D();
        gcTen = previewTen.getGraphicsContext2D();
        gcEleven = previewEleven.getGraphicsContext2D();
        gcTwelve = previewTwelve.getGraphicsContext2D();
        gcThirteen = previewThirteen.getGraphicsContext2D();
        gcFourteen = previewFourteen.getGraphicsContext2D();
        gcFifteen = previewFifteen.getGraphicsContext2D();


    }

    public void setTitleArea(String title) {
        previewTitleArea.setText(title);

    }

    public void setOriginArea(String origin) {
        previewOriginArea.setText(origin);

    }

    public void setCommentsArea(String comments) {
        previewCommentsArea.setText(comments);

    }

    public void setBornAmountField(String bornAmount) {
        previewBornAmountField.setText(bornAmount);

    }

    public void setSurviveAmountField(String surviveAmount) {
        previewSurviveAmountField.setText(surviveAmount);

    }

    public void drawAllCanvasesStatic(byte[][] board, int HEIGHT, int WIDTH) {
        Color aliveCellColor = Color.BLACK;
        Color mainCanvasBackgroundColor = Color.WHITE;
        Color deadCellColor = Color.valueOf("#F4F4F4");
        double cellSizeMainCanvas = calculateCellSize(mainCanvas, HEIGHT);
        double cellSizePreviewCanvas = calculateCellSize(previewOne, HEIGHT);

        drawStatic(mainGc, cellSizeMainCanvas, aliveCellColor, mainCanvasBackgroundColor, board, HEIGHT, WIDTH);

        byte[][] nextBoard = nextGenerationStatic(board, HEIGHT, WIDTH);
        drawStatic(gcOne, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcTwo, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcThree, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcFour, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcFive, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcSix, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcSeven, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcEight, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcNine, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcTen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcEleven, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcTwelve, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcThirteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcFourteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard = nextGenerationStatic(nextBoard, HEIGHT, WIDTH);
        drawStatic(gcFifteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);


    }

    public void drawAllCanvasesDynamic(ArrayList<ArrayList<Byte>> board, int height, int width) {
        Color aliveCellColor = Color.BLACK;
        Color mainCanvasBackgroundColor = Color.WHITE;
        Color deadCellColor = Color.valueOf("#F4F4F4");
        double cellSizeMainCanvas = calculateCellSize(mainCanvas, height);
        double cellSizePreviewCanvas = calculateCellSize(previewOne, height);

        drawDynamic(mainGc, cellSizeMainCanvas, aliveCellColor, mainCanvasBackgroundColor, board, height, width);

        ArrayList<ArrayList<Byte>> nextBoard = nextGenerationDynamic(board, height, width);
        drawDynamic(gcOne, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcTwo, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcThree, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcFour, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcFive, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcSix, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcSeven, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcEight, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcNine, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcTen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcEleven, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcTwelve, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcThirteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcFourteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);

        nextBoard = nextGenerationDynamic(nextBoard, height, width);
        drawDynamic(gcFifteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, height, width);


    }

    public double calculateCellSize(Canvas canvas, int height) {
        double canvasHeight = canvas.getHeight();
        double cellAmountHeight = (double) height;

        return canvasHeight / cellAmountHeight;


    }


    public void drawStatic(GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, byte[][] board, int height, int width) {


        try {
            for (int y = 0; y < height; y++) {
                try {
                    for (int x = 0; x < width; x++) {
                        if (board[y][x] == 1) {
                            gc.setFill(aliveCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                        } else {
                            gc.setFill(deadCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public void drawDynamic(GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, ArrayList<ArrayList<Byte>> board, int height, int width) {


        try {
            for (int y = 0; y < height; y++) {
                try {
                    for (int x = 0; x < width; x++) {
                        if (board.get(y).get(x) == 1) {
                            gc.setFill(aliveCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                        } else {
                            gc.setFill(deadCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public byte[][] nextGenerationStatic(byte[][] evolvingBoard, int height, int width) {
        byte[][] evolvedBoard = new byte[height][width];

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                //Check the status of the cell, whether it is alive or dead.
                if (evolvingBoard[y][x] == 1) {
                    aliveStatus = 1;
                }
                if (evolvingBoard[y][x] == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < evolvingBoard.length) && (x - 1 >= 0 && x - 1 < evolvingBoard[y - 1].length)) {
                    if (evolvingBoard[y - 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < evolvingBoard.length) && (x >= 0 && x < evolvingBoard[y - 1].length)) {
                    if (evolvingBoard[y - 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < evolvingBoard.length) && (x + 1 >= 0 && x + 1 < evolvingBoard[y - 1].length)) {
                    if (evolvingBoard[y - 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < evolvingBoard[y].length) {
                    if (evolvingBoard[y][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < evolvingBoard[y].length) {
                    if (evolvingBoard[y][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < evolvingBoard.length) && (x - 1 >= 0 && x - 1 < evolvingBoard[y + 1].length)) {
                    if (evolvingBoard[y + 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < evolvingBoard.length) && (x >= 0 && x < evolvingBoard[y + 1].length)) {
                    if (evolvingBoard[y + 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < evolvingBoard.length) && (x + 1 >= 0 && x + 1 < evolvingBoard[y + 1].length)) {
                    if (evolvingBoard[y + 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                evolvedBoard[y][x] = GoL.rules(neighbors, aliveStatus);
            }

        }
        return evolvedBoard;

    }

    public ArrayList<ArrayList<Byte>> nextGenerationDynamic(ArrayList<ArrayList<Byte>> evolvingBoard, int height, int width) {

        ArrayList<ArrayList<Byte>> evolvedBoard = new ArrayList<>();

        for (int y = 0; y < height; y++) {

            evolvedBoard.add(new ArrayList<>());
            for (int x = 0; x < width; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                //Check the status of the cell, whether it is alive or dead.
                if (evolvingBoard.get(y).get(x) == 1) {
                    aliveStatus = 1;
                } else if (evolvingBoard.get(y).get(x) == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 < evolvingBoard.size()) && (x - 1 >= 0 && x - 1 < evolvingBoard.get(y - 1).size())) {
                    if (evolvingBoard.get(y - 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < evolvingBoard.size()) && (x >= 0 && x < evolvingBoard.get(y - 1).size())) {
                    if (evolvingBoard.get(y - 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 < evolvingBoard.size()) && (x + 1 >= 0 && x + 1 < evolvingBoard.get(y - 1).size())) {
                    if (evolvingBoard.get(y - 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 < evolvingBoard.get(y).size()) {
                    if (evolvingBoard.get(y).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 < evolvingBoard.get(y).size()) {
                    if (evolvingBoard.get(y).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < evolvingBoard.size()) && (x - 1 >= 0 && x - 1 < evolvingBoard.get(y + 1).size())) {
                    if (evolvingBoard.get(y + 1).get(x - 1) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < evolvingBoard.size()) && (x >= 0 && x < evolvingBoard.get(y + 1).size())) {
                    if (evolvingBoard.get(y + 1).get(x) == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 < evolvingBoard.size()) && (x + 1 >= 0 && x + 1 < evolvingBoard.get(y + 1).size())) {
                    if (evolvingBoard.get(y + 1).get(x + 1) == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                byte nextStatus = GoL.rules(neighbors, aliveStatus);

                evolvedBoard.get(y).add(x, nextStatus);
            }

        }
        return evolvedBoard;

    }
}
