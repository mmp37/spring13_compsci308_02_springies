package simulation;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class AllMasses {
	private ArrayList<Mass> myMasses;
	private double totalMass;
	private double centerX;
	private double centerY;
	
	
	
	public AllMasses(){
		myMasses = new ArrayList<Mass>();
	}
	
	public AllMasses(ArrayList<Mass> masses){
		myMasses = masses;
		//calculate total mass
	}
	
	protected void reCalculate(){
		double tempX = 0;
		double tempY = 0;
		Iterator<Mass> iter = myMasses.iterator();
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
	
	public List<Mass> getList(){
		return myMasses;
	}
	
	public void add(Mass newMass){
		myMasses.add(newMass);
		calculateTotalMass();
	}
	
	public void remove(int index){
		myMasses.remove(index);
	}
	
	public void remove(Mass mass){
		myMasses.remove(mass);
	}
	
	
	private void calculateTotalMass(){
		
	}
	
	
	
	

}
