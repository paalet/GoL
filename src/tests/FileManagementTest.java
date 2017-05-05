package tests;


import model.FileManagement;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class for testing the logic in model.FileManagement.
 */
public class FileManagementTest {

    /**
     * Test that the hashmap returned from readFile represents what is in the file "1beacon.rle."
     * @throws IOException if file can not be read
     */
    @Test
    public void testReadFile() throws IOException {

        // Arrange
        File rleFile = new File("/1beacon.rle");
        HashMap<String, String> expectedResult = new HashMap<>();
        expectedResult.put("title", " 1 beacon\n");
        expectedResult.put("origin", null);
        expectedResult.put("comments", " Approximately the 32nd-most common oscillator.\n" +
                " www.conwaylife.com/wiki/index.php?title=1_beacon\n");
        expectedResult.put("width", "x = 7");
        expectedResult.put("height", "y = 7");
        expectedResult.put("rules", "rule = b3/s23\n");
        expectedResult.put("pattern", "2b2o3b$bobo3b$o2bob2o$2obo2bo$bobo3b$bo2bo2b$2b2o!");
        expectedResult.put("File Path", "testpath");

        // Act
        HashMap<String, String> actualResult = FileManagement.readFile(new FileReader(rleFile), "testpath");

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that readDimension with a parameter string of "x = 7" returns int 7.
     */
    @Test
    public void testReadDimension_01() {

        // Arrange
        String dimensionString = new String("x = 7");
        int expectedResult = 7;

        // Act
        int actualResult = FileManagement.readDimension(dimensionString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that readDimension with a parameter string of "y = 1140" returns int 1140.
     */
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

    /**
     * Test that readDimension with a parameter string of "x = 015" returns int 15.
     */
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

    /**
     * Test that readDimension with a parameter string of "y = -1" returns int 0.
     */
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

    /**
     * Test that readDimension with a parameter string of "x = !" returns int 0.
     */
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

    /**
     * Test that readRules with a parameter string of "rule = b3/s23" returns 3 as birth rule and 2 and 3 as survival rules.
     */
    @Test
    public void testReadRules_01() {

        // Arrange
        String rulesString = "rule = b3/s23";

        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        ArrayList<LinkedList<Byte>> expectedResult = new ArrayList<>();
        expectedResult.add(birthRules);
        expectedResult.add(survivalRules);

        // Act
        ArrayList<LinkedList<Byte>> actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that readRules with a parameter string of "rule = b123/s12345678" returns 1, 2 and 3 as birth rules and
     * 1, 2, 3, 4, 5, 6, 7 and 8 as survival rules.
     */
    @Test
    public void testReadRules_02() {

        // Arrange
        String rulesString = new String("rule = b123/s12345678");

        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 1);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 7);
        survivalRules.add((byte) 8);

        ArrayList<LinkedList<Byte>> expectedResult = new ArrayList<>();
        expectedResult.add(birthRules);
        expectedResult.add(survivalRules);

        // Act
        ArrayList<LinkedList<Byte>> actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that readRules with a parameter string of "rules = b12/s234" returns 1 and 2 as birth rules and
     * 2, 3 and 4 as survival rules.
     */
    @Test
    public void testReadRules_03() {

        // Arrange
        String rulesString = new String("rules = b12/s234");

        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        ArrayList<LinkedList<Byte>> expectedResult = new ArrayList<>();
        expectedResult.add(birthRules);
        expectedResult.add(survivalRules);

        // Act
        ArrayList<LinkedList<Byte>> actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that readRules with a parameter string of "rule = B3/S23" returns 3 as birth rule and
     * 2 and 3 as survival rules.
     */
    @Test
    public void testReadRules_04() {

        // Arrange
        String rulesString = new String("rule = B3/S23");

        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        ArrayList<LinkedList<Byte>> expectedResult = new ArrayList<>();
        expectedResult.add(birthRules);
        expectedResult.add(survivalRules);

        // Act
        ArrayList<LinkedList<Byte>> actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that readRules with a parameter string of "rule = b0123456789/s0123456789" returns 0 through 8 as both birth and
     * survival rules, but no rule of 9.
     */
    @Test
    public void testReadRules_05() {

        // Arrange
        String rulesString = new String("rule = b0123456789/s0123456789");

        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 0);
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        birthRules.add((byte) 4);
        birthRules.add((byte) 5);
        birthRules.add((byte) 6);
        birthRules.add((byte) 7);
        birthRules.add((byte) 8);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 1);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 7);
        survivalRules.add((byte) 8);
        ArrayList<LinkedList<Byte>> expectedResult = new ArrayList<>();
        expectedResult.add(birthRules);
        expectedResult.add(survivalRules);

        // Act
        ArrayList<LinkedList<Byte>> actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that readRules with a parameter string of "rule = b38/s23¤8" returns 3 and 8 as birth rules and
     * 2, 3 and 8 as survival rules.
     */
    @Test
    public void testReadRules_06() {

        // Arrange
        String rulesString = new String("rule = b38/s23¤8");

        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        birthRules.add((byte) 8);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 8);
        ArrayList<LinkedList<Byte>> expectedResult = new ArrayList<>();
        expectedResult.add(birthRules);
        expectedResult.add(survivalRules);

        // Act
        ArrayList<LinkedList<Byte>> actualResult = FileManagement.readRules(rulesString);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that readPatternStaticBoard returns the correct board
     */
    @Test
    public void testReadPatternStaticBoard_01() {

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

    /**
     * Test that readPatternStaticBoard returns the correct board
     */
    @Test
    public void testReadPatternStaticBoard_02() throws IOException{

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

    /**
     * Test that readPatternStaticBoard returns the correct board
     */
    @Test
    public void testReadPatternStaticBoard_03() throws IOException{

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

