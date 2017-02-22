package model;

import javafx.scene.paint.Color;

/**
 * Created by Pål on 09.02.2017.
 */
public class GoL {

    private static double cellSize;
    private static Color aliveCellColor;
    private static Color deadCellColor;
    private static int msPerGen;



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


    public static void setMsPerGen(int ms) {

        msPerGen = ms;
    }

    public static int getMsPerGen() {

        return msPerGen;
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

