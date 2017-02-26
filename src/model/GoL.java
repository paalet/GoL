package model;

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


    public static byte rules(int neighbors, int aliveStatus) {

        byte nextStatus = 0;

        if (aliveStatus == 1) {

            if (neighbors <= 1 ) {
                nextStatus = 0;
            }

            else if (neighbors >= 4) {
                nextStatus = 0;
            }

            else if (neighbors == 2 || neighbors == 3 ) {
                nextStatus = 1;
            }
        }
        else if (aliveStatus == 0) {

            if (neighbors == 3 ) {
                nextStatus = 1;
            }

            else {
                nextStatus = 0;
            }
        }
        return nextStatus;
    }
}

