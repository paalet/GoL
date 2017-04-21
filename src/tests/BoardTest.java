package tests;

import model.GoL;
import model.StaticBoard;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class for testing the logic in abstract class model.Board
 */
public class BoardTest {

    @Test
    public void testNextGeneration_board01(){

        /*
         Boardsize: 8x8
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,1},
                {1,0,0,1,1,0,0,1},
                {1,0,0,1,1,0,0,1},
                {1,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1}
        };
        String expectedBoardString = new String(
                "11111111" +
                "10111101" +
                "11000011" +
                "11011011" +
                "11011011" +
                "11000011" +
                "10111101" +
                "11111111"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(8);
        testStaticBoard.setWidth(8);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board02(){

        /*
         Boardsize: 8x8
         Rules:     b3 / s2,3
        */

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
                "00000000"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(8);
        testStaticBoard.setWidth(8);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board03(){

        /*
         Boardsize: 8x8
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {1,1,1,0,0,0,0,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,1,0,0,0,0},
                {0,0,0,1,1,0,0,0},
                {1,0,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,0},
                {1,0,0,0,0,1,1,1}
        };
        String expectedBoardString = new String(
                "01000000" +
                "01000011" +
                "00000000" +
                "00011000" +
                "00011000" +
                "00000000" +
                "11000010" +
                "00000010"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(8);
        testStaticBoard.setWidth(8);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board04(){

        /*
         Boardsize: 8x8
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0}
        };
        String expectedBoardString = new String(
                "01111111" +
                "00000000" +
                "11111111" +
                "00000000" +
                "11111111" +
                "00000000" +
                "11111111" +
                "10111110"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(8);
        testStaticBoard.setWidth(8);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board05(){

        /*
         Boardsize: 8x8
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {1,1,1,0,1,1,1,0},
                {1,0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1,0},
                {1,0,1,1,1,0,1,1}
        };
        String expectedBoardString = new String(
                "10101010" +
                "10101011" +
                "10101011" +
                "10101011" +
                "10101011" +
                "10101011" +
                "10101010" +
                "00101011"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(8);
        testStaticBoard.setWidth(8);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board06(){

        /*
         Boardsize: 8x8
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {1,1,0,0,0,0,1,1},
                {1,1,0,0,0,0,1,1},
                {0,0,1,0,0,1,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,1,0,0},
                {1,1,0,0,0,0,1,1},
                {1,1,0,0,0,0,1,1}
        };
        String expectedBoardString = new String(
                "11000011" +
                "10100101" +
                "01000010" +
                "00000000" +
                "00000000" +
                "01000010" +
                "10100101" +
                "11000011"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(8);
        testStaticBoard.setWidth(8);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board07(){

        /*
         Boardsize: 6x10
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1},
        };
        String expectedBoardString = new String(
                "1111111111" +
                "1011111101" +
                "1100000011" +
                "1100000011" +
                "1011111101" +
                "1111111111"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(6);
        testStaticBoard.setWidth(10);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board08(){

        /*
         Boardsize: 10x5
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {0,0,1,0,0},
                {0,0,1,0,0},
                {1,1,1,1,1},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {1,1,1,1,1},
                {0,0,1,0,0},
                {0,0,1,0,0}
        };
        String expectedBoardString = new String(
                "00000" +
                "00000" +
                "00000" +
                "00000" +
                "01110" +
                "01110" +
                "00000" +
                "00000" +
                "00000" +
                "00000"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(10);
        testStaticBoard.setWidth(5);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board09(){

        /*
         Boardsize: 10x10
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,0,0,0,0,1,1,0},
                {0,1,1,0,0,0,0,1,1,0},
                {0,1,1,0,0,0,0,1,1,0},
                {0,1,1,0,0,0,0,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,0,0,0,0,0,0,0,0,0}
        };
        String expectedBoardString = new String(
                "0011111100" +
                "0100000010" +
                "1000000001" +
                "1000110001" +
                "1001001001" +
                "1001001001" +
                "1000110001" +
                "1000000001" +
                "0100000010" +
                "0011111100"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(10);
        testStaticBoard.setWidth(10);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_board10(){

        /*
         Boardsize: 10x10
         Rules:     b3 / s2,3
        */

        // Arrange
        byte[][] testBoard = {
                {0,1,0,0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,1,0,0,1,1,1}
        };
        String expectedBoardString = new String(
                "0000000000" +
                "1111111110" +
                "0000000000" +
                "0000000000" +
                "0000000000" +
                "1111111110" +
                "0000000000" +
                "0000000000" +
                "0001000010" +
                "0001000010"
        );
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(10);
        testStaticBoard.setWidth(10);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);
        testStaticBoard.setCurrentBoard(testBoard);

        // Act
        testStaticBoard.nextGeneration();

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }


    @Test
    public void testNextGeneration_multipleGenerations(){

        /*
         Boardsize: 10x10
         Rules:     b3 / s2,3
        */

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
        StaticBoard testStaticBoard = new StaticBoard();
        testStaticBoard.setHeight(10);
        testStaticBoard.setWidth(10);
        testStaticBoard.newBoard();
        int[] initBornAmount = {3};
        int[] initSurviveAmount = {2,3};
        GoL.setBornAmount(initBornAmount);
        GoL.setSurviveAmount(initSurviveAmount);

        // Act
        testStaticBoard.setCurrentBoard(testBoard);
        for (int i = 0; i < 28; i++) {
            testStaticBoard.nextGeneration();
        }

        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }

}