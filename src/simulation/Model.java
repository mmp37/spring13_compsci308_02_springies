package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import view.Canvas;


/**
 * Model class that runs a physics simulation
 * 
 * @author Robert C. Duvall
 * @author  Matthew Parides
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Mass> myMasses2;
    private List<ArrayList<Mass>> myMasses;
    private List<Spring> mySprings;
    private List<Muscle> myMuscles; //not used
    private List<Force> myForces;
    private Dragging myDrag;
    private List<Listener> myListeners;
    private int myMassesIndex = 0;

    /**
     * Create a game of the given size with the given display for its shapes.
     * @param canvas - Canvas Object to fill myView
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses2 = new ArrayList<Mass>();
        myMasses = new ArrayList< ArrayList<Mass> >();
        myMasses.add(new ArrayList<Mass>());
        mySprings = new ArrayList<Spring>();
        myForces = new ArrayList<Force>();
        activateForces();
        myListeners = new ArrayList<Listener>();
        activateListeners();
        myDrag = new Dragging();
    }

    /**
     * 
     */
    public void initMyMasses(){
    	myMasses.add(new ArrayList<Mass>());
    	myMassesIndex = myMasses.size()-1;
    }
    
    /**
     * Draw all elements of the simulation.
     * @param pen - Graphics2D pen for painting
     */
    public void paint (Graphics2D pen) {
        for (Spring s : mySprings) {
            s.paint(pen);
        }
        for (List<Mass> masses : myMasses) {
        	for (Mass m : masses){
        		m.paint(pen);
        	}
        }
    }
    
    /**
     * adds the necessary key listeners to myListeners
     */
    public void activateListeners() {
        myListeners.add(new Listener(myView, Listener.GRAVITY_KEY_VAL));
        myListeners.add(new Listener(myView, Listener.VISCOSITY_KEY_VAL));
        myListeners.add(new Listener(myView, Listener.TOGGLE_WALL_FORCE_RIGHT));
        myListeners.add(new Listener(myView, Listener.TOGGLE_WALL_FORCE_TOP));
        myListeners.add(new Listener(myView, Listener.TOGGLE_WALL_FORCE_BOTTOM));
        myListeners.add(new Listener(myView, Listener.TOGGLE_WALL_FORCE_LEFT));
        myListeners.add(new CanvasListener(myView, Listener.DECREASE_CANVAS_SIZE));
        myListeners.add(new CanvasListener(myView, Listener.INCREASE_CANVAS_SIZE));
        myListeners.add(new NewAssemblyListener(myView, Listener.NEW_ASSEMBLY_KEY_VAL));

    }
    /**
     * adds the necessary forces to myForces
     */
    public void activateForces() {
        add(new GravityForce());
        add(new ViscosityForce());
        add(new WallRepulsionForce(0,1,myView.getWidth(), myView.getHeight(), 1));
        add(new WallRepulsionForce(0,1,myView.getWidth(), myView.getHeight(), 2));
        add(new WallRepulsionForce(0,1,myView.getWidth(), myView.getHeight(), 3));
        add(new WallRepulsionForce(0,1,myView.getWidth(), myView.getHeight(), 4));
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     * @param elapsedTime - amount of time since last update.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getSize();

        for (Listener l : myListeners) {
            l.update(myForces);
        }
        

        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }

        for (Force f : myForces) {
//        	for (ArrayList<Mass> masses : myMasses){
//        		f.applyForce(masses);
//        	}
            f.applyForce(myMasses);
        }
        
        for (List<Mass> masses : myMasses) {
        	for (Mass m : masses){
        		m.update(elapsedTime, bounds);
        	}
        	
        }
        myDrag.update(myMasses, mySprings, myView);
//        for (Mass m : myMasses) {
//            m.update(elapsedTime, bounds);
//        }
//        myDrag.update(myMasses, mySprings, myView);

    }

    /**
     * Add given mass to this simulation.
     * @param mass input mass object
     */
    public void add (Mass mass) {
       // myMasses.add(mass);
        myMasses.get(myMassesIndex).add(mass);
    }

    /**
     * Add given spring to this simulation.
     * @param spring input spring object
     */
    public void add (Spring spring) {
        mySprings.add(spring);
    }
    
    /**
     * Add input force to this simulation
     * @param force input force object
     */
    public void add (Force force) {
        myForces.add(force);
    }
    
    /**
     * sets the masses in this construct
     * @param myMasses
     */
    public void setMyMasses (List<Mass> masses) {
      //  this.myMasses.get(myMassesIndex) = masses;
    }
    
    /**
     * sets the springs in this construct
     * @param mySprings
     */
    public void setMySprings (List<Spring> springs) {
        this.mySprings = springs;
    }

    /**
     * sets the muscles in this construct
     * @param myMuscles
     */
    public void setMyMuscles (List<Muscle> myMuscles) {
        this.myMuscles = myMuscles;
    }
}
