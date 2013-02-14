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
 * @author Aaron Krolik & Matthew Parides
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Mass> myMasses;
    private List<Spring> mySprings;
    private List<Muscle> myMuscles;
    private List<Force> myForces;
    private Dragging myDrag;
    private List<Listener> myForceListeners;
    private List<CanvasListener> myCanvasListeners;

    /**
     * Create a game of the given size with the given display for its shapes.
     * @param canvas - Canvas Object to fill myView
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myForces = new ArrayList<Force>();
        activateForces();
        myForceListeners = new ArrayList<Listener>();
        myCanvasListeners = new ArrayList<CanvasListener>();
        activateListeners();
        

        myDrag = new Dragging();
    }

    /**
     * Draw all elements of the simulation.
     * @param pen - Graphics2D pen for painting
     */
    public void paint (Graphics2D pen) {
        for (Spring s : mySprings) {
            s.paint(pen);
        }
        for (Mass m : myMasses) {
            m.paint(pen);
        }
    }
    
    /**
     * adds the necessary key listeners to myForceListeners
     */
    public void activateListeners() {
        myForceListeners.add(new Listener(myView, Listener.GRAVITY_KEY_VAL));
        myForceListeners.add(new Listener(myView, Listener.VISCOSITY_KEY_VAL));
        myForceListeners.add(new Listener(myView, Listener.TOGGLE_WALL_FORCE_RIGHT));
        myForceListeners.add(new Listener(myView, Listener.TOGGLE_WALL_FORCE_TOP));
        myForceListeners.add(new Listener(myView, Listener.TOGGLE_WALL_FORCE_BOTTOM));
        myForceListeners.add(new Listener(myView, Listener.TOGGLE_WALL_FORCE_LEFT));
        myCanvasListeners.add(new CanvasListener(myView, Listener.DECREASE_CANVAS_SIZE));
        myCanvasListeners.add(new CanvasListener(myView, Listener.INCREASE_CANVAS_SIZE));
        
        
    }
    /**
     * adds the necessary forces to myForces
     */
    public void activateForces() {
        add(new GravityForce());
        add(new ViscosityForce());
        add(new RightWallRepulsionForce(myView));
        add(new LeftWallRepulsionForce());
        add(new TopWallRepulsionForce());
        add(new BottomWallRepulsionForce(myView));
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     * @param elapsedTime - amount of time since last update.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getSize();

        for (Listener l : myForceListeners) {
            l.update(myForces);
        }
        
        for(CanvasListener cl: myCanvasListeners) {
            cl.update();
        }

        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }

        for (Force f : myForces) {
            f.applyForce(myMasses);
        }

        for (Mass m : myMasses) {
            m.update(elapsedTime, bounds);
        }
        myDrag.update(myMasses, mySprings, myView);

    }

    /**
     * Add given mass to this simulation.
     * @param mass input mass object
     */
    public void add (Mass mass) {
        myMasses.add(mass);
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
        this.myMasses = masses;
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
