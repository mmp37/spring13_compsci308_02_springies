package simulation;

import java.awt.event.KeyEvent;
import java.util.List;
import util.Vector;

/**
 * gravity implementation of the force class
 * @author Matthew Parides & Aaron Krolik
 *
 */
public class GravityForce extends Force {
    private static final int GRAVITY_KEY_VAL = KeyEvent.VK_G;
    private static final int DEFAULT_ANGLE = 90;
    private static final double DEFAULT_MAGNITUDE = 9.8;
    private double myMagnitude;
    private double myAngle;
    

    /**
     * default constructor
     */
    public GravityForce () {
        super(GRAVITY_KEY_VAL);
        myAngle = DEFAULT_ANGLE;
        myMagnitude = DEFAULT_MAGNITUDE;
    }
    
    /**
     * Constructor with non-default values for magnitude and angle
     * @param angle - angle of gravity force
     * @param magnitude - magnitude of gravity force
     */
    public GravityForce (double angle, double magnitude) {
        super(GRAVITY_KEY_VAL);
        myAngle = angle;
        myMagnitude = magnitude;
    }

    @Override
    protected void envoke (List<Mass> masses) {
        for (Mass mass : masses) {
            mass.applyAccelerationVector(new Vector(myAngle, myMagnitude));
        }

    }

}
