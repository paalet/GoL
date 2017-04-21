package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import model.GoL;


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

    public void drawAllCanvases(byte[][]board, int HEIGHT, int WIDTH) {
        Color aliveCellColor = Color.BLACK;
        Color deadCellColor = Color.WHITE;
        double cellSizeMainCanvas = calculateCellSize(mainCanvas, HEIGHT);
        double cellSizePreviewCanvas = calculateCellSize(previewOne, HEIGHT);

        draw(mainGc, cellSizeMainCanvas, aliveCellColor, deadCellColor, board, HEIGHT, WIDTH);

        byte[][]nextBoard  = nextGeneration(board, HEIGHT, WIDTH);
        draw(gcOne, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcTwo, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcThree, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcFour, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcFive, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcSix, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcSeven, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcEight, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcNine, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcTen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcEleven, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcTwelve, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcThirteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcFourteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);

        nextBoard  = nextGeneration(nextBoard, HEIGHT, WIDTH);
        draw(gcFifteen, cellSizePreviewCanvas, aliveCellColor, deadCellColor, nextBoard, HEIGHT, WIDTH);


    }

    public double calculateCellSize(Canvas canvas, int HEIGHT) {
        double canvasHeight = canvas.getHeight();
        double cellAmountHeight = (double) HEIGHT;

        return canvasHeight/cellAmountHeight;


    }


    public void draw(GraphicsContext gc, double size, Color aliveCellColor, Color deadCellColor, byte[][] board, int HEIGHT, int WIDTH) {

        try {
            for (int y = 0; y < HEIGHT; y++) {
                try {
                    for (int x = 0; x < WIDTH; x++) {
                        if (board[y][x] == 1) {
                            gc.setFill(aliveCellColor);
                            gc.fillRect((x * size), (y * size), size, size);
                        }
                        else
                        {
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

    public byte[][] nextGeneration(byte[][] evolvingBoard, int HEIGHT, int WIDTH) {
        byte[][] evolvedBoard = new byte[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++) {

            for (int x = 0; x < WIDTH; x++) {

                int neighbors = 0;
                int aliveStatus = 0;

                //Check the status of the cell, whether it is alive or dead.
                if (evolvingBoard[y][x] == 1) {
                    aliveStatus = 1;
                }
                if ( evolvingBoard[y][x] == 0) {
                    aliveStatus = 0;
                }

                //Count the number of living neighbors of the particular cell
                if ((y - 1 >= 0 && y - 1 <  evolvingBoard.length) && (x - 1 >= 0 && x - 1 <  evolvingBoard[y].length)) {
                    if ( evolvingBoard[y - 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (y - 1 >= 0 && y - 1 <  evolvingBoard.length) {
                    if ( evolvingBoard[y - 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y - 1 >= 0 && y - 1 <  evolvingBoard.length) && (x + 1 >= 0 && x + 1 <  evolvingBoard[y].length)) {
                    if ( evolvingBoard[y - 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if (x - 1 >= 0 && x - 1 <  evolvingBoard[y].length) {
                    if ( evolvingBoard[y][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (x + 1 >= 0 && x + 1 <  evolvingBoard[y].length) {
                    if ( evolvingBoard[y][x + 1] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 <  evolvingBoard.length) && (x - 1 >= 0 && x - 1 <  evolvingBoard[y].length)) {
                    if ( evolvingBoard[y + 1][x - 1] == 1) {
                        neighbors++;
                    }
                }

                if (y + 1 >= 0 && y + 1 <  evolvingBoard.length) {
                    if ( evolvingBoard[y + 1][x] == 1) {
                        neighbors++;
                    }
                }

                if ((y + 1 >= 0 && y + 1 <  evolvingBoard.length) && (x + 1 >= 0 && x + 1 <  evolvingBoard[y].length)) {
                    if ( evolvingBoard[y + 1][x + 1] == 1) {
                        neighbors++;
                    }
                }

                //Returns a value to a temporary array based on the rules method in the GoL class.
                evolvedBoard[y][x] = GoL.rules(neighbors, aliveStatus);
            }

        }
        return evolvedBoard;

    }


}
