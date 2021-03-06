package simulation;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.List;
import view.Canvas;

/**
 * class that handles keyListening for the springies model
 * @author Matthew Parides & Aaron Krolik (AK designed Listener class hierarchy)
 *
 */
public class Listener {
    /**
     * these are the key values for the keystrokes corresponding to 
     * actions in the program
     */
    public static final int MASS_KEY_VAL = KeyEvent.VK_M;
    public static final int GRAVITY_KEY_VAL = KeyEvent.VK_G;
    public static final int VISCOSITY_KEY_VAL = KeyEvent.VK_V;
    public static final int NEW_ASSEMBLY_KEY_VAL = KeyEvent.VK_N;
    public static final int CLEAR_ASSEMBLIES = KeyEvent.VK_C;
    public static final int TOGGLE_WALL_FORCE_RIGHT = KeyEvent.VK_2;
    public static final int TOGGLE_WALL_FORCE_BOTTOM = KeyEvent.VK_3;
    public static final int TOGGLE_WALL_FORCE_LEFT = KeyEvent.VK_4;
    public static final int TOGGLE_WALL_FORCE_TOP = KeyEvent.VK_1;
    public static final int INCREASE_CANVAS_SIZE = KeyEvent.VK_UP;
    public static final int DECREASE_CANVAS_SIZE = KeyEvent.VK_DOWN;

    private Canvas myView;
    private int myKey;
    
    /**
     * constructor
     * @param view - view canvas
     * @param key - key event for this listener
     */
    public Listener (Canvas view, int key) {
        myView = view;
        myKey = key;
    }
    
    /**
     * constructor
     */
    public Listener () {
    }

    /**
     * getter for the listener's view
     * @return myView - canvas view for this listener
     */
    protected Canvas getView() {
        return myView;
    }
    
    /**
     * getter for this instance of listener's key value
     * @return myKey
     */
    protected int getKey() {
        return myKey;
    }
    
    /**
     * toggles forces appropriately - depends on current force state(on/off)
     * and key presses.
     * @param forces - forces to be toggled
     */
    public void update (List<Force> forces) {
        if (!myView.getLastKeyHeuristic()) {
            return;
        }
        myView.setLastKeyHeuristic(false);

        Collection<Integer> keysPressed = myView.getKeysPressed();

        if (!keysPressed.contains(myKey)) {
            return;
        }
        for (Force f : forces) {

            enforce(f);

        }

    }
    
    
    /*
     * default for common toggle forces
     */
    protected void enforce(Force f) {
        f.toggleState(myKey);
    }


    
}
