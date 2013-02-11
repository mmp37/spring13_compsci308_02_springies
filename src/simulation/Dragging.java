package simulation;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import view.Canvas;

public class Dragging  {

	
	
	public void update(List<Mass> masses, List<Spring> springs, Canvas view) {
		
		if (view.getLastMousePosition()==view.NO_MOUSE_PRESSED){
			
			
			Iterator<Spring> springIter = springs.iterator();
			while (springIter.hasNext()){
				Spring s = springIter.next();
				if (s instanceof DragSpring){
					springIter.remove();
					s=null;
				}
			}
			
			Iterator<Mass> massIter = masses.iterator();
			while (massIter.hasNext()){
				Mass m = massIter.next();
				if (m instanceof DragMass){
					massIter.remove();
					m=null;
				}
			}
				
			return;
			
		}
		
		
		if (!view.getLastClickHeuristic()){
			return;
		}
		view.setLastClickHeuristic(false);
		
		Point myLocation = view.getLastMousePosition();
		Mass closest = null;
		double minDistance = 100000;
		
		for (Mass m : masses){
			
			double distance = myLocation.distance(m.getPoint());
			
			if ( distance <= minDistance ){
				closest = m;
				minDistance = distance;
			}
			
		}
		
		Mass dMass = new DragMass(myLocation.getX(), myLocation.getY(), 100, view);
		
		Spring dSpring = new DragSpring(closest, dMass, minDistance, 100);
		
		masses.add(dMass);
		springs.add(dSpring);
		 
	}

}
