package simulation;

import util.Vector; 
import java.awt.geom.Point2D;
import java.math.*;

public class theEnvironment {
	private static double WALL_REPULSION_EXPONENT;
	private static double WALL_REPULSION_MAGNITUDE;
	private static double centerX;
	private static double centerY;
	private static double totalMass;
	private static Vector myGravity;
	
	public theEnvironment(double gravityAngle, double gravityMagnitude){
		myGravity = new Vector(gravityAngle, gravityMagnitude);
	}
	public theEnvironment(){
	}
	
	protected void setTotalMass(double totMass){
		totalMass = totMass;
	}
	
	protected void setCenterMass(double x, double y){
		centerX = x;
		centerY = y;
	}
	
	protected double getVectorAngleFromRawCoords(double xCoord1, double yCoord1, double xCoord2, double yCoord2){
		Point2D.Double point1 = new Point2D.Double(xCoord1, yCoord1);
		Point2D.Double point2 = new Point2D.Double(xCoord2, yCoord2);
		Vector vec = new Vector(point1, point2);
		return vec.getDirection();
	}
	
	public static Vector calculateWallRepulsionForce(double angle, double magnitude, double distance, double exponent){
		return new Vector(angle, (magnitude)/Math.pow(distance, exponent));
	}
	
	public static Vector calculateNetForce(double xCoord, double yCoord){
		Point2D.Double point1 = new Point2D.Double(xCoord, yCoord);
		Point2D.Double point2 = new Point2D.Double(centerX, centerY);
		Vector gravity = new Vector(90, 9.8);
		double centerOfMassAngle = Vector.angleBetween(point1, point2);
		Vector centerOfMassForce = new Vector();
	//	wall vector
		//ex gravity.sum(wall vector);
		}
	
	
}
