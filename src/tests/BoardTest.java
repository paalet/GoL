package tests;

import model.StaticBoard;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pål on 03.03.2017.
 */
public class BoardTest {

    @Test
    public void testBoard(){
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
        String expectedBoardString = new String("0000000001110000000000000000000000000000000000000000001100000011");
        StaticBoard staticBoard = new StaticBoard();

        staticBoard.setBoard(testBoard);
        staticBoard.nextGeneration();
        String boardString = staticBoard.toString();
        Assert.assertEquals(boardString, expectedBoardString);
    }

}