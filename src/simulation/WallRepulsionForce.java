package simulation;

import java.awt.event.KeyEvent;
import java.util.List; 
import util.Vector;
import view.Canvas;


/**
 * Force subclass for wall repulsion forces
 * @author Matthew Parides
 *
 */
public class WallRepulsionForce extends Force {
    private static final int TOP_WALL = 1;
    private static final int RIGHT_WALL = 2;
    private static final int BOTTOM_WALL = 3;
    private static final int LEFT_WALL = 4;
    private static final int RIGHT_ANGLE = 90;
    private double myPower = 2;
    private double myMagnitude = 1;
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
     * @param canv - the canvas that this force is applied in
     * @param side - the side of the canvas that this force corresponds to.
     */
    public WallRepulsionForce (double power, double mag, double width
                               , double height, int side) {
        this(width, height);
        myPower = power;
        myMagnitude = mag;
        mySide = side;
        
        myRepulsionAngle = side * RIGHT_ANGLE;
        
        
    }
    
    private void setKey(int side) {
        if (side == 1) {
            setKeyVal(KeyEvent.VK_1);
        }
        
        else if (side == 2) {
            setKeyVal(KeyEvent.VK_2);
        }
        
        else if (side == 3) {
            setKeyVal(KeyEvent.VK_3);
        }
        
        else if (side == 4) {
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
