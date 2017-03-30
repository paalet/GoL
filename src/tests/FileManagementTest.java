package tests;

import javafx.fxml.FXML;
import model.FileManagement;
import model.StaticBoard;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManagementTest {

    @FXML
    private Canvas boardCanvas;

    @Test
    public void testReadFile() throws IOException {

        // Arrange
        String expectedBoardString = new String(
                "0011000" +
                "0101000" +
                "1001011" +
                "1101001" +
                "0101000" +
                "0100100" +
                "0011000");

        // Act
        StaticBoard testStaticBoard = new StaticBoard();
        File rleFile = new File("testpatterns/1beacon.rle");
        if (rleFile != null) {
            FileManagement.readFile(new FileReader(rleFile), testStaticBoard, 585.0, 804.375);
        }
        // Assert
        String actualBoardString = testStaticBoard.toString();
        Assert.assertEquals(expectedBoardString, actualBoardString);
    }
}
