package controller;


import com.sun.javafx.iio.png.PNGIDATChunkInputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GifCreatorController implements Initializable {

    private Board gifBoard;

    @FXML
    private Button cancelGifBtn;

    @FXML
    private Button createGifBtn;

    public GifCreatorController(Board gameBoard) {

        if (gameBoard instanceof StaticBoard) {

            gifBoard = new StaticBoard((StaticBoard) gameBoard);
        } else if (gameBoard instanceof DynamicBoard) {

            gifBoard = new DynamicBoard((DynamicBoard) gameBoard);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialize GIF creation input values
        GifCreator.setPath("GoLgif.gif");
        GifCreator.setCellSize(30);
        GifCreator.setTimePerMilliSecond(200);
        GifCreator.calculateImageSize(gifBoard);
        GifCreator.setAliveCellColor(new java.awt.Color((float) GoL.getAliveCellColor().getRed(),
                (float) GoL.getAliveCellColor().getGreen(),
                (float) GoL.getAliveCellColor().getBlue(),
                (float) GoL.getAliveCellColor().getOpacity()));
        GifCreator.setDeadCellColor(new java.awt.Color((float) GoL.getDeadCellColor().getRed(),
                (float) GoL.getDeadCellColor().getGreen(),
                (float) GoL.getDeadCellColor().getBlue(),
                (float) GoL.getDeadCellColor().getOpacity()));

    }


    public void createGifEvent() throws Exception{

        // Let user choose file name and path
        FileChooser pathChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif");
        pathChooser.getExtensionFilters().add(extFilter);
        GifCreator.setPath(pathChooser.showSaveDialog(null).getPath());
        GifCreator.calculateImageSize(gifBoard);

        // Create GIFWriter object and write to .gif
        lieng.GIFWriter gwriter = new lieng.GIFWriter(GifCreator.getImageWidth(),
                GifCreator.getImageHeight(),
                GifCreator.getPath(),
                GifCreator.getTimePerMilliSecond());
        gwriter.setBackgroundColor(java.awt.Color.black);
        gwriter.flush();
        GifCreator.writeGif(gwriter, gifBoard, 20);

        // Five feedback to user and close stage
        String filePath = GifCreator.getPath();
        new CustomDialog("GIF created", true,
                "Your gif was made and saved at " + filePath);
        Stage gifStage = (Stage) createGifBtn.getScene().getWindow();
        gifStage.close();

    }


    public void cancelGifEvent() {

        Stage gifStage = (Stage) cancelGifBtn.getScene().getWindow();
        gifStage.close();
    }

}
