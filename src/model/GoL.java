package model;

import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * Class with data related to game state and execution
 */
public class GoL {


    private static boolean isRunning;
    private static double cellSize;
    private static Color aliveCellColor;
    private static Color deadCellColor;
    private static double currRate;
    private static int[] bornAmount;
    private static int[] surviveAmount;
    private static HashMap<String, String> loadedData;


    public static void setIsRunning(boolean running) {

        isRunning = running;
    }


    public static boolean getIsRunning() {

        return isRunning;
    }


    public static void setCellSize(double size){

       cellSize = size;
    }


    public static double getCellSize(){

        return cellSize;
    }


    public static void setAliveCellColor(Color aliveColor) {

        aliveCellColor = aliveColor;
    }


    public static void setDeadCellColor(Color deadColor) {

        deadCellColor = deadColor;
    }


    public static Color getAliveCellColor() {

        return aliveCellColor;
    }


    public static Color getDeadCellColor() {

        return deadCellColor;
    }


    public static void setCurrRate(double rate) {

        currRate = rate;
    }


    public static double getCurrRate() {

        return currRate;
    }

    public static void setBornAmount(int[] newBornAmount) {

        bornAmount = newBornAmount;
    }

    public static int[] getBornAmount() {
        return bornAmount;

    }

    public static void setSurviveAmount(int[] newSurviveAmount) {

        surviveAmount = newSurviveAmount;
    }

    public static int[] getSurviveAmount() {

        return surviveAmount;
    }

    public static void setLoadedData(HashMap<String, String> newFileData) {
        loadedData = newFileData;
    }

    public static HashMap<String, String> getLoadedData() {
        return loadedData;
    }

    /**
    * Calculates a cellsize and snaps it to the nearest possible value that would fit in the canvas perfectly based on the horizontal size.
    */
    public static void calculateCellSize (int boardHeight, int boardWidth, double canvasHeight, double canvasWidth, Slider cellSizeSlider, TextArea gameMessagesText) {

        //Bolean values that will set to true if the dimension it represents exceeds the maximum allowed value.
        boolean heightExceeded = false;
        boolean widthExceeded = false;

        //String value that will contain the name of the axis to adjust the canvas size to, if any of the two has exceeded the maximum allowed value.
        String dimensionToFit = "";

        double sizeFromSlider = cellSizeSlider.getValue();

        //The new cell amounts that is set to be applied given the value from the slider.
        double cellAmountHeight = canvasHeight / sizeFromSlider;
        double cellAmountWidth = canvasWidth / sizeFromSlider;

        //Rounding the values so that we later can see which one it is closest to, the higher or lower whole number.
        double roundedHeight = Math.round(cellAmountHeight);
        double floorHeight = Math.floor(cellAmountHeight);
        double ceilHeight = Math.ceil(cellAmountHeight);


        //Values containing the new proposed width and height of the canvas.
        double newHeight = cellAmountHeight * canvasHeight;
        double newWidth = cellAmountWidth * canvasWidth;

        //Checks if the new cellAmount expands the board to a value beyond the maximum allowed texture size by java.
        if((boardHeight * sizeFromSlider) > 5000) {
            heightExceeded = true;
        }

            if((boardWidth * sizeFromSlider) > 5000) {
            widthExceeded = true;
        }

        if(widthExceeded && heightExceeded) {
            if(newHeight > newWidth) {
                dimensionToFit = "height";
            }
            else if(newHeight < newWidth) {
                dimensionToFit = "width";
            }
            else
            {
                dimensionToFit = "squareBoard";
            }

        }

        if(!widthExceeded && heightExceeded) {
            dimensionToFit = "height";
        }

        if(widthExceeded && !heightExceeded) {
            dimensionToFit = "width";
        }

        switch(dimensionToFit) {

            case "height" : {
                double sliderSize = Math.floor(5000/(double)boardHeight);
                GoL.setCellSize(sliderSize);
                cellSizeSlider.setValue(sliderSize);
                gameMessagesText.setText("This value will exceed the size limit\nof the canvas.\nPlease choose a lower cell size.");
                break;
            }

            case "width" :  {
                double sliderSize = Math.floor(5000/(double)boardWidth);
                GoL.setCellSize(sliderSize);
                cellSizeSlider.setValue(sliderSize);
                gameMessagesText.setText("This value will exceed the size limit\nof the canvas.\nPlease choose a lower cell size.");
                break;
            }

            case "squareBoard" : {
                double sliderSize = Math.floor(5000/(double)boardHeight);
                GoL.setCellSize(sliderSize);
                cellSizeSlider.setValue(sliderSize);
                gameMessagesText.setText("This value will exceed the size limit\nof the canvas.\nPlease choose a lower cell size.");
                break;
            }


            default:
                break;

        }

        if(!widthExceeded && !heightExceeded) {

            if (roundedHeight == floorHeight) {
                setCellSize(canvasHeight / floorHeight);
                cellSizeSlider.setValue(canvasHeight / floorHeight);
            } else {
                setCellSize(canvasHeight / ceilHeight);
                cellSizeSlider.setValue(canvasHeight / ceilHeight);
            }
        }

    }


    /**
     * Method that determines if a cell is to live or not in the next generation of the game based on
     * the conditions set for for birth and survival in the class' fields bornAmount and surviveAmount.
     * @param neighbors the amount of live cells neighboring the specified cell.
     * @param aliveStatus the current status of the specified cell.
     * @return the cell's status for the next generation.
     */
    public static byte rules(int neighbors, int aliveStatus) {

        byte nextStatus = 0;

        if (aliveStatus == 1) {

            for (int i = 0; i < surviveAmount.length; i++) {

                if (neighbors == surviveAmount[i]) {

                    nextStatus = 1;
                }
            }
        }
        else if (aliveStatus == 0) {

            for (int i = 0; i < bornAmount.length; i++) {

                if (neighbors == bornAmount[i]) {

                    nextStatus = 1;
                }
            }
        }
        return nextStatus;
    }
}

