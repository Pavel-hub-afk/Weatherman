package sample.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.util.Duration;


public class Shake {
    private Timeline tt;
    public Shake (TextField textField) {
        tt = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(textField.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(50), new KeyValue(textField.translateXProperty(), 9))
        );
        tt.setAutoReverse(true);
        tt.setCycleCount(4);
        tt.play();
    }
}