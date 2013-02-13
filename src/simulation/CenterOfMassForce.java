package simulation;


import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

import util.Vector;

public class CenterOfMassForce extends Force{
	private double myPower=2;
	private static final int MASS_KEY_VAL = 77;
	
	/**
	 * constructor
	 */
	public CenterOfMassForce(){
		super(MASS_KEY_VAL);
	}
	
	/**
	 * constructor
	 * @param power
	 */
	public CenterOfMassForce(double power){
		super(MASS_KEY_VAL);
		myPower = power;
	}
	
	@Override
	public void applyForce(List<Mass> masses){
		
		if ( !getState() )
			return;
		
		double centerX=0;
		double centerY=0;
		double allMass = 0;
		
		
		for (Mass mass : masses){
			double myMass = mass.getMass();
			allMass+=myMass;
			centerX += mass.getX()*myMass;
			centerY += mass.getY()*myMass;
		}
		
		centerX=centerX/allMass;
		centerY=centerY/allMass;
		
		Point center = new Point();
		center.setLocation(centerX, centerY);
		
		for (Mass mass : masses){
			Point2D massPt = mass.getPoint();
			double dist = massPt.distance(center);
			
			Vector force = new Vector(massPt,center);
			force.scale(1/Math.pow(dist, myPower));
			
			mass.applyAccelerationVector(force);
			
		}
		
	}

}