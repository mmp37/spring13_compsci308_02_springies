package simulation;


/**
 * allows for easy removal from an arrayList
 * @author AK
 */

public class DragSpring extends Spring {
    
    /**
     * constructor
     * @param start - mass at one end
     * @param end - mass at other end
     * @param length - length of spring
     * @param kVal - spring constant
     */
    public DragSpring (Mass start, Mass end, double length, double kVal) {
        super(start, end, length, kVal);
    }
}
