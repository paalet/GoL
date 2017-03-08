package model;

import java.io.IOException;
import java.io.Reader;
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

    public static int readDimension(Reader r) throws IOException {
        Scanner heightScanner = new Scanner(r);
        boolean isDone = false;
        int dimension = 0;
        while (heightScanner.hasNext() && !isDone) {
            if (heightScanner.hasNextInt()) {
                dimension = heightScanner.nextInt();
                isDone = true;
            }
            else{
                System.out.println(heightScanner.next() + " is no int.");
            }
        }
        return dimension;
    }
}
