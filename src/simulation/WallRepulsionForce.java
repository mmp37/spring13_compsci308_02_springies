package simulation;

import java.awt.event.KeyEvent;
import java.util.List; 
import util.Vector;


/**
 * Force subclass for wall repulsion forces
 * @author Matthew Parides
 *
 */
public class WallRepulsionForce extends Force {
    /**
     * top wall value
     */
    public static final int TOP_WALL = 1;
    /**
     * right wall value
     */
    public static final int RIGHT_WALL = 2;
    /**
     * bottom wall value
     */
    public static final int BOTTOM_WALL = 3;
    /**
     * left wall value
     */
    public static final int LEFT_WALL = 4;
    private static final int DEFAULT_MAGNITUDE = 5;
    private static final int DEFAULT_POWER = 0;
    private static final int RIGHT_ANGLE = 90;
    private double myPower = DEFAULT_POWER;
    private double myMagnitude = DEFAULT_MAGNITUDE;
    private double myWidth;
    private double myHeight;
    private int myRepulsionAngle;
    private int mySide;
    

    /**
     * default constructor
     * @param height - the height of the environment that this force is applied
     * in
     * @param width - the width of the environment that this force is applied in
     */
    public WallRepulsionForce (double width, double height) {
        super();
        myWidth = width;
        myHeight = height;
        
    }

    /**
     * constructor
     * 
     * @param power - exponent of the force
     * @param mag - magnitude of the force
     * @param side - the side of the canvas that this force corresponds to.
     * @param width - the width of the environment this force is applied in
     * @param height - the height of the environment this force is applied in
     */
    public WallRepulsionForce (double power, double mag, double width
                               , double height, int side) {
        this(width, height);
        myPower = power;
        myMagnitude = mag;
        mySide = side;
        setKey(mySide);
        myRepulsionAngle = side * RIGHT_ANGLE;
        
        
    }
    
    /**
     * convenience method for setting they key listener key for instance
     * of wall repulsion force
     * @param side - the number of the side this instance corresponds to
     */
    private void setKey(int side) {
        if (side == TOP_WALL) {
            setKeyVal(KeyEvent.VK_1);
        }
        
        else if (side == RIGHT_WALL) {
            setKeyVal(KeyEvent.VK_2);
        }
        
        else if (side == BOTTOM_WALL) {
            setKeyVal(KeyEvent.VK_3);
        }
        
        else if (side == LEFT_WALL) {
            setKeyVal(KeyEvent.VK_4);
        }
    }
    
    
    /**
     * gets the distance between this repulsion force's wall and the input mass
     * @param mass - mass to find the distance to
     * @return dist - distance to mass
     */
    public double getDistance(Mass mass) {
        double dist = 0;
        if (mySide == TOP_WALL) {
            dist = mass.getY();
        }
        
        else if (mySide == RIGHT_WALL) {
            dist = mass.getX();
        }
        
        else if (mySide == BOTTOM_WALL) {
            dist = mass.getY() - myHeight;
        }
        
        else if (mySide == LEFT_WALL) {
            dist = mass.getX() - myWidth;
        }
        return dist;
    }
    
    

    /**
     * calculate the wall repulsion vector from an angle and distance.
     * 
     * @param distance - distance between the wall and the object
     * @return Vector
     */
    public Vector calculateWallRepulsionForce (double distance) {
        return new Vector(myRepulsionAngle, myMagnitude
                                 / Math.pow(distance, myPower));
    }

    /**
     * this method is for resetting the size of the environment for calculation.
     * Used when canvas is resized
     * 
     * @param width - the new width of the environment
     * @param height - the new height of the environment
     */
    public void setWidthAndHeight (double width, double height) {
        myHeight = height;
        myWidth = width;
    }

    /**
     * force calculation on input masses
     * @param masses - input masses to apply force to
     */
    @Override
    protected void envoke (List<Mass> masses) {
        for (Mass mass : masses) {
            double distance = getDistance(mass);
            Vector force = calculateWallRepulsionForce(distance);

            mass.applyAccelerationVector(force);
        }

    }

}
