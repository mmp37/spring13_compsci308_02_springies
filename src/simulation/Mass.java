package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Mass extends Sprite {
    
    /**
     * default sprite size
     */
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    /**
     * default mass image
     */
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");
    
    protected Vector myVelocity;
    private double myMass;
    

    /**
     * Constructor with coordinates and mass magnitude inputs
     * @param x - x coordinate
     * @param y - y coordinate
     * @param mass - mass magnitude
     */
    public Mass (double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myMass = mass;
        myVelocity = new Vector();
    }

    /**
     * @param elapsedTime - time since last update
     * @param bounds - Dimensions of this simulation's environment.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        applyAccelerationVector(getBounce(bounds));
        // convert force back into Mover's velocity
        getVelocity().sum(myVelocity);
        myVelocity.reset();
        // move mass by velocity
        super.update(elapsedTime, bounds);
    }
    
    /**
     * returns this mass's mass
     * @return myMass
     */
    public double getMass () {
        return myMass;
    }
    
    /**
     * returns this mass's position as a Point2D.Double point
     * @return Point2D.Double(getX(), getY()) - getX and getY return this mass's
     * x and y coords.
     */
    public Point2D getPoint () {
        return new Point2D.Double(getX(), getY());
    }

    /**
     * paints this mass
     * @param pen - Graphics2D pen used to paint.
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Use the given force to change this mass's acceleration.
     * @param force - vector force to be applied to this mass.
     */
    public void applyAccelerationVector (Vector force) {
        force.scale(1 / myMass);
        myVelocity.sum(force);
    }
    
    /**
     * Sets the velocity of this mass
     * @param force force to set velocity to
     */
    public void setForce (Vector force) {
        myVelocity = new Vector(force);

    }

    /**
     * Convenience method.
     * @param other - mass to calculate the distance to
     */
    public double distance (Mass other) {
        // this is a little awkward, so hide it
        return new Location(getX(), getY()).distance(new Location(other.getX(), other.getY()));
    }

    // check for move out of bounds
    protected Vector getBounce (Dimension bounds) {
        final double IMPULSE_MAGNITUDE = 2;
        Vector impulse = new Vector();
        if (getLeft() < 0) {
            impulse = new Vector(RIGHT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getRight() > bounds.width) {
            impulse = new Vector(LEFT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        if (getTop() < 0) {
            impulse = new Vector(DOWN_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getBottom() > bounds.height) {
            impulse = new Vector(UP_DIRECTION, IMPULSE_MAGNITUDE);
        }
        impulse.scale(getVelocity().getRelativeMagnitude(impulse));
        return impulse;
    }
}
