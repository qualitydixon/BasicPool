/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicpool;

import javafx.util.Duration;

/**
 *
 * @author Mike
 */
public class Config {
    public static final int UNITY = 1;
    public static final int WINDOWWIDTH = 1280;
    public static final int WINDOWHEIGHT = 768;
    public static final int TABLEWIDTH = 800;
    public static final int TABLEHEIGHT = 400;
    public static final double SPOTX = 580;
    public static final double SPOTY = 200;
    public static final int RADIUS = 10;
    public double cueTheta = 0;
    
    // Add floor
    public static final double FLOOR = TABLEHEIGHT - 50;
    public static final double OFFSET = FLOOR - SPOTY;
    
    // Walls
    public static final double RIGHTWALL = 755;
    public static final double LEFTWALL = 45;
    public static final double TOPWALL = 38;
    public static final double BOTTOMWALL = 360;
    
    // Init Positions
    public static final double CUEPOS[]   = {100, SPOTY - 5};
    public static final double ONEPOS[]   = {SPOTX, SPOTY};
    public static final double TWOPOS[]   = {SPOTX + 20.01, SPOTY - 10.01};
    public static final double THREEPOS[] = {SPOTX + 20.01, SPOTY + 10.01};
    public static final double FOURPOS[]  = {SPOTX + 40.02, SPOTY - 20.02};
    public static final double FIVEPOS[]  = {SPOTX + 60.03, SPOTY + 10.01};
    public static final double SIXPOS[]   = {SPOTX + 40.02, SPOTY + 20.02};
    public static final double SEVENPOS[] = {SPOTX + 60.03, SPOTY - 10.01};
    public static final double EIGHTPOS[] = {SPOTX + 40.02, SPOTY};
    
    
    
    // Set Duration
    public static final Duration DURATION = Duration.millis(850);
    public static final double deltaT = .01;
    
    // Ball Names
    public static final String CUEBALL   = "Cue Ball";
    public static final String ONEBALL   = "One Ball";
    public static final String TWOBALL   = "Two Ball";
    public static final String THREEBALL = "Three Ball";
    public static final String FOURBALL  = "Four Ball";
    public static final String FIVEBALL  = "Five Ball";
    public static final String SIXBALL   = "Six Ball";
    public static final String SEVENBALL = "Seven Ball";
    public static final String EIGHTBALL = "Eight Ball";
    

    
    
    
}
