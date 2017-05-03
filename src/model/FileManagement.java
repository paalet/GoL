package model;

import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


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
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("RLE Files (*.rle)", "*.rle"));
        chooser.setTitle("Choose Game of Life pattern file");
        File returnFile = chooser.showOpenDialog(null);
        if (returnFile != null) {

            return returnFile;
            
        } else {

            return null;
        }
    }

    /**
     * Returns an inputStream from a URL the user types in an inputDialog.
     * @return
     * @throws IOException
     */
    public static InputStream loadFileFromURL() throws IOException{

        try {
            TextInputDialog inputDialog = new TextInputDialog();
            inputDialog.setHeaderText("Please enter the URL to your Game of Life .rle pattern file");
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
        } catch (MalformedURLException e) {
            CustomDialog errMsg = new CustomDialog("Error opening file from URL", true,
                    "There was a problem opening the specified URL.\r\n" +
                            "Please make sure you have entered the correct address.");
            return null;
        }

    }

    /**
     * Returns a HashMap of the data located in .rle file broken up into separate strings ready for application
     * or further manipulation.
     * @param r
     * @return

     * @throws IOException
     */
    public static HashMap<String, String> readFile(Reader r, String absoluteFilePath) throws IOException {

        HashMap<String, String> fileData = new HashMap<>();
        StringBuilder fileStringBuilder = new StringBuilder();

        fileData.put("File Path", absoluteFilePath);

        /**
         * Reads the file and builds a String with its contents off which the rest of the method is based on.
         */
        int data = r.read();
        while (data != -1) {

            char exitChar = (char) data;
            fileStringBuilder.append(exitChar);
            data = r.read();
        }
        String fileString = new String(fileStringBuilder);
        int index = 0;

        /**
         * Sifts out titles, origins and comments located in a .rle-file.
         */
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

            /**
             * Finds the size of the x-coordinates a .rle file.
             */
            int x = fileString.indexOf(120, index);
            int comma = fileString.indexOf(44, x);
            String widthSubString = fileString.substring(x, comma);
            fileData.put("width", widthSubString);


            /**
             * Finds the size of the y-coordinates in a .rle file.
             */
            int y = fileString.indexOf(121, index);
            comma = fileString.indexOf(44, y);
            String heightSubString = fileString.substring(y, comma);
            fileData.put("height", heightSubString);
        }
        catch(IndexOutOfBoundsException e) {
            //Wont set any values to height or width if the format isnt supported.´
            //A message of the error is sent to the user in a CustomDialig window with info on how to correct it.
            //This is done in the confirmFileData method in MainScreenController.java
        }

        /**
         * Finds the rules in a .rle file.
         */
        if (fileString.contains("rule")) {

            int rulesIndex = fileString.indexOf("rule", index);
            int rulesEndIndex = fileString.indexOf(10, rulesIndex);
            String rulesString = fileString.substring(rulesIndex, rulesEndIndex);
            fileData.put("rules", rulesString);
        }

        /**
         * Extracts a Game of Life board pattern.
         */
        int endOfLine = fileString.indexOf(10, index);
        index = endOfLine + 1;
        String patternString = fileString.substring(index);
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

        // Wrap and return rule linkedlists
        ArrayList<LinkedList<Byte>> rulesWrapper = new ArrayList<>();
        rulesWrapper.add(birthRules);
        rulesWrapper.add(survivalRules);
        return rulesWrapper;


        /*
        //Trims out "rules" in order to avoid conflict when searching for the index of "s" later in the method.
        rulesString = rulesString.replace("rules", "");

        //boolean variable that will remain true if everything goes as planned.
        boolean allOk = true;

        //Counts the amount of byte values and makes a new array with a fitting size to fit that amount of values
        //index represents the index of the char-value b/B
        int stringLength = rulesString.length();
        int index = rulesString.indexOf(98);
        if(index == -1) {
            index = rulesString.indexOf(66);
            if(index == -1) {
                allOk = false;
            }
        }
        index++;
        int bornNumbers = 0;
        if (allOk) {
                while (rulesString.charAt(index) != 47 && rulesString.charAt(index) != 10 && rulesString.charAt(index) != 32) {
                    bornNumbers++;
                    index++;
                }

        }
        int [] bornAmount = new int[bornNumbers];


        //Sets the byte values in the array with a for-loop
        //index represents the index of the char-value b/B
        index = rulesString.indexOf(98);
        if(index == -1) {
            index = rulesString.indexOf(66);
            if(index == -1) {
                allOk = false;
            }
        }
        index++;
        if(allOk) {
            for (int i = 0; i < bornNumbers; i++) {
                char charAtIndex = rulesString.charAt(index);
                int numberAtIndex = Character.getNumericValue(charAtIndex);
                bornAmount[i] = numberAtIndex;
                index++;
            }
        }

        //Counts the amount of byte values and makes a new array with a fitting size to fit that amount of values
        //index represents the index of the char-value s/S
        index = rulesString.indexOf(115);
        if(index == -1) {
            index = rulesString.indexOf(83);
            if (index == -1) {
                allOk = false;
            }
        }
        index++;
        int surviveNumbers = 0;
        if(allOk) {
            while (index < stringLength) {
                surviveNumbers++;
                index++;
            }
        }
        int [] surviveAmount = new int[surviveNumbers];

        //Sets the byte values in the array with a for-loop
        index = rulesString.indexOf(115);
        if(index == -1) {
            index = rulesString.indexOf(83);
            if (index == -1) {
                allOk = false;
            }
        }
        index++;
        if(allOk) {
            for (int i = 0; i < surviveNumbers; i++) {
                char charAtIndex = rulesString.charAt(index);
                int numberAtIndex = Character.getNumericValue(charAtIndex);
                surviveAmount[i] = numberAtIndex;
                index++;
            }
        }

        int[][] rules = {bornAmount, surviveAmount};

        //Returns the rules-values if everything went by alright, and we could read the rules fine. Returns null if not.
        if(allOk)
        {
            return rules;
        }
        else
        {
            return null;
        }
*/
    }


    /**
     * Converts a string containing cell pattern from the loaded .rle file into a two-dimensional byte array
     * that represents the cell pattern.
     * @param patternString a String of cell pattern data.
     * @param width width of the pattern. Determines size of first dimension of array.
     * @param height height of the pattern. Determines size of second dimension of array.
     * @return two dimensional byte array where value 0 represents a dead cell and value 1 represents a live cell.
     * @throws IOException
     */
    public static byte[][] readPatternStaticBoard(String patternString, int height, int width) throws IOException {

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


    /**
     * Converts a string containing cell pattern from the loaded .rle file into a two-dimensional byte array
     * that represents the cell pattern.
     * @param patternString a String of cell pattern data.
     * @param width width of the pattern. Determines size of first dimension of array.
     * @param height height of the pattern. Determines size of second dimension of array.
     * @return two dimensional byte array where value 0 represents a dead cell and value 1 represents a live cell.
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
                try {
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
                } catch (ArrayIndexOutOfBoundsException e) {
                    //
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