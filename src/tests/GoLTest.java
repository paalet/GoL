package tests;

import model.GoL;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

/**
 * Class for testing the logic in model.GoL.
 */
class GoLTest {

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_01() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 0;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_02() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 1;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_03() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 2;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_04() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 3;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_05() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 4;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_06() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 5;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_07() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 6;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_08() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 7;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_09() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 8;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_10() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 0;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_11() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 1;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_12() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 2;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_13() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 3;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_14() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 4;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_15() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 5;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_16() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 6;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_17() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 7;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_18() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 3);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 8;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_19() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 0;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_20() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 1;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_21() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 2;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_22() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 3;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_23() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 4;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_24() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 5;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_25() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 6;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_26() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 7;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_27() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 8;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_28() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 0;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_29() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 1;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_30() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 2;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_31() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 3;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_32() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 4;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_33() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 5;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_34() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 6;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_35() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 7;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_36() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 2);
        birthRules.add((byte) 3);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 3);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 5);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 8;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_37() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 0;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_38() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 1;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_39() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);;
        int neighbors = 2;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_40() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 3;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_41() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 4;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_42() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 5;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_43() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 6;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_44() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 7;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_45() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 8;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_46() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 0;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_47() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 1;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_48() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 2;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_49() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 3;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_50() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 4;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_51() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 5;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    void testRules_52() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 6;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_53() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 7;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that rules() returns correct live status based on implemented rules, cell status
     * and number of live neighbors.
     */
    @Test
    void testRules_54() {

        // Arrange
        LinkedList<Byte> birthRules = new LinkedList<>();
        birthRules.add((byte) 1);
        birthRules.add((byte) 3);
        birthRules.add((byte) 5);
        birthRules.add((byte) 7);
        LinkedList<Byte> survivalRules = new LinkedList<>();
        survivalRules.add((byte) 0);
        survivalRules.add((byte) 2);
        survivalRules.add((byte) 4);
        survivalRules.add((byte) 6);
        survivalRules.add((byte) 8);
        GoL.setBirthRules(birthRules);
        GoL.setSurvivalRules(survivalRules);
        int neighbors = 8;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }
}
