/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicpool;

import static basicpool.Config.*;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.awt.event.ActionListener;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Mike
 */
public class BasicPool extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WINDOWWIDTH, WINDOWHEIGHT, Color.ALICEBLUE);
        primaryStage.setScene(scene);
        
        // Use table background image
        ImageView table = new ImageView(new Image(getClass().getResourceAsStream("/table.png")));
        table.setFocusTraversable(true);
        table.setLayoutX(240);
        table.setLayoutY(184);
        table.setFitWidth(TABLEWIDTH);
        table.setFitHeight(TABLEHEIGHT);
        root.getChildren().add(table);
        
        // Reset button
        Button reset = new Button("Reset");
        reset.setLayoutX(640);
        reset.setLayoutY(700);
        root.getChildren().add(reset);
        
        
        
        Ball cueBall = new Ball(RADIUS, Color.WHITE, CUEPOS, CUEBALL);
        Ball one = new Ball(RADIUS, Color.YELLOW, ONEPOS, ONEBALL);
        Ball two = new Ball(RADIUS, Color.BLUE, TWOPOS, TWOBALL);
        Ball three = new Ball(RADIUS, Color.RED, THREEPOS, THREEBALL);
        Ball four = new Ball(RADIUS, Color.PURPLE, FOURPOS, FOURBALL);
        Ball five = new Ball(RADIUS, Color.ORANGE, FIVEPOS, FIVEBALL);
        Ball six = new Ball(RADIUS, Color.GREEN, SIXPOS, SIXBALL);
        Ball seven = new Ball(RADIUS, Color.DARKRED, SEVENPOS, SEVENBALL);
        Ball eight = new Ball(RADIUS, Color.BLACK, EIGHTPOS, EIGHTBALL);
        
        root.getChildren().addAll(cueBall,
                                      one,
                                      two,
                                      three,
                                      four,
                                      five,
                                      six,
                                      seven,
                                      eight);
        
        
        
        ArrayList<Ball> al = new ArrayList<>();
        
        al.add(cueBall);
        al.add(one);
        al.add(two);
        al.add(three);
        al.add(four);
        al.add(five);
        al.add(six);
        al.add(seven);
        al.add(eight);
        
        for (Ball item : al) {
            item.setCenterX(table.getLayoutX() + item.initialPosition[0]);
            item.setCenterY(table.getLayoutY() + item.initialPosition[1]);
            
            root.getChildren().addAll(item.top, item.bottom, item.left, item.right);
            
        }
        
        // Create walls/pockets
        Line topLeft = new Line(311, 222, 619, 222);
        Line topRight = new Line(659, 222, 969, 222);
        Line right = new Line(997, 247, 997, 518);
        Line bottomRight = new Line(969, 545, 660, 545);
        Line bottomLeft = new Line(620, 545, 310, 545);
        Line left = new Line(283, 518, 283, 248);
        
        root.getChildren().add(topLeft);
        root.getChildren().add(topRight);
        root.getChildren().add(right);
        root.getChildren().add(bottomLeft);
        root.getChildren().add(bottomRight);
        root.getChildren().add(left);
        
        
        
        
        // Initialize map
        for(Ball item : al) {
            for(Ball item2 : al) {
                Collision.colMap.put(item, item2, Boolean.FALSE);
            }
            
        }

        // Add the cue
        Cue cue = new Cue(cueBall);
        cue.setPresent(true);
        root.getChildren().add(cue);
        
        
        
        
        // Display mouse position on screen
        Text t = new Text();
        root.setOnMouseMoved( new EventHandler<MouseEvent>() {
        
            public void handle(MouseEvent event) {              

                String msg = "x = " + event.getX() + " y = " + event.getY();
                t.setText(msg);

            }
        });
        
        Rotate rotateCCW = new Rotate(-2, cueBall.getCenterX(), cueBall.getCenterY());
        Rotate rotateCW = new Rotate(2, cueBall.getCenterX(), cueBall.getCenterY());
        
        
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent ke) {
//                System.out.println("Key Pressed: " + ke.getText());
                KeyCode code = ke.getCode();
                if(cueBall.getTV() == 0) {
                    switch( code ) {
                        case A:
                            cue.getTransforms().add(rotateCCW);
                            cue.setCueTheta((cue.getCueTheta() - 2));
                            break;
                        case W:
                            cue.getTransforms().add(rotateCW);
                            cue.setCueTheta((cue.getCueTheta() + 2));
                            break;
                        case S:
                            cue.getTransforms().add(rotateCCW);
                            cue.setCueTheta((cue.getCueTheta() - 2));
                            break;
                        case D:
                            cue.getTransforms().add(rotateCW);
                            cue.setCueTheta((cue.getCueTheta() + 2));
                            break;
                        case F:
                            cue.shoot(cueBall);
                            System.out.println(cueBall.getVX() + "    " + cueBall.getVY());
                            System.out.println(cue.getCueTheta());
                            root.getChildren().remove(cue);
                            cue.setPresent(false);
                            cue.resetPower();
//                            loop.play();
                            break;
                        case E:
                            cue.decreasePower();
                            break;
                        case R:
                            cue.increasePower();
                            break;
                    } 
                }
            }
        });
        
        Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            int x = 1;
            
            @Override
            public void handle(ActionEvent t) {
                
                for ( Ball ball1 : al) {

                    if(!Pockets.checkPockets(ball1)) {
                        if (Collision.checkWallCollisionX(ball1)) {
                            ball1.setVX(ball1.getVX() * -1); 
                        }
                    
                        if (Collision.checkWallCollisionY(ball1)) {
                            ball1.setVY(ball1.getVY() * -1);
                        }
                    } else if (Pockets.checkRemove(ball1)) {
                        root.getChildren().remove(ball1);
                        ball1.setVX(0);
                        ball1.setVY(0);
                        Collision.pottedBalls.add(ball1);
                    }
                    
                    Collision.collide(ball1, al);
                    
                    // ball1 new v/a/x/y
                    ball1.deltaX = ball1.getVX() * deltaT + .5 * ball1.acceleration[0] * Math.pow(deltaT, 2);
                    ball1.deltaY = ball1.getVY() * deltaT + .5 * ball1.acceleration[0] * Math.pow(deltaT, 2);
                    
                    if (abs(ball1.getVX()) < 1) ball1.setVX(0);
                    if (abs(ball1.getVY()) < 1) ball1.setVY(0);
                    
                    if (abs(ball1.getVX()) > 0) {ball1.acceleration[0] = -(signum(ball1.getVX()) * 25);}
                    else ball1.acceleration[0] = 0;
                    if (abs(ball1.getVY()) > 0) {ball1.acceleration[1] = -(signum(ball1.getVY()) * 25);}
                    else ball1.acceleration[1] = 0;
                    
                    ball1.velocity[0] += ball1.acceleration[0] * deltaT;
                    ball1.velocity[1] += ball1.acceleration[1] * deltaT;
                    

                    
                    ball1.setCenterX(ball1.getCenterX() + ball1.deltaX);
                    ball1.setCenterY(ball1.getCenterY() + ball1.deltaY);
                    
                    Line[] lineArray = {ball1.top, ball1.bottom, ball1.left, ball1.right};
                    for(Line l : lineArray) {
                        l.setStartX(ball1.getCenterX());
                        l.setStartY(ball1.getCenterY());
                    }
                    ball1.top.setEndX(ball1.getCenterX());
                    ball1.bottom.setEndX(ball1.getCenterX());
                    ball1.left.setEndY(ball1.getCenterY());
                    ball1.right.setEndY(ball1.getCenterY());
                    ball1.top.setEndY(222);
                    ball1.bottom.setEndY(545);
                    ball1.left.setEndX(283);
                    ball1.right.setEndX(997);
                    
                    
                }

                if(x%2 == 0) {                    
                    for(Ball item : al) {
                        for(Ball item2 : al) {
                            Collision.colMap.put(item, item2, Boolean.FALSE);
                        }
                    }
                }
                
                x += 1;
                
                if(Ball.checkMotion(al) && !cue.getPresent()) {
                    if(Collision.pottedBalls.size() != 0) {
                        if(Collision.pottedBalls.get(Collision.pottedBalls.size() - 1) == cueBall) {
                            cueBall.setCenterX(table.getLayoutX() + CUEPOS[0]);
                            cueBall.setCenterY(table.getLayoutY() + CUEPOS[1]);
                            root.getChildren().add(cueBall);
                            Collision.pottedBalls.remove(Collision.pottedBalls.size() - 1);

                        }
                    }
                    System.out.println("Cue eX:  " + cue.getEndX());
                    cue.reposition(cueBall);
                    rotateCW.setPivotX(cueBall.getCenterX());
                    rotateCCW.setPivotX(cueBall.getCenterX());
                    rotateCW.setPivotY(cueBall.getCenterY());
                    rotateCCW.setPivotY(cueBall.getCenterY());
                    System.out.println("Cue eX:  " + cue.getEndX());
                    root.getChildren().add(cue);
                    cue.setPresent(true);
//                    loop.pause();
                }

            }

        }));
        
        // Set button event
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                // rerack()
                for (Ball item : al) {
                    item.setCenterX(table.getLayoutX() + item.initialPosition[0]);
                    item.setCenterY(table.getLayoutY() + item.initialPosition[1]);
                    item.setVX(0.0);
                    item.setVY(0.0);
                }
                System.out.println("Cue eX:  " + cue.getEndX());
                cue.reposition(cueBall);
                rotateCW.setPivotX(cueBall.getCenterX());
                rotateCCW.setPivotX(cueBall.getCenterX());
                rotateCW.setPivotY(cueBall.getCenterY());
                rotateCCW.setPivotY(cueBall.getCenterY());
                if(!cue.getPresent()) root.getChildren().add(cue);
                System.out.println("Cue eX:  " + cue.getEndX());
                
                for (Ball item : Collision.pottedBalls) {
                    if(!root.getChildren().contains(item)) {
                        root.getChildren().add(item);
                    }
                }
                Collision.pottedBalls.clear();
//                loop.pause();
            }
        });
        
      
        t.setFill(Color.MEDIUMSEAGREEN);
        t.setFont(Font.font ("Verdana", 20));
        t.setX(75);
        t.setY(75);
        root.getChildren().add(t);
        
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();

        primaryStage.setTitle("Billiards!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    
    
    
    
    
    
}

    
