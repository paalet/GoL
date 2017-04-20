package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.*;
import java.awt.TextArea;
import java.awt.TextField;

/**
 * Created by simenperschandersen on 19.04.2017.
 */
public class FileEditor {
    @FXML
    private TextField saveWindowTitleField;
    @FXML
    private TextField saveWindowOriginField;
    @FXML
    private TextField saveWindowCommentsField;

    public void setTitle(String title) {
        saveWindowTitleField.setText(title);

    }

    public void setOrigin(String origin) {
        saveWindowOriginField.setText(origin);

    }
    public void setComments(String comments) {
        saveWindowCommentsField.setText(comments);

    }


}
