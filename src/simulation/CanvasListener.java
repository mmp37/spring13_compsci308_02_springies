package simulation;

import java.awt.event.KeyEvent;
import java.util.Collection;
import view.Canvas;

/**
 * Listener for canvas events
 * @author Matthew Parides & AK
 *
 */
public class CanvasListener extends Listener {
    /**
     * how much each button press will change the canvas size
     */
    private static final int CANVAS_SIZE_INCREMENT = 10;
  
    /**
     * constructor
     * @param view - canvas
     * @param key - keyevent
     */
    public CanvasListener(Canvas view, int key) {
        super(view, key);
        System.out.println(key);
    }

   
    
    /**
     * updates the canvas if there was a correct key press from the view.
     */
//    public void update() {
//        if (!tempView.getLastKeyHeuristic()) {
//            return;
//        }
//        tempView.setLastKeyHeuristic(false);
//
//        Collection<Integer> keysPressed = tempView.getKeysPressed();
//
//        if (!keysPressed.contains(tempKey)) {
//            return;
//        }
//
//        if (tempKey == KeyEvent.VK_UP) {
//            tempView.setSize(tempView.getWidth() + CANVAS_SIZE_INCREMENT, 
//                             tempView.getHeight() + CANVAS_SIZE_INCREMENT);
//            tempView.step();
//        }
//        
//        else if (tempKey == KeyEvent.VK_DOWN) {
//            tempView.setBounds(0, 0, tempView.getWidth() - CANVAS_SIZE_INCREMENT, 
//                             tempView.getHeight() - CANVAS_SIZE_INCREMENT);
//        }
//
//    }
    
    
    @Override
    protected void enforce(Force f) {
        if (!(f instanceof WallRepulsionForce)) {
            return;
        }
        System.out.println(getKey());
        int tempKey = getKey();
        Canvas tempView = getView();
        if (tempKey == KeyEvent.VK_UP) {
            tempView.setSize(tempView.getWidth() + CANVAS_SIZE_INCREMENT, 
                           tempView.getHeight() + CANVAS_SIZE_INCREMENT);
            tempView.step();
        }

        else if (tempKey == KeyEvent.VK_DOWN) {
            tempView.setBounds(0, 0, tempView.getWidth() - CANVAS_SIZE_INCREMENT, 
                               tempView.getHeight() - CANVAS_SIZE_INCREMENT);
        }
        
    }
}
