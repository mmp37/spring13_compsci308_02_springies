package simulation;

import java.awt.event.KeyEvent;
import java.util.List;
import util.Vector;

/**
 * class to handle viscosity force
 * @author Aaron Krolik & Matthew Parides
 *
 */
public class ViscosityForce extends Force {
    private static final int VISCOSITY_KEY_VAL = KeyEvent.VK_V;
    private static final double DEFAULT_FACTOR = .9;
    private double myFactor;
    
    /**
     * default constructor
     */
    public ViscosityForce () {
        super(VISCOSITY_KEY_VAL);
        myFactor = DEFAULT_FACTOR;
    }
    
    /**
     * constructor
     * @param scalingFactor - the size of the scaling factor of viscosity
     */
    public ViscosityForce (double scalingFactor) {
        super(VISCOSITY_KEY_VAL);
        myFactor = scalingFactor;
    }

    /**
     * sets the scaling factor of this viscous force
     * @param in - new scaling factor
     */
    public void setScalingFactor (int in) {
        myFactor = in;
    }

    
    @Override
    protected void envoke(List<Mass> masses) {
        for (Mass mass : masses) {
            Vector velocity = mass.getVelocity();
            velocity.scale(myFactor);
        }
    }

}
