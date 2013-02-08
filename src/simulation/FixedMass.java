package simulation;

import java.awt.Dimension;

import util.Location;
import util.Vector;

public class FixedMass extends Mass {
	
	 public FixedMass (double x, double y, double mass) {
		 super(x,y,100000);
	      
	 }
	 
	  @Override
	    public void update (double elapsedTime, Dimension bounds) {
	        applyForce(getBounce(bounds));
	        // convert force back into Mover's velocity
	        getVelocity().sum(myAcceleration);
	        myAcceleration.reset();
	        
	    }


}