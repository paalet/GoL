package controller;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.*;
import java.util.HashMap;


/**
 * Controller class that interacts with the view and calls logic from classes in the model package.
 */
public class MainScreenController implements Initializable {

    @FXML
    private Canvas boardCanvas;
    @FXML
    private Button playButton;
    @FXML
    private Slider cellSizeSlider;
    @FXML
    private ColorPicker aliveCellColorPicker;
    @FXML
    private ColorPicker deadCellColorPicker;
    @FXML
    private Label fpsLabel;
    @FXML
    private Label rulesLabel;
    @FXML
    private Button saveFileButton;
    @FXML
    private Button openFileButton;
    @FXML
    private TextArea titleText;
    @FXML
    private TextArea originText;
    @FXML
    private TextArea commentText;
    @FXML
    private TextArea saveWindowTitleArea;
    @FXML
    private TextArea saveWindowOriginArea;
    @FXML
    private TextArea saveWindowCommentsArea;
    @FXML
    private TextArea saveWindowBornAmountArea;
    @FXML
    private TextArea saveWindowSurviveAmountArea;

    private DynamicBoard staticBoard = new DynamicBoard();
    private GraphicsContext gc;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            staticBoard.nextGeneration();
            draw();
        }
    }));
    private Stage gifStage = new Stage();


    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        // Initialise game values
        GoL.setIsRunning(false);
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2, 3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        GoL.setCellSize(boardCanvas.getHeight() / staticBoard.getHeight());
        boardCanvas.setWidth(calculateCanvasWidth(staticBoard.getWidth()));
        GoL.setAliveCellColor(Color.valueOf("0x344c50ff"));
        GoL.setDeadCellColor(Color.valueOf("0xe1effdff"));
        GoL.setCurrRate(5.0);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(GoL.getCurrRate());

        // Create GIF Stage
        FXMLLoader gifLoader = new FXMLLoader(getClass().getResource("../view/gifCreator.fxml"));
        try {
            Parent gifRoot = gifLoader.load();
            Scene gifScene = new Scene(gifRoot);
            gifStage.setScene(gifScene);
        } catch (IOException e) {
            System.out.println("gifLoader.load() did not produce Parent");
        }
        GifCreatorController gifController = gifLoader.getController();
        gifStage.setTitle("Create GIF");
        gifStage.initModality(Modality.APPLICATION_MODAL);

        // Display game values
        aliveCellColorPicker.setValue(GoL.getAliveCellColor());
        deadCellColorPicker.setValue(GoL.getDeadCellColor());
        cellSizeSlider.setValue(GoL.getCellSize());
        fpsLabel.setText(GoL.getCurrRate() + " gen/s");
        displayRules();

        //Draw board
        gc = boardCanvas.getGraphicsContext2D();
        draw();
    }

    /**
     * A simple function that calles the draw function in the Board class.
     */
    private void draw() {

        staticBoard.draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
    }

    /**
     * A function used to calculate the appropriate canvas size in case the dimensions of the board changes.
     * Used to avoid empty spaces on the canvas, which looks ugly, and can result in some nasty exceptions.
     * @param boardWidth
     * @return
     */
    public double calculateCanvasWidth(int boardWidth) {
        double boardWidthDouble = (double) boardWidth;
        return GoL.getCellSize() * boardWidthDouble;
    }

    /**
     * An event listener that gets called upon pressing start. Sets the animation into play if the animation isnt running, pauses otherwise.
     */
    public void playEvent() {

        if (!GoL.getIsRunning()) {

            play();
        } else {

            pause();
        }
    }

    /**
     * Resumes timeline.
     */
    private void play() {

        timeline.setRate(GoL.getCurrRate());
        timeline.play();
        GoL.setIsRunning(true);
        playButton.setText("Pause");
    }


    private void pause() {

        timeline.pause();
        GoL.setIsRunning(false);
        playButton.setText("Resume");
    }

    /**
     * Shows the GIF creator interface and pauses the game if it is running.
     */
    public void gifCreatorEvent() {

        if (GoL.getIsRunning()) {
            pause();
        }
        // gifController.setStatic/DynamicBoard som gjør deep copy via constructor med brett som input
        gifStage.showAndWait();
    }

    /**
     * Calculates a cellsize and snaps it, and the slider to the nearest possible value that would fit in the canvas perfectly based on the horizontal size.
     */
    public void setCellSizeEvent() {
        GoL.calculateCellSize(boardCanvas.getHeight(), boardCanvas.getWidth(), cellSizeSlider);
        calculateBoardSize(boardCanvas.getHeight(), boardCanvas.getWidth());
        draw();


    }

    private void calculateBoardSize(double canvasHeight, double canvasWidth) {

        staticBoard.calculateBoardSize(canvasHeight, canvasWidth);

    }


    public void setAliveCellColorEvent() {

        GoL.setAliveCellColor(aliveCellColorPicker.getValue());
        draw();
    }


    public void setDeadCellColorEvent() {

        GoL.setDeadCellColor(deadCellColorPicker.getValue());
        draw();
    }


    public void decreaseSpeedEvent() {

        if (GoL.getIsRunning()) {

            if (GoL.getCurrRate() > 0.6) {

                GoL.setCurrRate(GoL.getCurrRate() - 0.5);
                timeline.setRate(GoL.getCurrRate());
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        } else {

            if (GoL.getCurrRate() > 0.6) {

                GoL.setCurrRate(GoL.getCurrRate() - 0.5);
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        }
    }

    /**
     * Increases speed incrementally with 0.5 fps, and sets the label to display the new value.
     */
    public void increaseSpeedEvent() {

        if (GoL.getIsRunning()) {

            if (GoL.getCurrRate() < 20.0) {

                GoL.setCurrRate(GoL.getCurrRate() + 0.5);
                timeline.setRate(GoL.getCurrRate());
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        } else {

            if (GoL.getCurrRate() < 20.0) {

                GoL.setCurrRate(GoL.getCurrRate() + 0.5);
                fpsLabel.setText(GoL.getCurrRate() + " gen/s");
            }
        }
    }

    /**
     * Kills or gives birth to a cell on a simple click.
     * @param event
     */
    public void cellClickEvent(MouseEvent event) {

        staticBoard.cellClickDraw(event, gc, boardCanvas);
    }

    /**
     * Functionally the same as cellClickEvent, only with the possibility of dragging across mutiple cells to kill/give birth to cells quickly.
     * @param event
     */
    public void boardDragEvent(MouseEvent event) {

        staticBoard.cellDragDraw(event, gc, boardCanvas);
    }

    /**
     * Opens
     */
    public void openRulesEditor() {


        RulesEditor rulesEditor = RulesEditor.getInstance();
        rulesEditor.setVisible(true);
        //TODO make listener that notices rulesEditor closing and calls displayRules
        displayRules();
    }


    public void readFileFromDisk() throws IOException {

        File rleFile = FileManagement.loadFileFromDisk();
        if ( rleFile != null) {

            HashMap<String, String> fileData = FileManagement.readFile(new FileReader(rleFile));
            applyFileData(fileData);
            confirmFileData(fileData);
        }
    }


    public void readFileFromURL() throws IOException {

        InputStream rleStream = FileManagement.loadFileFromURL();
        if (rleStream != null) {

            HashMap<String, String> fileData = FileManagement.readFile(new InputStreamReader(rleStream));
            applyFileData(fileData);
            confirmFileData(fileData);
        }
    }

    public void openFileEditorWindow() throws Exception{


        Stage fileEditor = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/saveFile.fxml"));

        Scene scene = new Scene(root, 267, 504);

        fileEditor.setScene(scene);
        fileEditor.setTitle("Edit and save file");
        fileEditor.show();

        String title = "";
        String origin = "";
        String comments = "";

        if(!titleText.getText().equals("")) {
            title = titleText.getText();
        }
        if(!originText.getText().equals("")) {
            origin = originText.getText();
        }
        if(!commentText.getText().equals("")) {
            comments = commentText.getText();
        }

        StringBuilder bornStringBuilder = new StringBuilder();
        for(int i : GoL.getBornAmount()) {
            char data = (char) i;
            bornStringBuilder.append(data);
        }
        String bornString = new String(bornStringBuilder);

        StringBuilder surviveStringBuilder = new StringBuilder();
        for(int i : GoL.getSurviveAmount()) {
            char data = (char) i;
            surviveStringBuilder.append(data);
        }

        String surviveString = new String(surviveStringBuilder);

        saveWindowTitleArea.setText(title);
        saveWindowOriginArea.setText(origin);
        saveWindowCommentsArea.setText(comments);
        saveWindowBornAmountArea.setText(bornString);
        saveWindowSurviveAmountArea.setText(surviveString);


    }

    public void openFileEditor() throws IOException {

        Stage editor = new Stage();
        /*HashMap<String, String> fileData = new HashMap<>();

        //Inserts title if there is a title
        fileData.put("title", titleText.getText());

        //Inserts origin
        fileData.put("origin", originText.getText());

        //Inserts comments
        fileData.put("comment", commentText.getText());

        //Inserts height of board
        fileData.put("boardHeight", staticBoard.getHeight());

        //Inserts width of board
        fileData.put("boardWidth", staticBoard.getWidth());
        */





    }

    /**
     * Applies data obtained from the readFile method in FileManagement into the running program, such as title, origin, comments, board dimensions, rules, and the board.
     * @param fileData
     * @throws IOException
     */
    private void applyFileData(HashMap<String, String> fileData) throws IOException {

        // Show metadata in GUI
        titleText.setText(fileData.get("title"));
        originText.setText(fileData.get("origin"));
        commentText.setText(fileData.get("comments"));

        // Apply board size
        int width = FileManagement.readDimension(fileData.get("width"));
        int height = FileManagement.readDimension(fileData.get("height"));
        staticBoard.setWidth(width);
        staticBoard.setHeight(height);
        staticBoard.newBoard();

        // Apply rules if there are readable rules
        if(FileManagement.readRules(fileData.get("rules")) != null) {
            int rules[][] = FileManagement.readRules(fileData.get("rules"));
            GoL.setBornAmount(rules[0]);
            GoL.setSurviveAmount(rules[1]);
            displayRules();
        }

        // Apply pattern
       // staticBoard.setCurrentBoard(FileManagement.readPattern(fileData.get("pattern"), width, height));
        calculateCellSizeOnPatternLoad();
        draw();

        //Set data as loaded data
        GoL.setLoadedData(fileData);
    }

    public void confirmFileData(HashMap<String, String> fileData) {
        boolean titleOk = false;
        boolean originOk = false;
        boolean commentsOk = false;
        boolean dimensionsOk = false;
        boolean rulesOk = false;
        boolean patternOk = false;
        if(fileData.get("title") != null) {
            titleOk = true;
        }
        if(fileData.get("origin") != null) {
            originOk = true;
        }
        if(fileData.get("comments") != null) {
            commentsOk = true;
        }
        if(fileData.get("width") != null && fileData.get("height") != null) {
            dimensionsOk = true;
        }
        if(fileData.get("rules") != null) {
            if(FileManagement.readRules(fileData.get("rules")) != null) {
                rulesOk = true;
            }
        }
        if(fileData.get("pattern") != null) {
            patternOk = true;
        }

        StringBuilder report = new StringBuilder();
        int linesInReport = 0;

        if(titleOk) {
            report.append("Name found.\n\n");
            linesInReport = linesInReport + 2;
        }
        else {
            report.append("Name not found.\nTo display a name, please begin the line where you want the title with '#N'.\n\n");
            linesInReport = linesInReport + 3;
        }
        if(originOk) {
            report.append("Origin found.\n\n");
            linesInReport = linesInReport + 2;
        }
        else {
            report.append("Origin not found.\nTo display an origin, please begin the line where you want the origin with '#O'.\n\n");
            linesInReport = linesInReport + 3;
        }
        if(commentsOk) {
            report.append("Comments found.\n\n");
            linesInReport = linesInReport + 2;
        }
        else {
            report.append("Comments not found.\nNTo display comments, please begin each line of comment with '#C'.\n\n");
            linesInReport = linesInReport + 3;
        }
        if(rulesOk) {
            report.append("Rules found and set.\n\n");
            linesInReport = linesInReport + 2;
        }
        else {
            report.append("Rules not found, or of an invalid format.\nTo implement rules, please use the following syntax:\n\nrules = B{value}/S{value}\b\b'rule' instead of 'rules' and the use of lower-case letters representing the born/survive amount is also supported.\b\b");
            linesInReport = linesInReport + 4;
        }
        if(dimensionsOk) {
            report.append("Dimensions found and set.\n\n");
            linesInReport = linesInReport + 2;
        }
        else {
            report.append("Dimensions not found.\nNTo implement a set of board-dimensions, please use the following syntax:\n\nx = {value}, y = {value}\n");
            linesInReport = linesInReport + 3;
        }
        if(patternOk) {
            report.append("Pattern found and set.\n\n");
            linesInReport = linesInReport + 2;
        }
        else {
            report.append("Pattern not found, or of invalid format.\nTo learn how to implement a pattern readable in an RLE-file, please visit http://www.conwaylife.com/wiki/RLE.\n\n");
            linesInReport = linesInReport + 2;
        }

        String reportString = new String(report);
        System.out.print(reportString);


        CustomDialog importInfo = new CustomDialog("File load", true, reportString, 400, linesInReport * 50);
        System.out.println(report);
    }

    /**
     * Builds a String to be set as label to display the rules in use.
     */
    public void displayRules() {

        int[] bornArray = GoL.getBornAmount();
        int[] surviveArray = GoL.getSurviveAmount();
        StringBuilder rulesBuilder = new StringBuilder();
        rulesBuilder.append("B");
        for (int aBornArray : bornArray) {
            rulesBuilder.append(aBornArray);
        }
        rulesBuilder.append("/S");
        for(int aSurviveArray : surviveArray) {
            rulesBuilder.append(aSurviveArray);
        }
        String rulesString = new String(rulesBuilder);
        rulesLabel.setText(rulesString);
    }

    /**
     * Re-evaluates an appropriate cellsize if a new board pattern is loaded.
     */
    public void calculateCellSizeOnPatternLoad() {


        double canvasHeightDouble = boardCanvas.getHeight();
        int boardHeightInt = staticBoard.getHeight();
        double boardHeightDouble = (double) boardHeightInt;
        GoL.setCellSize(canvasHeightDouble / boardHeightDouble);
        cellSizeSlider.setValue(GoL.getCellSize());
    }


}

