package simulation;

import java.awt.event.KeyEvent;
import java.util.List;
import util.Vector;
import view.Canvas;

/**
 * bottom wall force - constructors and methods for applying this force.
 * @author Matthew Parides
 *
 */
public class BottomWallRepulsionForce extends Force{
    /**
     * this wall's corresponding key event
     */
    public static final int TOGGLE_WALL_FORCE_DOWN = KeyEvent.VK_2;
    private static final int WALL_FORCE_ANGLE = 90;
    private double myPower;
    private double myMagnitude;
    private Canvas myCanvas;
    /**
     * default constructor
     * @param canv - the canvas that these wall repulsion forces will be 
     * represented in.
     */
    public BottomWallRepulsionForce (Canvas canv) {
        super(TOGGLE_WALL_FORCE_DOWN);
        myCanvas = canv;
        myPower = 2;
        myMagnitude = 1;
    }

    /**
     * constructor
     * 
     * @param power - the exponent of the wall repulsion force
     * @param mag - magnitude of the wall repulsion force
     * @param canv - the canvas that this force is applied in
     */
    public BottomWallRepulsionForce (double power, double mag, Canvas canv) {
        super(TOGGLE_WALL_FORCE_DOWN);
        myCanvas = canv;
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
                                 / Math.pow(myCanvas.getHeight() - yCoord, myPower));
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