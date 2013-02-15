package simulation;


import view.Canvas;



/**
 * Listener specialized for clearing assemblies in a corresponding model
 * @author AK
 *not sure why it's not working. enforce never seems to get called for some reason
 */
public class NewAssemblyListener extends Listener {

    
    private static final int NEW_ASSEMBLY_KEY = Listener.NEW_ASSEMBLY_KEY_VAL;

    
    
    /**
     * constructor
     * @param view - view canvas
     * @param key - key event for this listener
     */
    public NewAssemblyListener (Canvas view, int key) {
        super(view, NEW_ASSEMBLY_KEY);
        System.out.println("instance");
    }

    /**
     * constructor
     */
    public NewAssemblyListener() {
    
    }
    
    @Override
    protected void enforce(Force f) {
        System.out.println("newassembly");
        getView().loadNewModel();
    }

}
