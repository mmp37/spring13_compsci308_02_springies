package simulation;


import java.util.ArrayList;
import java.util.Collection;
import view.Canvas;



/**
 * Listener specialized for clearing assemblies in a corresponding model
 * @author AK
 *not sure why it's not working. enforce never seems to get called for some reason
 */
public class NewAssemblyListener extends Listener {

    
    private static final int myKey = Listener.NEW_ASSEMBLY_KEY_VAL;

    
    
    /**
     * constructor
     * @param view - view canvas
     * @param key - key event for this listener
     * @param mod - the model that this listener connects to
     */
    public NewAssemblyListener (Canvas view, int key) {
    	super(view, 78);
       System.out.println("instance");
    }
    
    /**
     * constructor
     */
    public NewAssemblyListener() {
    	
    }
    
    @Override
    protected void enforce(Force f){
    	System.out.println("newassembly");
    	getView().loadNewModel();
    }
   
}
