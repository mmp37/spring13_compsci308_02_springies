package simulation;

import java.util.List;
import util.Vector;

/**
 * class to handle viscosity force
 * @author Aaron Krolik & Matthew Parides
 *
 */
public class ViscosityForce extends Force {
    private static final int VISCOSITY_KEY_VAL = 86;
    private double myFactor = 0.9;
    
    /**
     * default constructor
     */
    public ViscosityForce () {
        super(VISCOSITY_KEY_VAL);
    }
    
    /**
     * constructor
     * @param scalingFactor - the size of the scaling factor of viscosity
     */
    public ViscosityForce (int scalingFactor) {
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
    public void applyForce (List<Mass> masses) {

        if (!isOn()) {
            return;
        }

        for (Mass mass : masses) {
            Vector velocity = mass.getVelocity();
            velocity.scale(myFactor);
        }
    }

}
