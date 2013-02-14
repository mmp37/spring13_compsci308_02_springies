package simulation;

import java.awt.event.KeyEvent;
import java.util.List;
import util.Vector;
import view.Canvas;

/**
 * left wall force - constructors and methods for applying this force.
 * @author Matthew Parides
 *
 */
public class LeftWallRepulsionForce extends Force {
    /**
     * this force's corresponding key event
     */
    public static final int TOGGLE_WALL_FORCE_LEFT = KeyEvent.VK_3;
    private static final int WALL_FORCE_ANGLE = 180;
    private double myPower = 2;
    private double myMagnitude = 1;
    /**
     * default constructor
     */
    public LeftWallRepulsionForce () {
        super(TOGGLE_WALL_FORCE_LEFT);
    }

    /**
     * constructor
     * 
     * @param power - the exponent of the wall repulsion force
     * @param mag - magnitude of the wall repulsion force
     */
    public LeftWallRepulsionForce (double power, double mag) {
        super(TOGGLE_WALL_FORCE_LEFT);
        myPower = power;
        myMagnitude = mag;
    }

    /**
     * calculate the wall repulsion vector from an angle and distance.
     * @param xCoord - distance between the wall and the object
     * @return Vector
     */
    public Vector calculateWallRepulsionForce (double xCoord) {
        return new Vector(WALL_FORCE_ANGLE, myMagnitude
                                 / Math.pow(xCoord, myPower));
    }
    
    /**
     * force calculation on input masses
     * @param masses - input masses to apply force to
     */
    @Override
    public void applyForce (List<Mass> masses) {

        if (!isOn()) {
            return;
        }
        for (Mass mass : masses) {

            Vector force = calculateWallRepulsionForce(mass.getX());

            mass.applyAccelerationVector(force);

        }

    }

}
