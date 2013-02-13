package simulation;

import java.util.Collection;
import java.util.List;

import view.Canvas;

public class ForceListener extends Listener{
        public static final int MASS_KEY_VAL = 77;
        public static final int GRAVITY_KEY_VAL  = 71;
        public static final int VISCOSITY_KEY_VAL = 86;
        
        public ForceListener(Canvas view, int key){
                super(view, key);
        }
        


}