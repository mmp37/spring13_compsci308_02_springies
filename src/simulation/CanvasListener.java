package simulation;

import java.awt.event.KeyEvent;
import java.util.Collection;
import view.Canvas;

/**
 * Listener for canvas events
 * @author Matthew Parides
 *
 */
public class CanvasListener extends Listener {
    /**
     * how much each button press will change the canvas size
     */
    private static final int CANVAS_SIZE_INCREMENT = 10;
    private Canvas myView;
    private int myKey;
    
    /**
     * constructor
     * @param view - canvas
     * @param key - keyevent
     */
    public CanvasListener(Canvas view, int key) {
        myView = view;
        myKey = key;
    }

    /**
     * default constructor
     */
    public CanvasListener () {
    }
    
    /**
     * updates the canvas if there was a correct key press from the view.
     */
    public void update() {
        if (!myView.getLastKeyHeuristic()) {
            return;
        }
        myView.setLastKeyHeuristic(false);

        Collection<Integer> keysPressed = myView.getKeysPressed();

        if (!keysPressed.contains(myKey)) {
            return;
        }

        if (myKey == KeyEvent.VK_UP) {
            myView.setBounds(0, 0, myView.getWidth() + CANVAS_SIZE_INCREMENT, 
                             myView.getHeight() + CANVAS_SIZE_INCREMENT);
        }
        
        else if (myKey == KeyEvent.VK_DOWN) {
            myView.setBounds(0, 0, myView.getWidth() - CANVAS_SIZE_INCREMENT, 
                             myView.getHeight() - CANVAS_SIZE_INCREMENT);
        }

    }
}
