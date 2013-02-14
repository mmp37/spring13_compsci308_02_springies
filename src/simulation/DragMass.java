package simulation;

import java.awt.Dimension;
import java.awt.Point;
import view.Canvas;

/**
 * The created mass from the mouse click and drag. constructor and update methods.
 * @author Aaron Krolik
 *
 */
public class DragMass extends Mass {
    private Canvas myView;
    
    /**
     * Creates a "dragmass" this is a temporary mass controlled by the mouse
     * click and drag.
     * @param x - starting x coordinate
     * @param y - starting y coordinate
     * @param mass - the weight/magnitude of the mass
     * @param view - the canvas that will contain this mass
     */
    public DragMass (double x, double y, double mass, Canvas view) {
        super(x, y, mass);
        myView = view;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        if (myView.getLastMousePosition() != Canvas.NO_MOUSE_PRESSED) {
            Point point = myView.getLastMousePosition();
            setCenter(point.getX(), point.getY());
            super.update(elapsedTime, bounds);
        }
    }

}
