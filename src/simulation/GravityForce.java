package simulation;

import java.util.List;

import util.Vector;

public class GravityForce extends Force {
	private double myMagnitude = 9.8;
	private double myAngle = 90;
	private static final int GRAVITY_KEY_VAL  = 71;
	
	

	public GravityForce() {
		super(GRAVITY_KEY_VAL);
	}


	public GravityForce(double angle, double magnitude) {
		super(GRAVITY_KEY_VAL);
		myAngle = angle;
		myMagnitude = magnitude;
	}

	@Override
	public void applyForce(List<Mass> masses) {
		if (!getState())
			return;
		for (Mass mass : masses) {
			mass.applyAccelerationVector(new Vector(myAngle, myMagnitude));
		}

	}

}
