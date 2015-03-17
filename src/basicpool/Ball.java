/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicpool;

import static basicpool.Config.RADIUS;
import java.util.ArrayList;
import java.util.stream.DoubleStream;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Mike
 */
class Ball extends Circle {
    
    String name;
    String color;
    double mass = 1;
    double velocity[] = {0.0, 0.0};
    
    double acceleration[] = {0.0, 0.0};
    double[] initialPosition = {0.0, 0.0};
    Line top, bottom, left, right;
    double deltaX = 1;
    double deltaY = 1;
    
    public static double motion;
    
    public Ball(int radius, Color col, double startPos[], String name) {
        super(radius, col);
        this.initialPosition[0] = startPos[0];
        this.initialPosition[1] = startPos[1];
        this.name = name;
        top = new Line(this.getCenterX(), this.getCenterY(), this.getCenterX(), 222);
        bottom = new Line(this.getCenterX(), this.getCenterY(), this.getCenterX(), 545);
        left = new Line(this.getCenterX(), this.getCenterY(), 283, this.getCenterY());
        right = new Line(this.getCenterX(), this.getCenterY(), 997, this.getCenterY());
        top.setStroke(Color.TRANSPARENT);
        bottom.setStroke(Color.TRANSPARENT);
        left.setStroke(Color.TRANSPARENT);
        right.setStroke(Color.TRANSPARENT);
        
    }
    
    public double getVX() {
        return this.velocity[0];
    }
    public double getVY() {
        return this.velocity[1];
    }
    
    public void setVX(double newVX) {
        this.velocity[0] = newVX;
    }
    
    public void setVY(double newVY) {
        this.velocity[1] = newVY;
    }
    
    public double getTV() {
        return DoubleStream.of(this.velocity).sum();
    }
    
    boolean checkInPocket() {
        if(this.getCenterY() < 222 || this.getCenterY() > 545) return true;
        else return false;
    }
    
    static boolean checkMotion(ArrayList<Ball> l) {
        motion = 0;
        for(Ball item : l) {
            motion += item.getTV();
        }
        if(motion == 0) return true;
        else return false;
    }
}
