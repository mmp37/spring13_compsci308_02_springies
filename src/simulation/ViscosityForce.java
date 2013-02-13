package simulation;

import java.util.List;
import util.Vector;


public class ViscosityForce extends Force {
    private double myFactor = 0.9;
    private static final int VISCOSITY_KEY_VAL = 86;

    public ViscosityForce () {
        super(VISCOSITY_KEY_VAL);
    }

    public ViscosityForce (int scalingFactor) {
        super(VISCOSITY_KEY_VAL);
        myFactor = scalingFactor;
    }

    public void setScalingFactor (int in) {
        myFactor = in;
    }

    @Override
    public void applyForce (List<Mass> masses) {

        if (!isOn())
            return;

        for (Mass mass : masses) {
            Vector velocity = mass.getVelocity();
            velocity.scale(myFactor);
        }
    }

}
