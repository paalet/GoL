package tests;

import model.GoL;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class for testing the logic in model.GoL.
 */
public class GoLTest {


    @Test
    public void testRules_01() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 0;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_02() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 1;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_03() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 2;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_04() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 3;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_05() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 4;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_06() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 5;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_07() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 6;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_08() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 7;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_09() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 8;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_10() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 0;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_11() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 1;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_12() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 2;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_13() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 3;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_14() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 4;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_15() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 5;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_16() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 6;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_17() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 7;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_18() {

        // Arrange
        int[] bornAmount = {3};
        int[] surviveAmount = {2,3};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 8;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_19() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 0;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_20() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 1;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_21() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 2;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_22() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 3;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_23() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 4;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_24() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 5;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_25() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 6;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_26() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 7;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_27() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 8;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_28() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 0;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_29() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 1;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_30() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 2;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_31() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 3;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_32() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 4;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_33() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 5;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_34() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 6;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_35() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 7;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_36() {

        // Arrange
        int[] bornAmount = {1,2,3};
        int[] surviveAmount = {3,4,5};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 8;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_37() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 0;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_38() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 1;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_39() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 2;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_40() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 3;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_41() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 4;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_42() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 5;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_43() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 6;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_44() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 7;
        int aliveStatus = 0;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_45() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 8;
        int aliveStatus = 0;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_46() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 0;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_47() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 1;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_48() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 2;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_49() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 3;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_50() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};;
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 4;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_51() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 5;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_52() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 6;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_53() {

        // Arrange
        int[] bornAmount = {1,3,5,7};
        int[] surviveAmount = {0,2,4,6,8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 7;
        int aliveStatus = 1;
        byte expectedResult = 0;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testRules_54() {

        // Arrange
        int[] bornAmount = {1, 3, 5, 7};
        int[] surviveAmount = {0, 2, 4, 6, 8};
        GoL.setBornAmount(bornAmount);
        GoL.setSurviveAmount(surviveAmount);
        int neighbors = 8;
        int aliveStatus = 1;
        byte expectedResult = 1;

        // Act
        byte actualResult = GoL.rules(neighbors, aliveStatus);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }
}
