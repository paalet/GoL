package model;

import controller.MainScreenController;

import java.awt.*;
import java.awt.TextArea;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.*;
import java.awt.TextArea;

import static javafx.application.ConditionalFeature.FXML;

/**
 * Created by simenperschandersen on 07.03.2017.
 */
public class FileManagement {


    public static void readFile(Reader r, StaticBoard staticBoard, double canvasHeight, double canvasWidth, String[] metaData) throws IOException {

        StringBuilder fileString = new StringBuilder();
        int data = r.read();
        while (data != -1) {
            char exitChar = (char) data;
            fileString.append(exitChar);
            data = r.read();
        }
        String fileStringResult = new String(fileString);
        int i = 0;

        //Sift out title and comments
        String title = null;
        String origin = null;
        List<String> comments = new ArrayList<String>();
        byte commentCntr = 0;
        while (fileStringResult.indexOf(35, i) != -1) {
            int hashTag = fileStringResult.indexOf(35, i);
            char nextChar = fileStringResult.charAt(hashTag + 1);
            int endOfLine = fileStringResult.indexOf(10, i);
            i = endOfLine + 1;

            switch (nextChar) {
                case 78:
                    title = new String(fileStringResult.substring(hashTag + 2, endOfLine));
                    break;
                case 79:
                    origin = new String(fileStringResult.substring(hashTag + 2, endOfLine));
                    break;
                case 67:
                    comments.add(commentCntr, fileStringResult.substring(hashTag + 2, endOfLine));
                    commentCntr++;
                    break;

                default: break;
            }
        }
        metaData[0] = title;
        metaData[1] = origin;




        //Find x-size
        int x = fileStringResult.indexOf(120, i);
        int comma = fileStringResult.indexOf(44, x);
        String coordSubString = fileStringResult.substring(x,comma);
        staticBoard.setWIDTH(readDimension(coordSubString));
        //Find y-size
        int y = fileStringResult.indexOf(121, i);
        comma = fileStringResult.indexOf(44, y);
        coordSubString = fileStringResult.substring(y,comma);
        staticBoard.setHEIGHT(readDimension(coordSubString));
        staticBoard.calculateBoardSize(canvasHeight, canvasWidth);
        staticBoard.newBoard();

        //Find rules if there are any
        if (fileStringResult.contains("rule")) {
            int rulesIndex = fileStringResult.indexOf("rule");
            int rulesEndIndex = fileStringResult.indexOf(10, rulesIndex);
            String rulesString = fileStringResult.substring(rulesIndex, rulesEndIndex);
            readRules(rulesString);
        }

        // Extract Game of Life pattern
        int endOfLine = fileStringResult.indexOf(10, i);
        i = endOfLine + 1;
        String patternString = fileStringResult.substring(i);
        staticBoard.setBoard(readPattern(patternString, staticBoard.getHEIGHT(), staticBoard.getWIDTH()));
    }


    public static int readDimension(String inputString) throws IOException {

        Scanner dimensionScanner = new Scanner(inputString);
        dimensionScanner.useDelimiter(" ");
        boolean isDone = false;
        int dimension = 0;
        while (!isDone) {
            if (dimensionScanner.hasNextInt()) {
                dimension = dimensionScanner.nextInt();
                isDone = true;
            }
            else {
                dimensionScanner.next();
            }
        }
        return dimension;

    }

    public static void readRules(String rulesString) {

        //Counts the amount of byte values and makes a new array with a fitting size to fit that amount of values
        int stringLength = rulesString.length();
        int index = rulesString.indexOf(66);
        index++;
        int bornNumbers = 0;
        while (rulesString.charAt(index) != 47) {
            bornNumbers++;
            index++;
        }
        int [] bornAmount = new int[bornNumbers];;

        //Sets the byte values in the array with a for-loop
        index = rulesString.indexOf(66);
        index++;
        for (int i = 0; i < bornNumbers; i++) {
            char charAtIndex = rulesString.charAt(index);
            int numberAtIndex = Character.getNumericValue(charAtIndex);
            bornAmount[i] = numberAtIndex;
            index++;
        }
        GoL.setBornAmount(bornAmount);

        //Counts the amount of byte values and makes a new array with a fitting size to fit that amount of values
        index = rulesString.indexOf(83);
        index++;
        int surviveNumbers = 0;
        while (index < stringLength) {
            surviveNumbers++;
            index++;
        }
        int [] surviveAmount = new int[surviveNumbers];

        //Sets the byte values in the array with a for-loop
        index = rulesString.indexOf(83);
        index++;
        for (int i = 0; i < surviveNumbers; i++) {
            char charAtIndex = rulesString.charAt(index);
            int numberAtIndex = Character.getNumericValue(charAtIndex);
            surviveAmount[i] = numberAtIndex;
            index++;
        }

        GoL.setSurviveAmount(surviveAmount);
    }

    public static byte[][] readPattern(String patternString, int height, int width) throws IOException {

        // Create scanner to break pattern into rows
        Scanner patternScanner = new Scanner(patternString);
        patternScanner.useDelimiter("\\$");
        byte[][] board = new byte[width][height];
        int rowNo = 0;

        // Create charArray with pattern info for each row
        while (patternScanner.hasNext()) {
            String row = new String(patternScanner.next());
            char[] charArray = row.toCharArray();
            String cellCountString = new String("");
            int columnNo = 0;

            // Go through each row char by char
            try {
                for (int i = 0; i < charArray.length; i++) {

                    // Numbers
                    if (charArray[i] >= 48 && charArray[i] <= 57) {
                        cellCountString = cellCountString + charArray[i];
                    }

                    // Dead cells
                    else if (charArray[i] == 98) {

                        if (cellCountString.equals("")) {
                            board[rowNo][columnNo] = 0;
                            columnNo++;
                        } else {
                            int cellCountInt = Integer.parseInt(cellCountString);
                            for (int j = 0; j < cellCountInt; j++) {
                                board[rowNo][columnNo] = 0;
                                columnNo++;
                            }
                        }
                        cellCountString = "";
                    }

                    // Alive cell
                    else if (charArray[i] == 111) {
                        if (cellCountString.equals("")) {
                            board[rowNo][columnNo] = 1;
                            columnNo++;
                        } else {
                            int cellCountInt = Integer.parseInt(cellCountString);
                            for (int j = 0; j < cellCountInt; j++) {
                                board[rowNo][columnNo] = 1;
                                columnNo++;
                            }
                        }
                        cellCountString = "";
                    }
                }
                rowNo++;
            }
            catch(ArrayIndexOutOfBoundsException e) {
                //
            }
        }
        return board;
    }

}