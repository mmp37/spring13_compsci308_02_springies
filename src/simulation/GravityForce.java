package simulation;

import java.util.List;

import util.Vector;

public class GravityForce extends Force {
	private double myMagnitude = 9.8;
	private double myAngle = 90;
	
	

	public GravityForce() {
		super(71);
	}


	public GravityForce(double angle, double magnitude) {
		super(71);
		myAngle = angle;
		myMagnitude = magnitude;
	}

	@Override
	public void applyForce(List<Mass> masses) {
		if (!getState())
			return;
		for (Mass mass : masses) {
			mass.applyForce(new Vector(myAngle, myMagnitude));
		}

	}

}
