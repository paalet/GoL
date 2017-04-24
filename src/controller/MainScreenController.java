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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    private Pane pane;
    @FXML
    private Button mainMenuButton;
    @FXML
    public TextArea gameMessagesText;
    @FXML
    private Button staticBoardLoadButton;
    @FXML
    private Button autoExpandBoardLoadButton;
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

    private Board board = new StaticBoard();
    private String boardType;
    private GraphicsContext gc;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            board.nextGeneration();
            draw();
        }
    }));


    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        // Initialise game values
        GoL.setIsRunning(false);
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2, 3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        GoL.setCellSize(boardCanvas.getHeight() / board.getHeight());
        boardCanvas.setWidth(calculateCanvasWidth(board.getWidth()));
        GoL.setAliveCellColor(Color.valueOf("0x344c50ff"));
        GoL.setDeadCellColor(Color.valueOf("0xe1effdff"));
        GoL.setCurrRate(5.0);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(GoL.getCurrRate());

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

    public void initializeStaticBoard() {

        board = new StaticBoard();
        boardType = "Static";
    }

    public void initializeDynamicBoard() {
        board = new DynamicBoard();
        boardType = "Dynamic";

    }

    public void returnToMenuEvent() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/menu.fxml"));
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        Pane root = loader.load();
        Scene scene = new Scene(root, 1045, 868);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * A simple function that calles the draw function in the Board class.
     */
    private void draw() {

        board.draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor());
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
     * Creates and displays the GIF creator interface and pauses the game if it is running.
     */
    public void gifCreatorEvent() {

        if (GoL.getIsRunning()) {
            pause();
        }

        // Create GIF Stage
        Stage gifStage = new Stage();
        FXMLLoader gifLoader = new FXMLLoader(getClass().getResource("../view/gifCreator.fxml"));
        GifCreatorController gifController = new GifCreatorController(board);
        gifLoader.setController(gifController);
        try {
            Parent gifRoot = gifLoader.load();
            Scene gifScene = new Scene(gifRoot);
            gifStage.setScene(gifScene);
        } catch (IOException e) {
            System.out.println("gifLoader.load() did not produce Parent");
            e.printStackTrace();
        }

        gifStage.setTitle("Create GIF");
        gifStage.initModality(Modality.APPLICATION_MODAL);
        gifStage.showAndWait();
    }

    /**
     * Calculates a cellsize and snaps it, and the slider to the nearest possible value that would fit in the canvas perfectly based on the horizontal size.
     */
    public void setCellSizeEvent() {
        gameMessagesText.setText("");
        if(boardType.equals("Dynamic")) {
            GoL.calculateCellSize(board.getHeight(), board.getWidth(), boardCanvas.getHeight(), boardCanvas.getWidth(), cellSizeSlider, gameMessagesText);
            calculateBoardSize(656, 985);
        }
        else {
            GoL.setCellSize(cellSizeSlider.getValue());
        }

        try {
            boardCanvas.setWidth(GoL.getCellSize() * (double) board.getWidth());
        } catch (RuntimeException er) {
            gameMessagesText.setText("ERROR: Game crashed due to either lack of\n memory or exceeding canvas size limit.\nPlease alert the developers.");
        }
        try {
            boardCanvas.setHeight(GoL.getCellSize() * (double) board.getHeight());
        } catch (RuntimeException er) {
            gameMessagesText.setText("ERROR: Game crashed due to either lack of\nmemory or exceeding canvas size limit.\nPlease alert the developers.");
        }

        draw();


    }

    private void calculateBoardSize(double canvasHeight, double canvasWidth) {

        board.calculateBoardSize(canvasHeight, canvasWidth);

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
        timeline.pause();

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
        timeline.pause();

        board.cellClickDraw(event, gc, boardCanvas);
    }

    /**
     * Functionally the same as cellClickEvent, only with the possibility of dragging across mutiple cells to kill/give birth to cells quickly.
     * @param event
     */
    public void boardDragEvent(MouseEvent event) {

        board.cellDragDraw(event, gc, boardCanvas);
    }

    /**
     * Opens
     */
    public void openRulesEditor() {
        pause();

        RulesEditor rulesEditor = RulesEditor.getInstance();
        rulesEditor.setVisible(true);
        //TODO make listener that notices rulesEditor closing and calls displayRules
        displayRules();
    }


    public void readFileFromDisk() throws IOException {
        pause();

        File rleFile = FileManagement.loadFileFromDisk();
        if ( rleFile != null) {

            HashMap<String, String> fileData = FileManagement.readFile(new FileReader(rleFile));
            applyFileData(fileData);
            confirmFileData(fileData);
        }
    }


    public void readFileFromURL() throws Exception {
        pause();

        InputStream rleStream = FileManagement.loadFileFromURL();
        if (rleStream != null) {

            HashMap<String, String> fileData = FileManagement.readFile(new InputStreamReader(rleStream));
            applyFileData(fileData);
            confirmFileData(fileData);
        }
    }

    public void openFileEditorWindow() throws Exception{

        pause();


        Stage fileEditor = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/previewPattern.fxml"));
        Pane root = loader.load();

        Scene scene = new Scene(root, 900, 700);

        scene.getStylesheets().add("../view/previewPatternStyles.css");

        fileEditor.setScene(scene);
        fileEditor.setTitle("Preview pattern");
        fileEditor.setResizable(false);
        fileEditor.initModality(Modality.WINDOW_MODAL);

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
            bornStringBuilder.append(i);
        }
        String bornString = new String(bornStringBuilder);

        StringBuilder surviveStringBuilder = new StringBuilder();
        for(int i : GoL.getSurviveAmount()) {
            surviveStringBuilder.append(i);
        }

        String surviveString = new String(surviveStringBuilder);
        System.out.println(bornString);
        System.out.println(surviveString);

        FileEditor edController = loader.getController();
        edController.setTitleArea(title);
        edController.setOriginArea(origin);
        edController.setCommentsArea(comments);
        edController.setBornAmountField(bornString);
        edController.setSurviveAmountField(surviveString);
        switch(boardType) {
            case "Static": edController.drawAllCanvasesStatic(board.getWidth(), board.getHeight(),board.getCurrentBoard(), board.getHeight(), board.getWidth());
                break;
            case "Dynamic": edController.drawAllCanvasesDynamic(board.getWidth(), board.getHeight(), board.getCurrentBoard(), board.getHeight(), board.getWidth());
                break;
            default: System.out.println("ERROR: Could not find board-type.");
                break;

        }



        fileEditor.show();

    }


    public void setGameMessagesText(String text) {
        gameMessagesText.setText(text);
        System.out.println(text);
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
        board.setWidth(width);
        board.setHeight(height);
        board.newBoard();

        // Apply rules if there are readable rules, if not, re-apply standard rules.
        if(FileManagement.readRules(fileData.get("rules")) != null) {
            int rules[][] = FileManagement.readRules(fileData.get("rules"));
            GoL.setBornAmount(rules[0]);
            GoL.setSurviveAmount(rules[1]);
            displayRules();
        }
        else {
            int[] initBornAmount = {3};
            int[] initSurviveAmount = {2, 3};
            GoL.setBornAmount(initBornAmount);
            GoL.setSurviveAmount(initSurviveAmount);
        }

        // Apply pattern
        if (board instanceof StaticBoard) {

            ((StaticBoard) board).setCurrentBoard(FileManagement.readPatternStaticBoard(fileData.get("pattern"), height, width));
        } else if (board instanceof DynamicBoard) {

            ((DynamicBoard) board).setCurrentBoard(FileManagement.readPatternDynamicBoard(fileData.get("pattern"), height, width));
        }
        calculateCellSizeOnPatternLoad();
        boardCanvas.setWidth(calculateCanvasWidth(board.getWidth()));
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

        if(titleOk) {
            report.append("<html><body><p style='text-align:center'>Name found.<br><br>");
        }
        else {
            report.append("<html><body><p style='text-align:center'>Name not found.<br>To display a name, please begin the line where you want the title with '#N'.<br><br>");
        }
        if(originOk) {
            report.append("Origin found.<br><br>");
        }
        else {
            report.append("Origin not found.<br>To display an origin, please begin the line where you want the origin with '#O'.<br><br>");
        }
        if(commentsOk) {
            report.append("Comments found.<br><br>");
        }
        else {
            report.append("Comments not found.<br>NTo display comments, please begin each line of comment with '#C'.<br><br>");
        }
        if(rulesOk) {
            report.append("Rules found and set.<br><br>");
        }
        else {
            report.append("Rules not found, or of an invalid format. Standard rule-set applied.<br>To implement rules, please use the following syntax:<br>rules = B{value}/S{value}<br><br>'rule' instead of 'rules' and the use of lower-case letters representing the born/survive amount is also supported.<br><br>");
        }
        if(dimensionsOk) {
            report.append("Dimensions found and set.<br><br>");
        }
        else {
            report.append("Dimensions not found.<br>To implement a set of board-dimensions, please use the following syntax:<br><br>x = {value}, y = {value}<br>");
        }
        if(patternOk) {
            report.append("Pattern found and set.<br><br></p></body></html>");
        }
        else {
            report.append("Pattern not found, or of invalid format.<br>To learn how to implement a pattern readable in an RLE-file, please visit http://www.conwaylife.com/wiki/RLE.<br><br></p></body></html>");
        }

        String reportString = new String(report);

        CustomDialog importInfo = new CustomDialog("File load", true, reportString);
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
        int boardHeightInt = board.getHeight();
        double boardHeightDouble = (double) boardHeightInt;
        GoL.setCellSize(canvasHeightDouble / boardHeightDouble);
        cellSizeSlider.setValue(GoL.getCellSize());
    }


}

