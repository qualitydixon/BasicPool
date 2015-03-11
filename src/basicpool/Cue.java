/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicpool;

import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

/**
 *
 * @author Mike
 */
public class Cue extends Line {
    
    double cueTheta = 0;
    boolean cuePresent;
    double power = 0;
    double MAXPOWER = 900;
    
    public Cue(Ball cb) {
        super(); 
        super.setStrokeWidth(5);
        super.setStartX(cb.getCenterX() - 150);
        super.setStartY(cb.getCenterY() - 150);
        super.setEndX(cb.getCenterX());
        super.setEndY(cb.getCenterY());
    }


    void increasePower() {
        if(this.power < MAXPOWER) this.power += 25;
        System.out.println("Power: " + this.power);
    }
    
    void decreasePower() {
        if(this.power > 0) this.power -= 25;
        System.out.println("Power: " + this.power);
    }
    
    double getCueTheta() {
        return cueTheta;
    }
    void setPresent(boolean state) {
        cuePresent = state;
    }
    
    boolean getPresent() {
        return cuePresent;
    }
    
    void setCueTheta(double newCueTheta) {
        cueTheta = newCueTheta;
        return;
    }
    
    void reposition(Ball cb) {
        this.setStartX(cb.getCenterX() - 150);
        this.setStartY(cb.getCenterY() - 150);
        this.setEndX(cb.getCenterX());
        this.setEndY(cb.getCenterY());
    }
    
    void shoot(Ball ball1) {
        
        Point2D transformedEnd = this.localToScene(this.getStartX(), this.getStartY());
        
        double diffX = (ball1.getCenterX() - transformedEnd.getX());
        double diffY = (ball1.getCenterY() - transformedEnd.getY());

        System.out.println("diffX " + diffX + " diffY " + diffY);

        if(abs(diffX) < .001) diffX = 0;
        if(abs(diffY) < .001) diffY = 0;

        double nMag = sqrt(pow(diffX,2) + pow(diffY,2));

        double nX = diffX/nMag;
        double nY = diffY/nMag;
        System.out.println("nX " + nX + " nY " + nY);

        if(abs(diffX) < .001) nX = 0;
        if(abs(diffY) < .001) nY = 0;
        
        ball1.setVX(power*nX);
        ball1.setVY(power*nY);
        
    }
    
}
