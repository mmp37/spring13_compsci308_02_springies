package simulation;

import java.util.List;


/**
 * Force parent class that provides general functionality to force subclasses
 * @author Aaron Krolik
 *
 */
public class Force {
    private boolean myState = true;
    private int myKeyVal;

    /**
     * default constructor
     */
    public Force () {

    }
    
    /**
     * set's this force's key listener value
     * @param in - this force's key value
     */
    public Force (int in) {
        myKeyVal = in;
    }
    
    /**
     * return this force's keyvalue to respond to
     * @return KEY_VAL
     */
    public int getKeyVal () {
        return myKeyVal;
    }
    
    /**
     * turns this force on if it is off, off if it is on.
     */
    public void toggleState () {
        myState = !myState;
    }
    
    /**
     * returns this force's state (on/off)
     * @return state - (on/off state)
     */
    protected boolean isOn () {
        return myState;
    }

    /**
     * applies the implemented force class's force on input masses.
     * 
     * @param masses - input masses
     */
    public void applyForce (List<Mass> masses) {

    }

}
