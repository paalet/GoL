package tests;

import model.StaticBoard;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pål on 03.03.2017.
 */
public class BoardTest {

    @Test
    public void testNestGeneration1(){
        byte[][] testBoard = {
                {0,0,1,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,1,1}
        };
        String expectedBoardString = new String(
                "00000000" +
                        "01110000" +
                        "00000000" +
                        "00000000" +
                        "00000000" +
                        "00000000" +
                        "00000011" +
                        "00000011");
        StaticBoard staticBoard = new StaticBoard();

        staticBoard.setTestBoard(testBoard);
        staticBoard.nextGeneration();
        Assert.assertEquals(staticBoard.toString(), expectedBoardString);
    }

    @Test
    public void testNestGeneration2(){
        byte[][] testBoard = {
                {0,0,0,0,0,0,0,0},
                {0,1,1,0,0,0,0,0},
                {0,1,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,1,1}
        };
        String expectedBoardString = new String(
                "00000000" +
                        "01110000" +
                        "00000000" +
                        "00000000" +
                        "00000000" +
                        "00000000" +
                        "00000011" +
                        "00000011");
        StaticBoard staticBoard = new StaticBoard();

        staticBoard.setTestBoard(testBoard);
        staticBoard.nextGeneration();
        Assert.assertEquals(staticBoard.toString(), expectedBoardString);
    }
}