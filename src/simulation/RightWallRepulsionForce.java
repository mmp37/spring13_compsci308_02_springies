package simulation;

import java.awt.event.KeyEvent;
import java.util.List;
import util.Vector;
import view.Canvas;

/**
 * right wall force - constructors and methods for applying this force.
 * @author Matthew Parides
 *
 */
public class RightWallRepulsionForce extends Force {
    /**
     * this force's corresponding keyboard key.
     */
    public static final int TOGGLE_WALL_FORCE_RIGHT = KeyEvent.VK_1;
    private static final int WALL_FORCE_ANGLE = 0;
    private double myPower;
    private double myMagnitude;
    private Canvas myCanvas;
    /**
     * default constructor
     * @param canv - the canvas that these wall repulsion forces will be 
     * represented in.
     */
    public RightWallRepulsionForce (Canvas canv) {
        super(TOGGLE_WALL_FORCE_RIGHT);
        myCanvas = canv;
        myPower = 2;
        myMagnitude = 1;
    }

    /**
     * constructor
     * 
     * @param power - exponent of this force
     * @param mag - magnitude of this force
     * @param canv - the canvas that this force is applied in
     */
    public RightWallRepulsionForce (double power, double mag, Canvas canv) {
        super(TOGGLE_WALL_FORCE_RIGHT);
        myCanvas = canv;
        myPower = power;
        myMagnitude = mag;
    }

    /**
     * calculate the wall repulsion vector from an angle and distance.
     * @param xCoord - xCoordinate of referenced mass
     * @return Vector
     */
    public Vector calculateWallRepulsionForce (double xCoord) {
        return new Vector(WALL_FORCE_ANGLE, myMagnitude
                                 / Math.pow(myCanvas.getWidth() - xCoord, myPower));
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