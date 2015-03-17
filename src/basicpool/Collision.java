/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicpool;

import static basicpool.Config.RADIUS;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javafx.scene.shape.Circle;

/**
 *
 * @author Mike
 */
public class Collision {
    
//    static double nX(Ball b1, Ball b2) {
//        double diffX = (b1.getCenterX() - b2.getCenterX());
//        if(abs(diffX) < .001) diffX = 0;
//    }
    
    // create collision table
    static Table<Ball, Ball, Boolean> colMap = HashBasedTable.create();
    static ArrayList<Ball> pottedBalls = new ArrayList<>();
    
    public static boolean checkCollision(Circle ball1, Circle ball2) {
        double X1 = ball1.getCenterX();
        double Y1 = ball1.getCenterY();
        double X2 = ball2.getCenterX();
        double Y2 = ball2.getCenterY();
        if (sqrt(pow(abs(X1-X2),2)+pow(abs(Y1-Y2),2)) <= (2*RADIUS)) {
            return true;
        } else {
            return false;
        }
    }
    
    static void collide(Ball ball1, ArrayList<Ball> al) {
        for (Ball ball2 : al) {
                        if (ball2 == ball1 || pottedBalls.contains(ball2)) continue;
                        if (checkCollision(ball1, ball2)) {
                            if (colMap.get(ball2,ball1) || colMap.get(ball1,ball2)) {
                                System.out.println("Skip  " + ball2.velocity[0]);
                            } else {
                                // populate collision array entry ball1/ball2 & ball2/ball1
                                colMap.put(ball1, ball2, Boolean.TRUE);
                                colMap.put(ball2, ball1, Boolean.TRUE);
                                System.out.println("Collision: " + ball1.name + " & " + ball2.name);
                                System.out.println(ball2.velocity[0] + "    " + ball2.velocity[1]);

                                //compute new v and a
                                
                                //nX = Collision.nX(ball1, ball2)
                                double diffX = (ball1.getCenterX() - ball2.getCenterX());
                                double diffY = (ball1.getCenterY() - ball2.getCenterY());
                                
                                System.out.println("diffX " + diffX + " diffY " + diffY);
                                
                                if(abs(diffX) < .001) diffX = 0;
                                if(abs(diffY) < .001) diffY = 0;
                                
                                double nMag = sqrt(pow(diffX,2) + pow(diffY,2));
                                
                                System.out.println("diffX " + diffX + " diffY " + diffY);
                                
                                double nX = diffX/nMag;
                                double nY = diffY/nMag;
                                
                                if(abs(diffX) < .001) nX = 0;
                                if(abs(diffY) < .001) nY = 0;
                                
                                System.out.println("nX " + nX + " nY " + nY);
                                
                                double velNormDotProd1 = (ball1.getVX() * nX) + (ball1.getVY() * nY);
                                double velNormDotProd2 = (ball2.getVX() * -nX) + (ball2.getVY() * -nY);
                                
                                double velTanDotProd1 = (ball1.getVX() * nY) + (ball1.getVY() * -nX);
                                double velTanDotProd2 = (ball2.getVX() * -nY) + (ball2.getVY() * nX);
                                
                                double[] velNorm1 = {velNormDotProd1 * nX, velNormDotProd1 * nY};
                                double[] velNorm2 = {velNormDotProd2 * -nX, velNormDotProd2 * -nY};
                                
                                double[] velTan1 = {velTanDotProd1 * nY, velTanDotProd1 * -nX};
                                double[] velTan2 = {velTanDotProd2 * -nY, velTanDotProd2 * nX};
                                
                                
                                System.out.println("     NORMAL COMPONENTS    ");
                                System.out.println(ball1.name + " Vnx: " + velNorm1[0] + " Vny: " + velNorm1[1]);
                                System.out.println(ball2.name + " Vnx: " + velNorm2[0] + " Vny: " + velNorm2[1]);
                                
                                System.out.println("     Tangential COMPONENTS    ");
                                System.out.println(ball1.name + " Vtx: " + velTan1[0] + " Vty: " + velTan1[1]);
                                System.out.println(ball2.name + " Vtx: " + velTan2[0] + " Vty: " + velTan2[1]);
                                
                                ball1.setVX(velNorm2[0] + velTan1[0]);
                                ball1.setVY(velNorm2[1] + velTan1[1]);
                                
                                ball2.setVX(velNorm1[0] + velTan2[0]);
                                ball2.setVY(velNorm1[1] + velTan2[1]);
                                
                                System.out.println("    TOTAL VELOCITY   ");
                                System.out.println(ball1.name + " Vx " + ball1.velocity[0] + " Vy " + ball1.velocity[1]);
                                System.out.println(ball2.name + " Vx " + ball2.velocity[0] + " Vy " + ball2.velocity[1]);
                              
                                
                            }
                        } 
                    }
    }
    
}
