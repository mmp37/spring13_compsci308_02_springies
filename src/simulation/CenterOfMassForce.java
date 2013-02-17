package simulation;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.List;
import util.Vector;

/**
 * CenterOfMassForce class contains methods for construction and calculating
 *  and applying a center of mass force to masses.
 * @author Matthew Parides & Aaron Krolik
 *
 */
public class CenterOfMassForce extends Force {
    
    private static final int MASS_KEY_VAL = KeyEvent.VK_M;
    
    private double myPower = 2;
    private double myMagnitude = 2;
    

    /**
     * constructor
     */
    public CenterOfMassForce () {
        super(MASS_KEY_VAL);
    }

    /**
     * constructor
     * 
     * @param mag - magnitude of this force
     * @param power - exponent value of center of mass force
     */
    public CenterOfMassForce (double mag, double power) {
        super(MASS_KEY_VAL);
        myPower = power;
        myMagnitude = mag;
    }

    @Override
    protected void envoke (List<Mass> masses) {
    
        double centerX = 0;
        double centerY = 0;
        double allMass = 0;
        
        for (Mass mass : masses) {
            double myMass = mass.getMass();
            allMass += myMass;
            centerX += mass.getX() * myMass;
            centerY += mass.getY() * myMass;
        }

        centerX = centerX / allMass;
        centerY = centerY / allMass;

        Point center = new Point();
        center.setLocation(centerX, centerY);

        for (Mass mass : masses) {
            Point2D massPt = mass.getPoint();
            double dist = massPt.distance(center);

            Vector force = new Vector(massPt, center);
            force.scale(myMagnitude / Math.pow(dist, myPower));

            mass.applyAccelerationVector(force);

        }

    }

}
