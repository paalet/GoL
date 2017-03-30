package tests;

import model.GoL;
import model.StaticBoard;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void testNextGeneration_board1(){

        // Arrange
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

        // Act
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHEIGHT(8);
        testStaticBoard.setWIDTH(8);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setBoard(testBoard);
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board2(){

        // Arrange
        byte[][] testBoard = {
                {0,0,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,1}
        };
        String expectedBoardString = new String(
                "00000000" +
                "00000011" +
                "11100011" +
                "11100011" +
                "11100011" +
                "11100011" +
                "00000011" +
                "00000000");

        // Act
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHEIGHT(8);
        testStaticBoard.setWIDTH(8);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setBoard(testBoard);
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_multipleGenerations(){

        // Arrange
        byte[][] testBoard = {
                {1,0,1,0,0,0,0,0,0,0},
                {0,1,1,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };
        String expectedBoardString = new String(
                "0000000000" +
                "0000000000" +
                "0000000000" +
                "0000000000" +
                "0000000000" +
                "0000000000" +
                "0000000000" +
                "0000000101" +
                "0000000011" +
                "0000000010");

        // Act
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHEIGHT(10);
        testStaticBoard.setWIDTH(10);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setBoard(testBoard);
        for (int i = 0; i < 28; i++) {
            testStaticBoard.nextGeneration();
        }

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }

}