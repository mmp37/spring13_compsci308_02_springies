package simulation;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.List;
import view.Canvas;


public class Listener {

    private Canvas myView;
    private int myKey;
    public static final int MASS_KEY_VAL = KeyEvent.VK_M;
    public static final int GRAVITY_KEY_VAL = KeyEvent.VK_G;
    public static final int VISCOSITY_KEY_VAL = KeyEvent.VK_V;
    public static final int NEW_ASSEMBLY_KEY_VAL = KeyEvent.VK_N;
    public static final int CLEAR_ASSEMBLIES = KeyEvent.VK_C;
    public static final int TOGGLE_WALL_FORCE_RIGHT = KeyEvent.VK_1;
    public static final int TOGGLE_WALL_FORCE_DOWN = KeyEvent.VK_2;
    public static final int TOGGLE_WALL_FORCE_LEFT = KeyEvent.VK_UP;
    public static final int TOGGLE_WALL_FORCE_UP = KeyEvent.VK_4;
    public static final int INCREASE_CANVAS_SIZE = KeyEvent.VK_UP;
    public static final int DECREASE_CANVAS_SIZE = KeyEvent.VK_DOWN;

    public Listener (Canvas view, int key) {
        myView = view;
        myKey = key;
    }

    public Listener () {
    }

    public void update (List<Force> forces) {
        if (!myView.getLastKeyHeuristic())
            return;
        myView.setLastKeyHeuristic(false);

        Collection<Integer> keysPressed = myView.getKeysPressed();

        if (!keysPressed.contains(myKey)) {
            return;
        }

        for (Force f : forces) {

            f.toggleState();

        }

    }

}
