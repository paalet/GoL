package model;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;


/**
 * Logic related to handling input from .rle files.
 */
public class FileManagement {

    /**
     * Finds a local .rle file through user interaction with FileChooser.
     * @return File at the specified filepath.
     */
    public static File loadFileFromDisk() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Game of Life pattern file");
        File returnFile = chooser.showOpenDialog(null);
        if (returnFile != null) {

            return returnFile;
        } else {

            System.out.println("User aborted");
            return null;
        }
    }

    /**
     * Finds a .rle file through URL from user input.
     * @return File at specified URL.
     * @throws IOException
     */
    public static InputStream loadFileFromURL() throws IOException{

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Please enter the destination URL to your Game of Life .rle pattern file");
        Optional<String> input = inputDialog.showAndWait();
        if (input.isPresent()) {

            String url = input.get();
            URL destination = new URL(url);
            URLConnection conn = destination.openConnection();
            InputStream returnStream = conn.getInputStream();
            return returnStream;
        } else {
            return null;
        }
    }

    /**
     * Breaks the data from a .rle file into Strings and returns them in a HashMap
     * to use as input for further breakdown and implementation.
     * @param r .rle file as Reader.
     * @return HashMap containing Strings for title, origin, comments, width, height, rules and pattern.
     * @throws IOException
     */
    public static HashMap<String, String> readFile(Reader r) throws IOException {

        HashMap<String, String> fileData = new HashMap<>();
        StringBuilder fileStringBuilder = new StringBuilder();
        int data = r.read();
        while (data != -1) {

            char exitChar = (char) data;
            fileStringBuilder.append(exitChar);
            data = r.read();
        }
        String fileString = new String(fileStringBuilder);
        int i = 0;

        //Sift out title and comments
        String title = null;
        String origin = null;
        StringBuilder commentBuilder = new StringBuilder();
        while (fileString.indexOf(35, i) != -1) {

            int hashTag = fileString.indexOf(35, i);
            char nextChar = fileString.charAt(hashTag + 1);
            int endOfLine = fileString.indexOf(10, i);
            i = endOfLine + 1;
            switch (nextChar) {

                case 78:
                    title = new String(fileString.substring(hashTag + 2, endOfLine));
                    break;
                case 79:
                    origin = new String(fileString.substring(hashTag + 2, endOfLine));
                    break;
                case 67:
                    commentBuilder.append(fileString.substring(hashTag + 2, endOfLine));
                    break;
                default:
                    break;
            }
        }
        String comments = new String(commentBuilder);
        fileData.put("title", title);
        fileData.put("origin", origin);
        fileData.put("comments", comments);

        // Find x-size
        int x = fileString.indexOf(120, i);
        int comma = fileString.indexOf(44, x);
        String widthSubString = fileString.substring(x,comma);
        fileData.put("width", widthSubString);


        // Find y-size
        int y = fileString.indexOf(121, i);
        comma = fileString.indexOf(44, y);
        String heightSubString = fileString.substring(y,comma);
        fileData.put("height", heightSubString);

        // Find rules
        if (fileString.contains("rule")) {

            int rulesIndex = fileString.indexOf("rule");
            int rulesEndIndex = fileString.indexOf(10, rulesIndex);
            String rulesString = fileString.substring(rulesIndex, rulesEndIndex);
            fileData.put("rules", rulesString);
        }

        // Extract Game of Life pattern
        int endOfLine = fileString.indexOf(10, i);
        i = endOfLine + 1;
        String patternString = fileString.substring(i);
        fileData.put("pattern", patternString);

        return fileData;
    }


    /**
     * Takes a String containing board dimension data from the loaded .rle file, isolates it and returns it as int.
     * @param inputString a String describing the width or height data.
     * @return dimension as int.
     * @throws IOException
     */
    public static int readDimension(String inputString) throws IOException {

        Scanner dimensionScanner = new Scanner(inputString);
        dimensionScanner.useDelimiter(" ");
        boolean isDone = false;
        int dimension = 0;
        while (!isDone && dimensionScanner.hasNext()) {
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

    /**
     * Takes a String containing rule data from the loaded .rle file, identifies birth and survival cases of
     * the loaded .rle file and place them in arrays as int values.
     * @param rulesString a String describing rule data .
     * @return array of int arrays with birth and survival rule cases.
     */
    public static int[][] readRules(String rulesString) {

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

        int[][] rules = {bornAmount, surviveAmount};
        return rules;
    }


    /**
     * Converts a string containing cell pattern from the loaded .rle file into a two dimensional byte array
     * that represents the cell pattern.
     * @param patternString a String of cell pattern data.
     * @param width width of the pattern. Determines size of first dimension of array.
     * @param height height of the pattern. Determines size of second dimension of array.
     * @return two dimensional byte array where value 0 represents a dead cell and value 1 represents a live cell.
     * @throws IOException
     */
    public static byte[][] readPattern(String patternString, int width, int height) throws IOException {

        // Create scanner to break pattern into rows
        Scanner patternScanner = new Scanner(patternString);
        patternScanner.useDelimiter("\\$");
        byte[][] board = new byte[height][width];
        int y = 0;

        // Create charArray with pattern info for each row
        while (patternScanner.hasNext()) {
            String row = new String(patternScanner.next());
            char[] charArray = row.toCharArray();
            String cellCountString = new String("");
            int x = 0;

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
                            board[y][x] = 0;
                            x++;
                        } else {
                            int cellCountInt = Integer.parseInt(cellCountString);
                            for (int j = 0; j < cellCountInt; j++) {
                                board[y][x] = 0;
                                x++;
                            }
                        }
                        cellCountString = "";
                    }

                    // Alive cell
                    else if (charArray[i] == 111) {
                        if (cellCountString.equals("")) {
                            board[y][x] = 1;
                            x++;
                        } else {
                            int cellCountInt = Integer.parseInt(cellCountString);
                            for (int j = 0; j < cellCountInt; j++) {
                                board[y][x] = 1;
                                x++;
                            }
                        }
                        cellCountString = "";
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException e) {
                //
            }
            y++;
        }
        return board;
    }

}