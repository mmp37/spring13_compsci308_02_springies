package simulation;


import java.util.ArrayList;
import java.util.Collection;
import view.Canvas;


/**
 * Listener specialized for clearing assemblies in a corresponding model
 * @author Matthew Parides
 *
 */
public class ClearAssemblyListener extends Listener {

    private Canvas myView;
    private int myKey;
    private Model myModel;
    
    
    /**
     * constructor
     * @param view - view canvas
     * @param key - key event for this listener
     * @param mod - the model that this listener connects to
     */
    public ClearAssemblyListener (Canvas view, int key, Model mod) {
    	super(view, key);
//        myView = view;
//        myKey = key;
//        myModel = mod;
    }
    
    /**
     * constructor
     */
    public ClearAssemblyListener() {
    }
    
    /**
     * toggles forces appropriately - depends on current force state(on/off)
     * and key presses.
     */
    public void clearAssemblies() {
        if (!myView.getLastKeyHeuristic()) {
            return;
        }
        myView.setLastKeyHeuristic(false);

        Collection<Integer> keysPressed = myView.getKeysPressed();

        if (!keysPressed.contains(myKey)) {
            return;
        }

        ArrayList<Mass> emptyMass = new ArrayList<Mass>();
        ArrayList<Spring> emptySpring = new ArrayList<Spring>();
        ArrayList<Muscle> emptyMuscle = new ArrayList<Muscle>();
        
        myModel.setMySprings(emptySpring);
        myModel.setMyMasses(emptyMass);
        myModel.setMyMuscles(emptyMuscle);

    }
}
