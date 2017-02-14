package model;

import javafx.scene.paint.Color;

/**
 * Created by Pål on 09.02.2017.
 */
public class GoL {

    private static double cellSize;
    private static Color cellColor;


   public static void setCellSize(double size){

       cellSize = size;

    }

    public static double getCellSize(){

        return cellSize;

    }

    public static void setCellColor(Color color) {
        cellColor = color;
    }

    public static Color getCellColor() {

        return cellColor;
    }
}

