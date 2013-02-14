package simulation;

import java.awt.event.KeyEvent;
import java.util.List;
import util.Vector;

/**
 * top wall force - constructors and methods for applying this force.
 * @author Matthew Parides
 *
 */
public class TopWallRepulsionForce extends Force {
    /**
     * this force's corresponding key event
     */
    public static final int TOGGLE_WALL_FORCE_UP = KeyEvent.VK_4;
    private static final int WALL_FORCE_ANGLE = 270;
    private double myPower = 2;
    private double myMagnitude = 1;
    /**
     * default constructor
     * represented in.
     */
    public TopWallRepulsionForce () {
        super(TOGGLE_WALL_FORCE_UP);
    }

    /**
     * constructor
     * 
     * @param power - the exponent of the wall repulsion force
     * @param mag - magnitude of the wall repulsion force
     */
    public TopWallRepulsionForce (double power, double mag) {
        super(TOGGLE_WALL_FORCE_UP);
        myPower = power;
        myMagnitude = mag;
    }

    /**
     * calculate the wall repulsion vector from an angle and distance.
     * @param yCoord - distance between the wall and the object
     * @return Vector
     */
    public Vector calculateWallRepulsionForce (double yCoord) {
        return new Vector(WALL_FORCE_ANGLE, myMagnitude
                                 / Math.pow(yCoord, myPower));
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