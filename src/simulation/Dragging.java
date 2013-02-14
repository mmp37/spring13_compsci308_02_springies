package simulation;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import view.Canvas;

/**
 * Class to handle the "drag" function. controls the mouse click and drag that
 * creates a mass and spring, and drags the assembly.
 * @author Aaron Krolik
 *
 */
public class Dragging {

    
    /**
     * updates the current state of the click and drag spring and mass.
     * @param masses - connected masses in assembly
     * @param springs - connected springs in assembly
     * @param view - the canvas that contains this assembly.
     */
    public void update (List<Mass> masses, List<Spring> springs, Canvas view) {

        if (view.getLastMousePosition() == Canvas.NO_MOUSE_PRESSED) {

            Iterator<Spring> springIter = springs.iterator();
            while (springIter.hasNext()) {
                Spring s = springIter.next();
                if (s instanceof DragSpring) {
                    springIter.remove();
                    s = null;
                }
            }

            Iterator<Mass> massIter = masses.iterator();
            while (massIter.hasNext()) {
                Mass m = massIter.next();
                if (m instanceof DragMass) {
                    massIter.remove();
                    m = null;
                }
            }

            return;

        }

        if (!view.getLastClickHeuristic()) {
            return;
        }
        view.setLastClickHeuristic(false);

        Point thisLoc = view.getLastMousePosition();
        Mass closest = null;
        double minDistance = 100000;

        for (Mass m : masses) {

            double distance = thisLoc.distance(m.getPoint());

            if (distance <= minDistance) {
                closest = m;
                minDistance = distance;
            }

        }

        Mass dMass = new DragMass(thisLoc.getX(), thisLoc.getY(), 100, view);

        Spring dSpring = new DragSpring(closest, dMass, minDistance, 100);

        masses.add(dMass);
        springs.add(dSpring);

    }

}
