package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.GoL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 */
public class RulesEditorController implements Initializable {

    @FXML
    private Button b0Btn;

    @FXML
    private Button b1Btn;

    @FXML
    private Button b2Btn;

    @FXML
    private Button b3Btn;

    @FXML
    private Button b4Btn;

    @FXML
    private Button b5Btn;

    @FXML
    private Button b6Btn;

    @FXML
    private Button b7Btn;

    @FXML
    private Button b8Btn;

    @FXML
    private Button s0Btn;

    @FXML
    private Button s1Btn;

    @FXML
    private Button s2Btn;

    @FXML
    private Button s3Btn;

    @FXML
    private Button s4Btn;

    @FXML
    private Button s5Btn;

    @FXML
    private Button s6Btn;

    @FXML
    private Button s7Btn;

    @FXML
    private Button s8Btn;

    private String highlightColor = "#6f9fed";
    private LinkedList<Byte> birthRules;
    private LinkedList<Byte> survivalRules;

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        // Import current rules
        birthRules = GoL.getBirthRules();
        survivalRules = GoL.getSurvivalRules();

        // Highlight buttons representing currently implemented rules
        for (int rule : birthRules) {
            switch (rule) {
                case 0:
                    b0Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 1:
                    b1Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 2:
                    b2Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 3:
                    b3Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 4:
                    b4Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 5:
                    b5Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 6:
                    b6Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 7:
                    b7Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 8:
                    b8Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
            }
        }
        for (int rule : survivalRules) {

            switch (rule) {
                case 0:
                    s0Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 1:
                    s1Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 2:
                    s2Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 3:
                    s3Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 4:
                    s4Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 5:
                    s5Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 6:
                    s6Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 7:
                    s7Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
                case 8:
                    s8Btn.setStyle("-fx-background-color: " + highlightColor);
                    break;
            }
        }
    }

    public void okEvent() {

        // Sort rules in ascending order. Done with Array.

        // Convert to Array and sort.
        byte[] birthRulesArray = new byte[birthRules.size()];
        for (int i = 0; i < birthRulesArray.length; i++) {
            birthRulesArray[i] = birthRules.get(i);
        }
        byte[] survivalRulesArray = new byte[survivalRules.size()];
        for (int i = 0; i < survivalRulesArray.length; i++) {
            survivalRulesArray[i] = survivalRules.get(i);
        }

        Arrays.sort(birthRulesArray);
        Arrays.sort(survivalRulesArray);

        // Place sorted rules back into List
        birthRules.clear();
        survivalRules.clear();
        for (byte rule : birthRulesArray) {

            birthRules.add(rule);
        }
        for (byte rule : survivalRulesArray) {

            survivalRules.add(rule);
        }

        // Close the stage
        Stage ruleEdStage = (Stage) b0Btn.getScene().getWindow();
        ruleEdStage.close();
    }

    public void b0Event() {

        if (birthRules.contains((byte) 0)) {
            birthRules.remove(birthRules.indexOf((byte) 0));
            b0Btn.setStyle("");

        } else {
            birthRules.add((byte) 0);
            b0Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }
    
    public void b1Event() {

        if (birthRules.contains((byte) 1)) {
            birthRules.remove(birthRules.indexOf((byte) 1));
            b1Btn.setStyle("");

        } else {
            birthRules.add((byte) 1);
            b1Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void b2Event() {

        if (birthRules.contains((byte) 2)) {
            birthRules.remove(birthRules.indexOf((byte) 2));
            b2Btn.setStyle("");

        } else {
            birthRules.add((byte) 2);
            b2Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void b3Event() {

        if (birthRules.contains((byte) 3)) {
            birthRules.remove(birthRules.indexOf((byte) 3));
            b3Btn.setStyle("");

        } else {
            birthRules.add((byte) 3);
            b3Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void b4Event() {

        if (birthRules.contains((byte) 4)) {
            birthRules.remove(birthRules.indexOf((byte) 4));
            b4Btn.setStyle("");

        } else {
            birthRules.add((byte) 4);
            b4Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void b5Event() {

        if (birthRules.contains((byte) 5)) {
            birthRules.remove(birthRules.indexOf((byte) 5));
            b5Btn.setStyle("");

        } else {
            birthRules.add((byte) 5);
            b5Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void b6Event() {

        if (birthRules.contains((byte) 6)) {
            birthRules.remove(birthRules.indexOf((byte) 6));
            b6Btn.setStyle("");

        } else {
            birthRules.add((byte) 6);
            b6Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void b7Event() {

        if (birthRules.contains((byte)7)) {
            birthRules.remove(birthRules.indexOf((byte) 7));
            b7Btn.setStyle("");

        } else {
            birthRules.add((byte) 7);
            b7Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void b8Event() {

        if (birthRules.contains((byte) 8)) {
            birthRules.remove(birthRules.indexOf((byte) 8));
            b8Btn.setStyle("");

        } else {
            birthRules.add((byte) 8);
            b8Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s0Event() {

        if (survivalRules.contains((byte) 0)) {
            survivalRules.remove(survivalRules.indexOf((byte) 0));
            s0Btn.setStyle("");

        } else {
            survivalRules.add((byte) 0);
            s0Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s1Event() {

        if (survivalRules.contains((byte) 1)) {
            survivalRules.remove(survivalRules.indexOf((byte) 1));
            s1Btn.setStyle("");

        } else {
            survivalRules.add((byte) 1);
            s1Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s2Event() {

        if (survivalRules.contains((byte) 2)) {
            survivalRules.remove(survivalRules.indexOf((byte) 2));
            s2Btn.setStyle("");

        } else {
            survivalRules.add((byte) 2);
            s2Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s3Event() {

        if (survivalRules.contains((byte) 3)) {
            survivalRules.remove(survivalRules.indexOf((byte) 3));
            s3Btn.setStyle("");

        } else {
            survivalRules.add((byte) 3);
            s3Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s4Event() {

        if (survivalRules.contains((byte) 4)) {
            survivalRules.remove(survivalRules.indexOf((byte) 4));
            s4Btn.setStyle("");

        } else {
            survivalRules.add((byte) 4);
            s4Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s5Event() {

        if (survivalRules.contains((byte) 5)) {
            survivalRules.remove(survivalRules.indexOf((byte) 5));
            s5Btn.setStyle("");

        } else {
            survivalRules.add((byte) 5);
            s5Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s6Event() {

        if (survivalRules.contains((byte) 6)) {
            survivalRules.remove(survivalRules.indexOf((byte) 6));
            s6Btn.setStyle("");

        } else {
            survivalRules.add((byte) 6);
            s6Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s7Event() {

        if (survivalRules.contains((byte) 7)) {
            survivalRules.remove(survivalRules.indexOf((byte) 7));
            s7Btn.setStyle("");

        } else {
            survivalRules.add((byte) 7);
            s7Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }

    public void s8Event() {

        if (survivalRules.contains((byte) 8)) {
            survivalRules.remove(survivalRules.indexOf((byte) 8));
            s8Btn.setStyle("");

        } else {
            survivalRules.add((byte) 8);
            s8Btn.setStyle("-fx-background-color: " + highlightColor);
        }
    }
}
