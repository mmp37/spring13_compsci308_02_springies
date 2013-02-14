package simulation;

import java.awt.Dimension;

/**
 * Fixed mass class that does not move
 * @author Aaron Krolik
 *
 */
public class FixedMass extends Mass {

    /**
     * constructor
     * @param x - x coord
     * @param y - y coord
     * @param mass - mass magnitude
     */
    public FixedMass (double x, double y, double mass) {
        super(x, y, 100000);

    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        applyAccelerationVector(getBounce(bounds));
        // convert force back into Mover's velocity
        getVelocity().sum(myVelocity);
        myVelocity.reset();

    }

}
