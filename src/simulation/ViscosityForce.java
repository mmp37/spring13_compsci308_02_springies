package simulation;

import java.util.List;

import util.Vector;

public class ViscosityForce extends Force{
	private double myFactor = 0.9;
	
	
	public ViscosityForce(){
		super(86);
	}
	
	public ViscosityForce(int scalingFactor){
		super(86);
		myFactor = scalingFactor;
	}
	
	public void setScalingFactor(int in){
		myFactor = in;
	}
	
	@Override
	public void applyForce(List<Mass> masses){
		
		if ( !getState() )
			return;
		
		for (Mass mass : masses){
			Vector velocity = mass.getVelocity();
			velocity.scale(myFactor);
		}
	}

}
