package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import view.Canvas;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
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
    private List<Listener> myListeners;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myForces = new ArrayList<Force>();
        add(new GravityForce());
        add(new ViscosityForce());
        add(new WallRepulsionForce(myView));

        myListeners = new ArrayList<Listener>();
        myListeners.add(new Listener(myView, Listener.GRAVITY_KEY_VAL));
        myListeners.add(new Listener(myView, Listener.VISCOSITY_KEY_VAL));

        myDrag = new Dragging();
    }

    /**
     * Draw all elements of the simulation.
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
     * Update simulation for this moment, given the time since the last moment.
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
     */
    public void add (Spring spring) {
        mySprings.add(spring);
    }

    public void add (Force force) {
        myForces.add(force);
    }

}
