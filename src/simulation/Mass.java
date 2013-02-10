package simulation;

import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;
import simulation.Environment;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Mass extends Sprite {    
    // reasonable default values
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");

    private double myMass;
    protected Vector myAcceleration;
    private Environment myEnvironment;


    /**
     * XXX.
     */
    public Mass (double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myMass = mass;
        myAcceleration = new Vector();
        myEnvironment = new Environment(90, 4, new Dimension(800,600), 0, 3,
                                        0, 4);
    }

    /**
     * XXX.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        applyForce(getBounce(bounds));
        // convert force back into Mover's velocity
        Vector enviroForces = myEnvironment.calculateNetForce(getX(),getY());
        myAcceleration.sum(enviroForces);
        getVelocity().sum(myAcceleration);
        getVelocity().sum(getBounce(myEnvironment.getDimensions()));
      //  getVelocity().sum(theEnviroment.calculateNetForce(getX(), getY()));
        getVelocity().scale(.99); //for viscoscity 
        myAcceleration.reset();
        // move mass by velocity
        super.update(elapsedTime, bounds);
    }
    
    public void setEnvironment(Environment enviro){
        myEnvironment = enviro;
    }

    /**
     * XXX.
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillOval((int)getLeft(), (int)getTop(), (int)getWidth(), (int)getHeight());
    }

    /**
     * Use the given force to change this mass's acceleration.
     */
    public void applyForce (Vector force) {
    	force.scale(1/myMass);
        myAcceleration.sum(force);
    }
    
    public void setForce (Vector force){
    	myAcceleration = new Vector(force); 
    	
    }

    /**
     * Convenience method.
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
    
    public double getMass(){
    	return myMass;
    }
}
