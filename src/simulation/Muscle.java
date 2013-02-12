package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;
import java.math.*;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Muscle extends Spring {
    // reasonable default values
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("spring.gif");
    public static final int IMAGE_HEIGHT = 20;

    private Mass myStart;
    private Mass myEnd;
    private double myLength;
    private double myK;
    private double myAngle=0;
    

    /**
     * XXX.
     */
    public Muscle (Mass start, Mass end, double length, double kVal) {
        super(start, end, length, kVal);
    	myStart = start;
        myEnd = end;
        myLength = length;
        myK = kVal;
    }

    /**
     * XXX.
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(getColor(myStart.distance(myEnd) - myLength));
        pen.drawLine((int)myStart.getX(), (int)myStart.getY(), (int)myEnd.getX(), (int)myEnd.getY());
    }

    /**
     * XXX.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        double dx = myStart.getX() - myEnd.getX();
        double dy = myStart.getY() - myEnd.getY();
        // apply hooke's law to each attached mass
        myAngle += (elapsedTime)*6.24;
//        if (myAngle>6.28)
//        	myAngle=0;
       // myAngle = (myAngle/360) - Math.floor((myAngle/360));
        System.out.println(30*Math.sin(myAngle));

        Vector force = new Vector(Vector.angleBetween(dx, dy), 
                                 30*Math.cos(myAngle));
       // myStart.applyForce(force);
        
        myStart.setForce(force);
        force.negate();
        myEnd.setForce(force);
        // update sprite values based on attached masses
        setCenter(getCenter(myStart, myEnd));
        setSize(getSize(myStart, myEnd));
        setVelocity(Vector.angleBetween(dx, dy), 0);
    }

    /**
     * returns the color of this muscle
     */
    protected Color getColor (double diff) {
        if (Vector.fuzzyEquals(diff, 0)) return Color.BLACK;
        else if (diff < 0.0) return Color.BLUE;
        else return Color.RED;
    }

    // compute center of this spring
    private static Location getCenter (Mass start, Mass end) {
        return new Location((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    // compute size of this spring
    private static Dimension getSize (Mass start, Mass end) {
        return new Dimension((int)start.distance(end), IMAGE_HEIGHT);
    }
}
