package model;

/**
 * Created by Pål on 09.02.2017.
 */
public class GoL {

    public double cellSize;

    GoL() {
        this.cellSize = 10.0;
    }

    public void setCellSize(double size){
        this.cellSize = size;
    }

    public double getCellSize(){
        return this.cellSize;


    }
}

