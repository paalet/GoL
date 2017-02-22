package model;

/**
 * Created by Pål on 22.02.2017.
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class GoLTimeline {

    private static GoLTimeline instance = null;
    protected GoLTimeline() {
        Timeline instance = new Timeline(new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextGeneration();
                draw();
            }
        }));
    }

    public static GoLTimeline getInstance() {
        if(instance == null) {
            instance = new GoLTimeline();
        }
        return instance;
    }
}
