/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicpool;

import static basicpool.Config.RADIUS;

/**
 *
 * @author Mike
 */
public class Pockets {
    
    
    static boolean checkPockets(Ball ball) {
        boolean topRight = (ball.getCenterX() > 969 && ball.getCenterY() < 247);
        boolean bottomRight = (ball.getCenterX() > 969 && ball.getCenterY() > 520);
        boolean bottomLeft = (ball.getCenterX() < 308 && ball.getCenterY() > 520);
        boolean topLeft = (ball.getCenterX() < 308 && ball.getCenterY() < 247);
        boolean bottom = (ball.getCenterX() < 660 - RADIUS && ball.getCenterX() > 620 + RADIUS);
        boolean top = (ball.getCenterX() < 660 - RADIUS && ball.getCenterX() > 620 + RADIUS);
        if (topRight || bottomRight || bottomLeft || topLeft || bottom || top) {
            return true;
        } else return false;
    }
    
    static boolean checkRemove(Ball ball) {
        if(ball.getCenterX() > 997 || ball.getCenterX() < 283 || ball.getCenterY() < 222 || ball.getCenterY() > 545)
            return true;
        else return false;
    }
    
}
