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
    private double myMagnitude = 9.8;
    private double myAngle = 90;
    

    /**
     * default constructor
     */
    public GravityForce () {
        super(GRAVITY_KEY_VAL);
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
    public void applyForce (List<Mass> masses) {
        if (!isOn()) {
            return;
        }
        for (Mass mass : masses) {
            mass.applyAccelerationVector(new Vector(myAngle, myMagnitude));
        }

    }

}
