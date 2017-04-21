package tests;


import model.FileManagement;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class for testing the logic in model.FileManagement.
 */
public class FileManagementTest {


    // TODO: 04.04.2017 Teste loadfile-metodene og få til testReadFile


    @Test
    public void testReadFile() throws IOException {

        // Arrange
        File rleFile = new File("testpatterns/1beacon.rle");
        HashMap<String, String> expectedResult = new HashMap<>();
        expectedResult.put("title", " 1 beacon\n");
        expectedResult.put("origin", null);
        expectedResult.put("comments", " Approximately the 32nd-most common oscillator.\n" +
                " www.conwaylife.com/wiki/index.php?title=1_beacon\n");
        expectedResult.put("width", "x = 7");
        expectedResult.put("height", "y = 7");
        expectedResult.put("rules", "rule = b3/s23\n");
        expectedResult.put("pattern", "2b2o3b$bobo3b$o2bob2o$2obo2bo$bobo3b$bo2bo2b$2b2o!");

        // Act
        HashMap<String, String> actualResult = FileManagement.readFile(new FileReader(rleFile));

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadDimension_01() throws IOException {

        // Arrange
        String dimensionString = new String("x = 7");
        int expectedResult = 7;

        // Act
        int actualResult = FileManagement.readDimension(dimensionString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadDimension_02() throws IOException {

        // Arrange
        String dimensionString = new String("y = 1140");
        int expectedResult = 1140;

        // Act
        int actualResult = FileManagement.readDimension(dimensionString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadDimension_03() throws IOException {

        // Arrange
        String dimensionString = new String("x = 015");
        int expectedResult = 15;

        // Act
        int actualResult = FileManagement.readDimension(dimensionString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadDimension_04() throws IOException {

        // Arrange
        String dimensionString = new String("y = 1");
        int expectedResult = 1;

        // Act
        int actualResult = FileManagement.readDimension(dimensionString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadDimension_05() throws IOException {

        // Arrange
        String dimensionString = new String("x = !");
        int expectedResult = 0;

        // Act
        int actualResult = FileManagement.readDimension(dimensionString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadDimension_06() throws IOException {

        // Arrange
        String dimensionString = new String("x=10");
        int expectedResult = 10;

        // Act
        int actualResult = FileManagement.readDimension(dimensionString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadRules_01() {

        // Arrange
        String rulesString = new String("rule = b3/s23");
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        int[][] expectedResult = {bornAmount, surviveAmount};

        // Act
        int[][] actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadRules_02() {

        // Arrange
        String rulesString = new String("rule = b123/s12345678");
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {1,2,3,4,5,6,7,8};
        int[][] expectedResult = {bornAmount, surviveAmount};

        // Act
        int[][] actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadRules_03() {

        // Arrange
        String rulesString = new String("rules = b12/s234");
        int[] bornAmount = {1,2};
        int[] surviveAmount = {2,3,4};
        int[][] expectedResult = {bornAmount, surviveAmount};

        // Act
        int[][] actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadRules_04() {

        // Arrange
        String rulesString = new String("rule = B3/S23");
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        int[][] expectedResult = {bornAmount, surviveAmount};

        // Act
        int[][] actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadRules_05() {

        // Arrange
        String rulesString = new String("rule = b0123456789/s0123456789");
        int[] bornAmount = {0,1,2,3,4,5,6,7,8,9};
        int[] surviveAmount = {0,1,2,3,4,5,6,7,8,9};
        int[][] expectedResult = {bornAmount, surviveAmount};

        // Act
        int[][] actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadRules_06() {

        // Arrange
        String rulesString = new String("rule = b38/s23¤8");

        // Act
        int[][] actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertNull(actualResult);
    }


    @Test
    public void testReadPattern_01() throws IOException{

        // Pattern 1beacon.rle

        // Arrange
        String patternString = new String("2b2o3b$bobo3b$o2bob2o$2obo2bo$bobo3b$bo2bo2b$2b2o!");
        int width = 7;
        int height = 7;
        byte [][] expectedResult = {
                {0,0,1,1,0,0,0},
                {0,1,0,1,0,0,0},
                {1,0,0,1,0,1,1},
                {1,1,0,1,0,0,1},
                {0,1,0,1,0,0,0},
                {0,1,0,0,1,0,0},
                {0,0,1,1,0,0,0}
        };

        // Act
        byte[][] actualResult = FileManagement.readPatternStaticBoard(patternString, width, height);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadPattern_02() throws IOException{

        // Pattern 5blink.rle

        // Arrange
        String patternString = new String("5bo$4bob2o$o3bo3b2o$o8bo$o3bo$3b3obo3bo$bobo2b2o2bobo$obo8bo$2o!");
        int width = 13;
        int height = 9;
        byte [][] expectedResult = {
                {0,0,0,0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,1,1,0,0,0,0,0},
                {1,0,0,0,1,0,0,0,1,1,0,0,0},
                {1,0,0,0,0,0,0,0,0,1,0,0,0},
                {1,0,0,0,1,0,0,0,0,0,0,0,0},
                {0,0,0,1,1,1,0,1,0,0,0,1,0},
                {0,1,0,1,0,0,1,1,0,0,1,0,1},
                {1,0,1,0,0,0,0,0,0,0,0,1,0},
                {1,1,0,0,0,0,0,0,0,0,0,0,0}
        };

        // Act
        byte[][] actualResult = FileManagement.readPatternStaticBoard(patternString, width, height);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadPattern_03() throws IOException{

        // Pattern barge.rle

        // Arrange
        String patternString = new String("bo2b$obob$bobo$2bo!");
        int width = 4;
        int height = 4;
        byte [][] expectedResult = {
                {0,1,0,0},
                {1,0,1,0},
                {0,1,0,1},
                {0,0,1,0},

        };

        // Act
        byte[][] actualResult = FileManagement.readPatternStaticBoard(patternString, width, height);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void testReadPattern_04() throws IOException{

        // Pattern beehat.rle

        // Arrange
        String patternString = new String("b2o3b$o2bo2b$b2obob$2bobob$obob2o$2o!");
        int width = 6;
        int height = 6;
        byte [][] expectedResult = {
                {0,1,1,0,0,0},
                {1,0,0,1,0,0},
                {0,1,1,0,1,0},
                {0,0,1,0,1,0},
                {1,0,1,0,1,1},
                {1,1,0,0,0,0}
        };

        // Act
        byte[][] actualResult = FileManagement.readPatternStaticBoard(patternString, width, height);

        // Assert
        Assert.assertArrayEquals(expectedResult, actualResult);
    }
}

