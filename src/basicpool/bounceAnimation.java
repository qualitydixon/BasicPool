/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicpool;

import static basicpool.Config.DURATION;
import static basicpool.Config.OFFSET;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Circle;

/**
 *
 * @author Mike
 */
public class bounceAnimation {
    
    Timeline timeline = new Timeline();
    
    public void startAnimation(Circle circle) {
        
            timeline.setCycleCount(Config.UNITY);
            timeline.setAutoReverse(true);

            final KeyValue kv;
            kv = new KeyValue(
                    circle.translateYProperty(),
                    30,
                    Interpolator.SPLINE(0.05f, 0, 0.5f, 0));
            final KeyFrame kf;
            kf = new KeyFrame(DURATION, kv);
            timeline.getKeyFrames().setAll(kf);
            timeline.playFromStart();
            
            timeline.setOnFinished(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    timeline.playFromStart();
                }
            });
    }
    
    public void stop() {
        timeline.stop();
    }
    
}
