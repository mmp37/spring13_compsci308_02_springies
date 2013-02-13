package simulation;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

import util.Vector;
import view.Canvas;

public class WallRepulsionForce extends Force{
        private double myPower=2;
        private double myMagnitude = 1;
        private static final int MASS_KEY_VAL = 77;
        private Canvas myCanvas;
        private int myHeight;
        private int myWidth;
        
        /**
         * default constructor
         */
        public WallRepulsionForce(Canvas canv){
                super(MASS_KEY_VAL);
                myCanvas = canv;
                myHeight = myCanvas.getHeight();
                myWidth = myCanvas.getWidth();
        }
        
        /**
         * constructor
         * @param power
         */
        public WallRepulsionForce(double power, double mag, Canvas canv){
                this(canv);
                myPower = power;
                myMagnitude = mag;
        }
        
        
        /**
         * calculate the wall repulsion vector from an angle and distance.
         * @param angle
         * @param distance
         * @return Vector 
         */
        public Vector calculateWallRepulsionForce(double angle, double distance){
                return new Vector(angle, myMagnitude
                                /Math.pow(distance, myPower));
        }
        
        /**
         * calculate the total wall repulsion force on a location in the system.
         * @param vec
         * @param xCoord
         * @param yCoord
         * @return Vector vec
         */
        public Vector calculateTotalWallRepulsion(double xCoord, double yCoord){
                Vector vec = new Vector();
                double xDimension = myWidth;
                double yDimension = myHeight;
                vec.sum(calculateWallRepulsionForce(0, xDimension-xCoord));
                vec.sum(calculateWallRepulsionForce(90, yDimension - yCoord));
                vec.sum(calculateWallRepulsionForce(180, xCoord));
                vec.sum(calculateWallRepulsionForce(270, yCoord));
                return vec;
        }
        
        /**
         * this method is for resetting the size of the environment for calculation.
         * Used when canvas is resized
         * @param canv
         */
        public void setWidthAndHeight(Canvas canv){
            myHeight = canv.getHeight();
            myWidth = canv.getWidth();
        }
        
        
        /**
         * force calculation on input masses
         */
        @Override
        public void applyForce(List<Mass> masses){
                
                if ( !getState() )
                        return;
                
                for (Mass mass : masses){
                        
                        Vector force = calculateTotalWallRepulsion(
                                                      mass.getX(), mass.getY());
                        
                        mass.applyAccelerationVector(force);
                        
                }
                
        }

}
