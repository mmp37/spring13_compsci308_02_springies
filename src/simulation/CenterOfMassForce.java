package simulation;


import java.awt.Point;
import java.util.List;

import util.Vector;

public class CenterOfMassForce extends Force{
	private double myPower=2;
	
	
	public CenterOfMassForce(){
		super(77);
	}
	
	public CenterOfMassForce(double power){
		super(77);
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
			Point massPt = mass.getPoint();
			double dist = massPt.distance(center);
			
			Vector force = new Vector(massPt, center);
			force.scale(1/Math.pow(dist, myPower));
			
			mass.applyForce(force);
			
		}
		
	}

}
