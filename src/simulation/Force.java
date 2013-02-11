package simulation;

import java.util.List;

public class Force {
	private boolean state=true;
	private int KEY_VAL;
	
	
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
	
	public Force(){
		
	}
	public Force(int in){
		KEY_VAL = in;
	}
	
	public void setVal(int in){
		KEY_VAL = in;
	}
	
	public void applyForce(List<Mass> masses){
		
	}

}