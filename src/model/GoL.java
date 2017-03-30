package model;

import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import javafx.scene.canvas.Canvas;

/**
 * Created by Pål on 09.02.2017.
 */
public class GoL {


    private static boolean isRunning;
    private static double cellSize;
    private static Color aliveCellColor;
    private static Color deadCellColor;
    private static double currRate;
    private static int[] bornAmount;
    private static int[] surviveAmount;


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

    public static void calculateCellSize (double canvasHeight, double canvasWidth, Slider cellSizeSlider) {
        double sizeFromSlider = cellSizeSlider.getValue();

        double newCellAmount = canvasHeight / sizeFromSlider;

        double roundedAmount = Math.round(newCellAmount);
        double floorAmount = Math.floor(newCellAmount);
        double ceilAmount = Math.ceil(newCellAmount);

        if (roundedAmount == floorAmount) {
            setCellSize(canvasHeight/floorAmount);
            cellSizeSlider.setValue(canvasHeight/floorAmount);
        }
        else {
            setCellSize(canvasHeight/ceilAmount);
            cellSizeSlider.setValue(canvasHeight/ceilAmount);
        }
    }




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

