package model;

import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Class handling logic related to input from Run-Length Encoding files.
 */
public class FileManagement {

    /**
     * Finds a local .rle file through user interaction with FileChooser.
     * @return the file at the specified location.
     */
    public static File loadFileFromDisk() {

        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("RLE Files (*.rle)", "*.rle"));
        chooser.setTitle("Choose Game of Life pattern file");
        File returnFile = chooser.showOpenDialog(null);
        if (returnFile != null) {

            return returnFile;

        } else {

            return null;
        }
    }

    /**
     * Create an inputStream from a URL specified by the user in an inputDialog.
     * @return InputStream if created
     */
    public static InputStream loadFileFromURL(){

        try {
            // Show text input dialog
            TextInputDialog inputDialog = new TextInputDialog();
            inputDialog.setHeaderText("Please enter the URL to your Game of Life .rle pattern file");
            Optional<String> input = inputDialog.showAndWait();
            if (input.isPresent()) {

                // Connect to URL and create InputStream
                String url = input.get();
                URL destination = new URL(url);
                URLConnection conn = destination.openConnection();
                InputStream returnStream = conn.getInputStream();
                return returnStream;

            } else {

                return null;
            }

        // Show message if invalid URL
        } catch (MalformedURLException mURLe) {

            CustomDialog errMsg = new CustomDialog("Error opening file from URL", true,
                    "There was a problem opening the specified URL.\r\n" +
                            "Please make sure you have entered the correct address.");
            return null;

        // Show message if connection could not be set up
        } catch (IOException ioe) {

            CustomDialog errMsg = new CustomDialog("Error connecting to URL", true,
                    "There was a problem connecting to the specified URL.");
            return null;
        }
    }

    /**
     * Returns the data located in .rle file broken up into separate strings ready for application
     * or further manipulation.
     * @param r the reader object created from local file or URL
     * @return hashmap of strings containing atomic data
     * @throws IOException if the reader input is corrupted
     */
    public static HashMap<String, String> readFile(Reader r, String absoluteFilePath) throws IOException {

        HashMap<String, String> fileData = new HashMap<>();
        StringBuilder fileStringBuilder = new StringBuilder();

        fileData.put("File Path", absoluteFilePath);

        // Reads the file and builds a String with its contents off which the rest of the method is based on.
        int data = r.read();
        while (data != -1) {

            char exitChar = (char) data;
            fileStringBuilder.append(exitChar);
            data = r.read();
        }
        String fileString = new String(fileStringBuilder);
        int index = 0;

        // Sifts out titles, origins and comments located in a .rle-file.
        String title = null;
        String origin = null;
        StringBuilder commentBuilder = new StringBuilder();
        while (fileString.indexOf(35, index) != -1) {

            int hashTag = fileString.indexOf(35, index);
            char nextChar = fileString.charAt(hashTag + 1);
            int endOfLine = fileString.indexOf(10, index);
            index = endOfLine + 1;
            switch (nextChar) {

                case 78:
                    title = fileString.substring(hashTag + 2, endOfLine);
                    break;
                case 79:
                    origin = fileString.substring(hashTag + 2, endOfLine);
                    break;
                case 67:
                    commentBuilder.append(fileString.substring(hashTag + 2, endOfLine));
                    commentBuilder.append("\n");
                    break;
                default:
                    break;
            }
        }

        String comments = new String(commentBuilder);
        fileData.put("title", title);
        fileData.put("origin", origin);
        fileData.put("comments", comments);

        try {
        // Finds the size of the x-coordinates a .rle file.
            int x = fileString.indexOf(120, index);
            int comma = fileString.indexOf(44, x);
            String widthSubString = fileString.substring(x, comma);
            fileData.put("width", widthSubString);

        // Finds the size of the y-coordinates in a .rle file.
            int y = fileString.indexOf(121, index);
            comma = fileString.indexOf(44, y);
            String heightSubString = fileString.substring(y, comma);
            fileData.put("height", heightSubString);
        }
        catch(IndexOutOfBoundsException e) {
            //Wont set any values to height or width if the format isn't supported.´
            //A message of the error is sent to the user in a CustomDialog window with info on how to correct it.
            //This is done in the confirmFileData method in MainScreenController.java
        }

        // Finds the rules in a .rle file.
        if (fileString.contains("rule")) {

            int rulesIndex = fileString.indexOf("rule", index);
            int rulesEndIndex = fileString.indexOf(10, rulesIndex);
            String rulesString = fileString.substring(rulesIndex, rulesEndIndex);
            fileData.put("rules", rulesString);
        }

        // Extracts a Game of Life board pattern.
        int endOfLine = fileString.indexOf(10, index);
        index = endOfLine + 1;
        String patternString = fileString.substring(index);
        fileData.put("pattern", patternString);

        return fileData;
    }

    /**
     * Takes a String containing board dimension data from the loaded .rle file, isolates it and returns it as int.
     * @param inputString a String describing the width or height data
     * @return dimension as int
     */
    public static int readDimension(String inputString) {

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
     * Takes a String containing rule data from the loaded .rle file, identifies birth and survival rules and
     * returns them in lists.
     * @param rulesString a String describing rule data.
     * @return wrapper ArrayList containing the two LinkedLists of rules
     */
    public static ArrayList<LinkedList<Byte>> readRules(String rulesString) {

        LinkedList<Byte> birthRules = new LinkedList<>();
        LinkedList<Byte> survivalRules = new LinkedList<>();

        //Trims out "rules" in order to avoid conflict when searching for the index of "s" later in the method.
        rulesString = rulesString.replace("rules", "");


        char[] chars = rulesString.toCharArray();
        boolean survivalPartOfRules = false;
        for (char c : chars) {

            // Check for "/", "S" or "s" which separates birth and survival rules in rulesString
            if (c == 47 || c == 83 || c == 115) {

                survivalPartOfRules = true;

            // Insert numbers found of value 0-8 as rules in the correct linkedlist
            } else if (c > 47 && c < 57){

                String ruleNumber = Character.toString(c);
                byte rule = Byte.parseByte(ruleNumber);
                if (!survivalPartOfRules) {

                    birthRules.add(rule);

                } else {

                    survivalRules.add(rule);
                }
            }
        }

        // Wrap and return rule lists
        ArrayList<LinkedList<Byte>> rulesWrapper = new ArrayList<>();
        rulesWrapper.add(birthRules);
        rulesWrapper.add(survivalRules);
        return rulesWrapper;
    }


    /**
     * Converts a string containing cell pattern from the loaded .rle file into a two-dimensional byte array
     * that represents the cell pattern.
     * @param patternString a String of cell pattern data
     * @param width width of the pattern. Determines size of first dimension of array
     * @param height height of the pattern. Determines size of second dimension of array
     * @return two dimensional byte array where value 0 represents a dead cell and value 1 represents a live cell.
     */
    public static byte[][] readPatternStaticBoard(String patternString, int height, int width) {

        // Create scanner to break pattern into rows
        Scanner patternScanner = new Scanner(patternString);
        patternScanner.useDelimiter("\\$");
        byte[][] board = new byte[height][width];
        int y = 0;

        // Create charArray with pattern info for each row
        while (patternScanner.hasNext()) {
            String row = new String(patternScanner.next());
            char[] charArray = row.toCharArray();
            String cellCountString = new String();
            int x = 0;

            // Go through each row char by char
            for (int i = 0; i < charArray.length; i++) {

                // Numbers found
                if (charArray[i] >= 48 && charArray[i] <= 57) {
                    cellCountString = cellCountString + charArray[i];
                }

                // Dead cells found
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

                // Alive cell found
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
            y++;
        }
        return board;
    }


    /**
     * Converts a string containing cell pattern from the loaded .rle file into a two-dimensional list
     * that represents the cell pattern.
     * @param patternString a String of cell pattern data.
     * @param width width of the pattern
     * @param height height of the pattern
     * @return two dimensional ArrayList where value 0 represents a dead cell and value 1 represents a live cell.
     * @throws IOException
     */
    public static ArrayList<ArrayList<Byte>> readPatternDynamicBoard(String patternString, int height, int width) throws IOException {

        // Create scanner to break pattern into rows
        Scanner patternScanner = new Scanner(patternString);
        patternScanner.useDelimiter("\\$");
        ArrayList<ArrayList<Byte>> board = new ArrayList<>();

        // Create charArray with pattern info for each row
        for (int y = 0; y < height; y++) {
            if (patternScanner.hasNext()) {
                String row = new String(patternScanner.next());
                board.add(y, new ArrayList<>());
                char[] charArray = row.toCharArray();
                String repeatingCellCntrString = new String();
                int x = 0;

                // Go through each row char by char

                for (int i = 0; i < charArray.length; i++) {

                    // Char processed is a number (ascii code 48-57)
                    if (charArray[i] >= 48 && charArray[i] <= 57) {
                        repeatingCellCntrString = repeatingCellCntrString + charArray[i];
                    }

                    // Char processed is a "b" (ascii code 98), representing a dead cell
                    else if (charArray[i] == 98) {

                        if (repeatingCellCntrString.equals("")) {
                            board.get(y).add(x, (byte) 0);
                            x++;
                        } else {
                            int cellCountInt = Integer.parseInt(repeatingCellCntrString);
                            for (int j = 0; j < cellCountInt; j++) {
                                board.get(y).add(x, (byte) 0);
                                x++;
                            }
                        }
                        repeatingCellCntrString = "";
                    }

                    // Cell processed is an "o" (ascii code 111), representing a live cell
                    else if (charArray[i] == 111) {
                        if (repeatingCellCntrString.equals("")) {
                            board.get(y).add(x, (byte) 1);
                            x++;
                        } else {
                            int cellCountInt = Integer.parseInt(repeatingCellCntrString);
                            for (int j = 0; j < cellCountInt; j++) {
                                board.get(y).add(x, (byte) 1);
                                x++;
                            }
                        }
                        repeatingCellCntrString = "";
                    }
                }

                // Add dead cell to arraylist if width dimension calls for it
                while (x < width) {
                    board.get(y).add(x, (byte) 0);
                    x++;
                }

            } else {
                //append empty row if height dimension calls for it
                board.add(y, new ArrayList<>());
                for (int x = 0; x < width; x++) {

                    board.get(y).add(x, (byte) 0);
                }
            }
        }
        return board;
    }
}