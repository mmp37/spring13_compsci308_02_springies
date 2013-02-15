package simulation;


/**
 * allows for easy removal from an arrayList
 * @author AK
 */

public class DragSpring extends Spring {

    public DragSpring (Mass start, Mass end, double length, double kVal) {
        super(start, end, length, kVal);
    }
}
