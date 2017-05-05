package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Controller class for mainScreen.fxml. This class interacts with multiple logic classes and controllers for other views.
 */
public class MainScreenController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button mainMenuButton;
    @FXML
    private TextArea gameMessagesText;
    @FXML
    private Button staticBoardLoadButton;
    @FXML
    private Button autoExpandBoardLoadButton;
    @FXML
    public Canvas boardCanvas;
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
    private CheckBox autoFillCheckBox;
    @FXML
    private Label loadedDimensionsLabel;
    @FXML
    public Label currentDimensionsLabel;
    @FXML
    private Label boardTypeLabel;

    private Board board = new StaticBoard();
    private String boardType;
    private GraphicsContext gc;
    private int cores =  Runtime.getRuntime().availableProcessors();

    /**
     * Timeline allows evolution of the board and animation at a set time interval. This object is manipulated to start
     * and pause animation and to change its rate.
     */
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            if (board instanceof DynamicBoard) {
                boolean expansionOccurred = ((DynamicBoard) board).autoBoardExpansion();
                if (expansionOccurred) {
                    calculateCellSize();
                    boardCanvas.setWidth(calculateCanvasWidth(board.getWidth()));
                    displayDimensions();
                }
            }

            long start = System.currentTimeMillis();
            ExecutorService executor = Executors.newFixedThreadPool(cores);

            NextGenerationThread [] nextGenThreads = new NextGenerationThread[cores];

            for (int i = 0; i < cores; i++) {
                nextGenThreads[i] = new NextGenerationThread(i + 1, cores, board);
            }

            for (NextGenerationThread nextGenThread : nextGenThreads) {
                executor.execute(nextGenThread);
            }
            executor.shutdown();
            boolean finished = false;
            try{
                finished = executor.awaitTermination(500, TimeUnit.MILLISECONDS);
            }
            catch(InterruptedException e) {
                gameMessagesText.setText("Generation wait time exceeded. Board is not being refreshed.\nPlease alert the developers of this error.");
            }

            if(finished) {
                board.updateCurrentFromNextBoard();
                draw();
            }
            
        }
    }));


    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        // Initialise game values
        GoL.setIsRunning(false);

        LinkedList<Byte> initBirthRules = new LinkedList<>();
        initBirthRules.add((byte) 3);
        LinkedList<Byte> initSurvivalRules = new LinkedList<>();
        initSurvivalRules.add((byte) 2);
        initSurvivalRules.add((byte) 3);
        GoL.setBirthRules(initBirthRules);
        GoL.setSurvivalRules(initSurvivalRules);

        GoL.setCellSize(boardCanvas.getHeight() / board.getHeight());
        boardCanvas.setWidth(calculateCanvasWidth(board.getWidth()));

        GoL.setAliveCellColor(Color.valueOf("0x344c50ff"));
        GoL.setDeadCellColor(Color.valueOf("0xe1effdff"));
        GoL.setGridColor(Color.valueOf("000000"));

        GoL.setCurrRate(5.0);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(GoL.getCurrRate());

        gameMessagesText.setText("");
        titleText.setText("");
        originText.setText("");
        commentText.setText("");

        // Display game values
        aliveCellColorPicker.setValue(GoL.getAliveCellColor());
        deadCellColorPicker.setValue(GoL.getDeadCellColor());
        cellSizeSlider.setValue(GoL.getCellSize());
        displayDimensions();
        fpsLabel.setText(GoL.getCurrRate() + " gen/s");
        displayRules();

        //Draw board
        gc = boardCanvas.getGraphicsContext2D();
        draw();
    }

    /**
     * Create a new instance of StaticBoard.
     */
    void initializeStaticBoard() {

        board = new StaticBoard();
        boardType = "Static";
        autoFillCheckBox.setSelected(false);
        boardTypeLabel.setText("Board type: Static Board");
    }

    /**
     * Create a new instance of DynamicBoard.
     */
    void initializeDynamicBoard() {
        board = new DynamicBoard();
        boardType = "Dynamic";
        autoFillCheckBox.setSelected(false);
        boardTypeLabel.setText("Board type: Dynamic Board");

    }

    /**
     * Set menu.fxml as the scene of primaryStage. This allows the user to go back and choose a different board type.
     * @throws IOException if fxml can not be loaded
     */
    public void returnToMenuEvent() throws IOException {
        GoL.setLoadedData(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        Pane root = loader.load();
        Scene scene = new Scene(root, 1299, 872);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Call the draw function in the Board class.
     */
    private void draw() {

        board.draw(boardCanvas, gc, GoL.getCellSize(), GoL.getAliveCellColor(), GoL.getDeadCellColor(), GoL.getGridColor());
        if (!GoL.getIsRunning() && GoL.getCellSize() >= 4) {

            board.drawGrid(gc, GoL.getCellSize(), GoL.getGridColor());
        }
    }

    /**
     * A function used to calculate the appropriate canvas size in case the dimensions of the board changes.
     * Used to avoid empty spaces on the canvas.
     * @param boardWidth number of columns in the current game board
     * @return the canvas width that fits the size of the current board and cells
     */
    private double calculateCanvasWidth(int boardWidth) {
        double boardWidthDouble = (double) boardWidth;
        return GoL.getCellSize() * boardWidthDouble;

    }

    /**
     * An event listener that gets called upon pressing start. Starts the animation if it isn't running, pauses otherwise.
     */
    public void playEvent() {

        if (!GoL.getIsRunning()) {

            play();

        } else {

            pause();
        }
    }

    /**
     * Start timeline.
     */
    private void play() {

        timeline.setRate(GoL.getCurrRate());
        timeline.play();
        GoL.setIsRunning(true);
        playButton.setText("Pause");
    }

    /**
     * Stop timeline.
     */
    private void pause() {

        timeline.pause();
        GoL.setIsRunning(false);
        playButton.setText("Resume");
        if (GoL.getCellSize() >= 4) {

            board.drawGrid(gc, GoL.getCellSize(), GoL.getGridColor());
        }
    }

    /**
     * Set the board back to a previous state. If a file is loaded the board data in fileData will be alllied. If no file
     * is loaded a fresh board is set.
     * @throws IOException if fileData can not be applied
     */
    public void resetEvent() throws IOException {

        if (GoL.getIsRunning()) {

            pause();
        }
        // Reset to loaded file
        if(GoL.getLoadedData() != null) {

            HashMap<String, String> fileData = GoL.getLoadedData();
            applyFileData(fileData);

        } else {

            // Reset to fresh board
            board.setHeight(8);
            board.setWidth(12);
            board.newBoard();
            boardCanvas.setHeight(656.0);
            boardCanvas.setWidth(986.0);
            GoL.setCellSize(boardCanvas.getHeight() / board.getHeight());
            cellSizeSlider.setValue(GoL.getCellSize());
            displayDimensions();
            draw();
        }
    }

    /**
     * Creates a stage and displays the GIF creator interface. Pauses the game if it is running.
     * @throws IOException if fxml fails to load
     */
    public void openGifCreatorEvent() throws IOException{

        if (GoL.getIsRunning()) {
            pause();
        }

        // Create GIF Stage
        Stage gifStage = new Stage();
        FXMLLoader gifLoader = new FXMLLoader(getClass().getResource("/gifCreator.fxml"));
        GifCreatorController gifController = new GifCreatorController(board);
        gifLoader.setController(gifController);

        Parent gifRoot = gifLoader.load();
        Scene gifScene = new Scene(gifRoot);
        gifStage.setScene(gifScene);

        gifStage.setTitle("Create GIF");
        gifStage.initModality(Modality.APPLICATION_MODAL);
        gifStage.showAndWait();
    }

    /**
     * Calculates a cell size and snaps it and the slider to the nearest possible value that would fit
     * in the canvas perfectly based on the horizontal size.
     */
    public void setCellSizeEvent() {

        if (GoL.getIsRunning()) {

            pause();
        }
        gameMessagesText.setText("");

        if(autoFillCheckBox.isSelected()) {
            GoL.calculateCellSizeFromSlider(board.getHeight(), board.getWidth(), boardCanvas.getHeight(), boardCanvas.getWidth(), cellSizeSlider, gameMessagesText);
            board.calculateBoardSize(656, 985);
        }
        else {

            if ((board.getHeight() * cellSizeSlider.getValue()) > 5000) {
                double sliderSize = Math.floor(5000/(double)board.getHeight());
                gameMessagesText.setText("This value will exceed the size limit\nof the canvas.\nPlease choose a lower cell size.");
                GoL.setCellSize(sliderSize);
                cellSizeSlider.setValue(sliderSize);
            } else if ((board.getWidth() * cellSizeSlider.getValue()) > 5000) {
                double sliderSize = Math.floor(5000/(double)board.getWidth());
                gameMessagesText.setText("This value will exceed the size limit\nof the canvas.\nPlease choose a lower cell size.");
                GoL.setCellSize(sliderSize);
                cellSizeSlider.setValue(sliderSize);
            } else {
                GoL.setCellSize(cellSizeSlider.getValue());
            }
        }
        boardCanvas.setWidth(GoL.getCellSize() * (double) board.getWidth());
        boardCanvas.setHeight(GoL.getCellSize() * (double) board.getHeight());

        displayDimensions();
        draw();
    }

    /**
     * Sets GoL.AliveCellColor when a color is picked in aliveCellColorPicker.
     */
    public void setAliveCellColorEvent() {

        GoL.setAliveCellColor(aliveCellColorPicker.getValue());
        draw();
    }

    /**
     * Sets GoL.DeadCellColor when a color is picked in deadCellColorPicker.
     */
    public void setDeadCellColorEvent() {

        GoL.setDeadCellColor(deadCellColorPicker.getValue());
        draw();
    }

    /**
     * Decreases the rate of the timeline. If the game is paused this method only updates the fpsLabel and the currRate variable
     * in GoL and lets the new rate take effect when the game is resumed.
     */
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
     * Increases the rate of the timeline. If the game is paused this method only updates the fpsLabel and the currRate variable
     * in GoL and lets the new rate take effect when the game is resumed.
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
     * Kills or gives birth to a cell when clicked.
     * @param event the event created upon clicking the canvas
     */
    public void cellClickEvent(MouseEvent event) {

        pause();
        board.cellClick(event, gc, boardCanvas);
        draw();
    }

    /**
     * Functionally the same as cellClickEvent, only with the possibility of dragging across multiple cells to kill/give birth to cells quickly.
     * @param event the event created upon click and drag on the canvas
     */
    public void boardDragEvent(MouseEvent event) {

        pause();
        board.cellDrag(event, gc, boardCanvas);
        draw();
    }

    /**
     * Creates a stage and displays the rulesEditor interface. Pauses the game if it is running.
     */
    public void openRulesEditor() throws IOException {

        if(GoL.getIsRunning()) {

            pause();
        }

        // Create Rules Editor Stage
        Stage ruleEdStage = new Stage();
        FXMLLoader ruleEdLoader = new FXMLLoader(getClass().getResource("/rulesEditor.fxml"));

        Parent ruleEdRoot = ruleEdLoader.load();
        Scene ruleEdScene = new Scene(ruleEdRoot);
        ruleEdStage.setScene(ruleEdScene);
        RulesEditorController ruleEdCtrl = ruleEdLoader.getController();

        ruleEdStage.setTitle("Edit rules");
        ruleEdStage.initModality(Modality.APPLICATION_MODAL);

        // Call method for sorting of rules upon closing editor
        ruleEdStage.setOnCloseRequest(we -> ruleEdCtrl.sortRules());

        ruleEdStage.showAndWait();
        displayRules();
    }

    /**
     * Creates an instance of DimensionInputDialog to let the user set the board to a preferred size.
     * @throws InterruptedException if dialog is interrupted
     */
    public void openSetDimensionsWindow() throws InterruptedException {


        JFrame frame = new JFrame("Set Board Size");
        new DimensionsInputDialog(frame, "<html><body><p style='text-align:center'>Enter your desired dimensions.</p></body></html>", board);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        calculateCellSize();
                        draw();
                        setCellSizeEvent();
                    }
                });

            }
        });
    }


    /**
     * Reads and applies data from a local Run-Length Encoding file. The file is located with a FileChooser, read with a FileReader,
     * broken up into separate strings of data, which are then parsed and applied. Finally feedback is given to the user
     * of how successful the operation went.
     * @throws IOException if .rle file can not be read
     */
    public void readFileFromDisk() throws IOException{

        if(GoL.getIsRunning()) {

            pause();
        }

        File rleFile = FileManagement.loadFileFromDisk();
        if ( rleFile != null) {

            String absolutePath = rleFile.getAbsolutePath();
            HashMap<String, String> fileData = FileManagement.readFile(new FileReader(rleFile), absolutePath);
            applyFileData(fileData);
            confirmFileData(fileData);
        }
    }

    /**
     * Reads and applies Run-Length Encoding data from a URL. The URL input from an input dialog, read with an InputStreamReader,
     * broken up into separate strings of data, which are then parsed and applied. Finally feedback is given to the user
     * of how successful the operation went.
     * @throws IOException if input stream can not be read
     */
    public void readFileFromURL() throws IOException {

        if(GoL.getIsRunning()) {

            pause();
        }

        InputStream rleStream = FileManagement.loadFileFromURL();
        if (rleStream != null) {

            HashMap<String, String> fileData = FileManagement.readFile(new InputStreamReader(rleStream), "URL");
            applyFileData(fileData);
            confirmFileData(fileData);
        }
    }

    /**
     *
     * @throws IOException
     */
    public void openFileEditorWindow() throws IOException{

        if(GoL.getIsRunning()) {

            pause();
        }

        Stage fileEditor = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/previewPattern.fxml"));
        Pane root = loader.load();

        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add("/view/previewPatternStyles.css");

        fileEditor.setScene(scene);
        fileEditor.setTitle("Preview pattern");
        fileEditor.setResizable(false);
        fileEditor.initModality(Modality.APPLICATION_MODAL);

        String title = titleText.getText();
        String origin = originText.getText();
        String comments = commentText.getText();

        StringBuilder bornStringBuilder = new StringBuilder();
        for(byte i : GoL.getBirthRules()) {
            bornStringBuilder.append(i);
        }
        String bornString = new String(bornStringBuilder);
        StringBuilder surviveStringBuilder = new StringBuilder();
        for(byte i : GoL.getBirthRules()) {

            surviveStringBuilder.append(i);
        }
        String surviveString = new String(surviveStringBuilder);

        FileEditorController edController = loader.getController();
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
            default: gameMessagesText.setText("");
                break;
        }
        fileEditor.show();
    }


    /**
     * Applies the data in fileData to the game. Such as title, origin, comments, board dimensions, rules, and the pattern on the board.
     * @param fileData hashmap containing strings of data derived from .rle input
     * @throws IOException if fileData can not be read
     */
    void applyFileData(HashMap<String, String> fileData) throws IOException {

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

        // Apply rules if there are viable rules in fileData
        if(FileManagement.readRules(fileData.get("rules")) != null) {
            ArrayList<LinkedList<Byte>> rules = FileManagement.readRules(fileData.get("rules"));
            GoL.setBirthRules(rules.get(0));
            GoL.setSurvivalRules(rules.get(1));
            displayRules();
        }
        else {

            // Set standard rules if input failed to provide viable rules
            LinkedList<Byte> initBirthRules = new LinkedList<>();
            initBirthRules.add((byte) 3);
            LinkedList<Byte> initSurvivalRules = new LinkedList<>();
            initSurvivalRules.add((byte) 2);
            initSurvivalRules.add((byte) 3);
            GoL.setBirthRules(initBirthRules);
            GoL.setSurvivalRules(initSurvivalRules);
            displayRules();
        }

        // Apply pattern
        if (board instanceof StaticBoard) {

            ((StaticBoard) board).setCurrentBoard(FileManagement.readPatternStaticBoard(fileData.get("pattern"), height, width));
        } else if (board instanceof DynamicBoard) {

            ((DynamicBoard) board).setCurrentBoard(FileManagement.readPatternDynamicBoard(fileData.get("pattern"), height, width));
        }
        calculateCellSize();
        boardCanvas.setWidth(calculateCanvasWidth(board.getWidth()));
        draw();

        //Set data as loaded data
        GoL.setLoadedData(fileData);
        displayDimensions();
        loadedDimensionsLabel.setText("Width = " + board.getWidth() + " / Height = " + board.getHeight());
    }

    /**
     * Checks which parts of the Run-Length Encoding file har been implmented and gives feedback to the user in the form of a dialog.
     * @param fileData hashmap containing strings of data derived from .rle input
     */
    private void confirmFileData(HashMap<String, String> fileData) {

        boolean titleOk = false;
        boolean originOk = false;
        boolean commentsOk = false;
        boolean dimensionsOk = false;
        boolean rulesOk = false;
        boolean patternOk = false;

        // Check which parts of data application were successful
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

        // Build a message to provide feedback to user
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

        // Provide feedback to user
        String reportString = new String(report);
        CustomDialog importInfo = new CustomDialog("File load", true, reportString);
    }

    /**
     * Builds a String representing the currently applied rules and displays it in a label.
     */
    private void displayRules() {

        LinkedList<Byte> birthRules = GoL.getBirthRules();
        LinkedList<Byte> survivalRules = GoL.getSurvivalRules();
        StringBuilder rulesBuilder = new StringBuilder();
        rulesBuilder.append("B");
        for (byte b : birthRules) {
            rulesBuilder.append(b);
        }
        rulesBuilder.append("/S");
        for(byte s : survivalRules) {
            rulesBuilder.append(s);
        }
        String rulesString = new String(rulesBuilder);
        rulesLabel.setText(rulesString);
    }

    /**
     * Re-evaluates an appropriate cellsize if a new board pattern is loaded.
     */
    private void calculateCellSize() {


        double canvasHeightDouble = boardCanvas.getHeight();
        int boardHeightInt = board.getHeight();
        double boardHeightDouble = (double) boardHeightInt;
        GoL.setCellSize(canvasHeightDouble / boardHeightDouble);
        cellSizeSlider.setValue(GoL.getCellSize());
    }

    /**
     * Displays the current board dimensions in a label.
     */
    private void displayDimensions() {

        currentDimensionsLabel.setText("Width = " + board.getWidth() + " / Height = " + board.getHeight());
    }
}

