package simulation;

import java.awt.Dimension;
import java.awt.Point;
import view.Canvas;


public class DragMass extends Mass {
    Canvas myView;

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
