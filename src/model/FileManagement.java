package model;

import java.io.IOException;
import java.io.Reader;
import java.io.SyncFailedException;
import java.util.Scanner;
import model.GoL;

/**
 * Created by simenperschandersen on 07.03.2017.
 */
public class FileManagement {


    public static void readTitle(String inputString) throws IOException {
        System.out.println("Title: " + inputString);
        /*
        StringBuilder titleString = new StringBuilder();
        int data = r.read();
        while (data != 10) {
            char exitChar = (char) data;
            titleString.append(exitChar);
            data = r.read();
        }
        String titleStringResult = new String(titleString);
        System.out.println("Title: " + titleStringResult);
        */
    }

    public static void readComment(String inputString) throws IOException {
        System.out.println("Comment: " + inputString);
        /*
        StringBuilder commentString = new StringBuilder();
        int data = r.read();
        while (data != 10) {
            char exitChar = (char) data;
            commentString.append(exitChar);
            data = r.read();
        }
        String commentStringResult = new String(commentString);
        System.out.println("Comment: " + commentStringResult);
        */

    }

    public static void readOrigin(String inputString) throws IOException {
        System.out.println("Origin; " + inputString);
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
   /*         char indexChar = rulesString.charAt(index);
            int indexInt = (int) indexChar;
            System.out.println(indexInt);
  */          surviveNumbers++;
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

    public static void readPattern(String patternString) {

        Scanner patternScanner = new Scanner(patternString);
        patternScanner.useDelimiter("\\$");
        while (patternScanner.hasNext()) {
            String line = new String(patternScanner.next());

        }

    }
}