package model;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

/**
 * Created by simenperschandersen on 07.03.2017.
 */
public class FileManagement {

    public static void readTitle(Reader r) throws IOException {
        StringBuilder titleString = new StringBuilder();
        int data = r.read();
        while (data != 10) {
            char exitChar = (char) data;
            titleString.append(exitChar);
            data = r.read();
        }
        String titleStringResult = new String(titleString);
        System.out.println("Title: " + titleStringResult);
    }

    public static void readComment(Reader r) throws IOException {
        StringBuilder commentString = new StringBuilder();
        int data = r.read();
        while (data != 10) {
            char exitChar = (char) data;
            commentString.append(exitChar);
            data = r.read();
        }
        String commentStringResult = new String(commentString);
        System.out.println("Comment: " + commentStringResult);

    }


    public static String readDimensions(Reader r, int data) throws IOException {
        StringBuilder dimensionString = new StringBuilder();
        while (data != 114) {
            char exitChar = (char) data;
            dimensionString.append(exitChar);
            data = r.read();
        }
        String dimensionStringResult = new String(dimensionString);
        return dimensionStringResult;
    }


    public static void implementDimensions(String d, StaticBoard staticBoard) throws IOException {
        Scanner dimensionScanner = new Scanner(d);
        dimensionScanner.useDelimiter(",| ");
        boolean widthIsSet = false;
        boolean isDone = false;
        while (dimensionScanner.hasNext() && !isDone) {
            if (dimensionScanner.hasNextInt() && !widthIsSet) {
                staticBoard.setWIDTH(dimensionScanner.nextInt());
                widthIsSet = true;
            }
            else if (dimensionScanner.hasNextInt() && widthIsSet) {
                staticBoard.setHEIGHT(dimensionScanner.nextInt());
                isDone = true;
            }
            else{
                dimensionScanner.next();
            }
        }
    }


    public static String readRules(Reader r, int data) throws IOException {
        StringBuilder ruleString = new StringBuilder();
        while (data != 10) {
            char exitChar = (char) data;
            ruleString.append(exitChar);
            data = r.read();
        }
        String ruleStringResult = new String(ruleString);
        return ruleStringResult;
    }


    public static void implementRules(String rs) throws IOException {
        Reader rr = new StringReader(rs);
        int stringLength = rs.length();
        int i;
        for (i = 0; i < stringLength; i++) {
            int data = rr.read();
            // Tester om det leses inn siffer
            if (data >= 48 && data <= 57) {
                System.out.println(data);
            }
        }
    }
}
