package simulation;

import java.util.List;

public class Force {
	private boolean state=true;
	private int KEY_VAL;
	
	
	public Force(){
		
	}
	public Force(int in){
		KEY_VAL = in;
	}
	
	public int getKeyVal(){
		return KEY_VAL;
	}
	
	public void setState(boolean in){
		state = in;
	}
	
	public void toggleState(){
		state = !state;
	}
	
	protected boolean getState(){
		return state;
	}
	
	public void setVal(int in){
		KEY_VAL = in;
	}
	
	/**
	 * applies the implemented force class's force on input masses.
	 * @param masses
	 */
	public void applyForce(List<Mass> masses){
		
	}

}