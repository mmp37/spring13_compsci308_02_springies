package simulation;

import util.Vector;  
import java.awt.geom.Point2D;
import java.math.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.Dimension;

public class Environment {
	private double WALL_REPULSION_EXPONENT;
	private double WALL_REPULSION_MAGNITUDE;
	private double CENTER_OF_MASS_EXPONENT;
	private double CENTER_OF_MASS_MAGNITUDE;
	private static double centerX;
	private static double centerY;
	private double totalMass;
	private Vector myGravity;
	private Dimension myDimensions;
	
	
	/**
	 * constructor sets all constants
	 * @param gravityAngle
	 * @param gravityMagnitude
	 * @param dim
	 * @param wRE
	 * @param wRM
	 * @param cOME
	 * @param cOMM
	 */
	public Environment(double gravityAngle, double gravityMagnitude, 
			Dimension dim, double wRE, double wRM, double cOME, double cOMM){
		myGravity = new Vector(gravityAngle, gravityMagnitude);
		myDimensions = dim;
		WALL_REPULSION_EXPONENT = wRE;
		WALL_REPULSION_MAGNITUDE = wRM;
		CENTER_OF_MASS_EXPONENT = cOME;
		CENTER_OF_MASS_MAGNITUDE = cOMM;
	}
	
	/**
	 * sets total mass in the system.
	 * @param totMass
	 */
	protected void setTotalMass(double totMass){
		totalMass = totMass;
	}
	
	/**
	 * sets the location of the center of mass.
	 * @param x
	 * @param y
	 */
	protected void setCenterMass(double x, double y){
		centerX = x;
		centerY = y;
	}
	
	/**
	 * calculate the wall repulsion vector from an angle and distance.
	 * @param angle
	 * @param distance
	 * @return Vector 
	 */
	public Vector calculateWallRepulsionForce(double angle, double distance){
		return new Vector(angle, WALL_REPULSION_MAGNITUDE
				/Math.pow(distance, WALL_REPULSION_EXPONENT));
	}
	
	/**
	 * calculate the center of mass vector from an angle and distance.
	 * @param angle
	 * @param distance
	 * @return Vector
	 */
	public Vector calculateCenterOfMassForce(double angle, double distance){
		return new Vector(angle, CENTER_OF_MASS_MAGNITUDE
				/Math.pow(distance, CENTER_OF_MASS_EXPONENT));
	}
	
	/**
	 * calculate the total wall repulsion force on a location in the system.
	 * @param vec
	 * @param xCoord
	 * @param yCoord
	 * @return Vector vec
	 */
	public Vector calculateTotalWallRepulsion(Vector vec, double xCoord, double yCoord){
		double xDimension = myDimensions.getWidth();
		double yDimension = myDimensions.getHeight();
		vec.sum(calculateWallRepulsionForce(0, xDimension-xCoord));
		vec.sum(calculateWallRepulsionForce(90, yDimension - yCoord));
		vec.sum(calculateWallRepulsionForce(180, xCoord));
		vec.sum(calculateWallRepulsionForce(270, yCoord));
		return vec;
	}
	
	/**
	 * Use the masses in this system to calculate the center of mass.
	 * @param masses
	 */
	protected void reCalculateCenterOfMass(ArrayList<Mass> masses){
		double tempX = 0;
		double tempY = 0;
		Iterator<Mass> iter = masses.iterator();
		while (iter.hasNext()) 
		{
			Mass massTemp = iter.next();
			totalMass += massTemp.getMass();
			tempX += massTemp.getMass()*massTemp.getX();
			tempY += massTemp.getMass()*massTemp.getY();
		}
		centerX = tempX/totalMass;
		centerY = tempY/totalMass;
	}

	
	/**
	 * calculates the net force vector of all forces in the system. Coordinates
	 * passed in should be the coordinates of an object in the system.
	 * @param xCoord
	 * @param yCoord
	 * @return totalVector
	 */
	public Vector calculateNetForce(double xCoord, double yCoord){
		Vector totalVector = new Vector();
		Point2D.Double point1 = new Point2D.Double(xCoord, yCoord);
		Point2D.Double point2 = new Point2D.Double(centerX, centerY);
		
		//center of mass
		double centerOfMassAngle = Vector.angleBetween(point1, point2);
		double centerOfMassDistance = Vector.distanceBetween(point1, point2);
		totalVector.sum(calculateCenterOfMassForce(centerOfMassAngle
				,centerOfMassDistance));
		totalVector = calculateTotalWallRepulsion(totalVector, xCoord, yCoord);
		totalVector.sum(myGravity);
		
		return totalVector;
		}
	
	
	
}
