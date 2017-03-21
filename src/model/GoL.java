package model;

import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

/**
 * Created by Pål on 09.02.2017.
 */
public class GoL {


    private static boolean isRunning;
    private static double cellSize;
    private static Color aliveCellColor;
    private static Color deadCellColor;
    private static double currRate;
    private static int[] birth = {3};
    private static int[] survival = {2,3};


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


    public void setBirth(int[] b) {

        birth = b;
    }


    public void setSurvival(int[] s) {

        survival = s;
    }


    public static void calculateCellSize (double canvasSize, Slider cellSizeSlider) {
        double sizeFromSlider = cellSizeSlider.getValue();

        double newCellAmount = canvasSize / sizeFromSlider;

        double roundedAmount = Math.round(newCellAmount);
        double floorAmount = Math.floor(newCellAmount);
        double ceilAmount = Math.ceil(newCellAmount);

        if (roundedAmount == floorAmount) {
            setCellSize(450/floorAmount);
            cellSizeSlider.setValue(450/floorAmount);
        }
        else {
            setCellSize(450/ceilAmount);
            cellSizeSlider.setValue(450/ceilAmount);
        }
    }


    public static byte rules(int neighbors, int aliveStatus) {

        byte nextStatus = 0;

        if (aliveStatus == 1) {

            for (int i = 0; i < survival.length; i++) {

                if (neighbors == survival[i]) {

                    nextStatus = 1;
                }
            }
        }
        else if (aliveStatus == 0) {

            for (int i = 0; i < birth.length; i++) {

                if (neighbors == birth[i]) {

                    nextStatus = 1;
                }
            }
        }
        return nextStatus;
    }
}

