package simulation;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import view.Canvas;


/**
 * XXX
 * 
 * @author Robert C. Duvall
 */
public class Factory {
    // data file keywords
    private static final String MASS_KEYWORD = "mass";
    private static final String SPRING_KEYWORD = "spring";
    private static final String MUSCLE_KEYWORD = "muscle";
    private static final String GRAVITY_KEYWORD = "gravity";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String CENTER_MASS_KEYWORD = "centermass";
    private static final String WALL_REPULSION_KEYWORD = "wall";
    private Canvas myCanvas;
    // mass IDs
    private Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();
    
    /**
     * Constructor
     * @param canv - the canvas that this factory will ultimately add objects to
     */
    public Factory(Canvas canv) {
        myCanvas = canv;
    }
    
    

    /**
     * Reads the input file and loads the model appropriately with masses, springs,
     * and muscles.
     * @param model - the model that information will be loaded to
     * @param modelFile - the file that contains the information that will load
     * the model
     */
    public void loadModel (Model model, File modelFile) {
        model.initMyMasses();
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (MASS_KEYWORD.equals(type)) {
                        model.add(massCommand(line));
                    }
                    else if (SPRING_KEYWORD.equals(type)) {
                        model.add(springCommand(line));
                    }
                    else if (MUSCLE_KEYWORD.equals(type)) {
                        model.add(muscleCommand(line));
                    }
                    else if (GRAVITY_KEYWORD.equals(type)) {
                        model.add(gravityCommand(line));
                    }
                    else if (VISCOSITY_KEYWORD.equals(type)) {
                        model.add(viscosityCommand(line));
                    }
                    else if (CENTER_MASS_KEYWORD.equals(type)) {
                        model.add(centerMassCommand(line));
                    }
                    else if (WALL_REPULSION_KEYWORD.equals(type)) {
                        model.add(wallCommand(line));
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    // create mass from formatted data
    private Mass massCommand (Scanner line) {
        int id = line.nextInt();
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        if (mass == -1) {
            Mass result = new FixedMass(x, y, mass);
            myMasses.put(id, result);
            return result;
        }

        Mass result = new Mass(x, y, mass);
        myMasses.put(id, result);
        return result;

    }

    // create spring from formatted data
    private Spring springCommand (Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        return new Spring(m1, m2, restLength, ks);
    }

    // create spring from formatted data
    private Muscle muscleCommand (Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        return new Muscle(m1, m2, restLength, ks);
    }
    
 // create spring from formatted data
    private GravityForce gravityCommand (Scanner line) {
        double angle = line.nextDouble();
        double magnitude = line.nextDouble();
        return new GravityForce(angle, magnitude);
    }
    
 // create spring from formatted data
    private ViscosityForce viscosityCommand (Scanner line) {
        double scaleFactor = line.nextDouble();
        return new ViscosityForce(scaleFactor);
    }
    
 // create spring from formatted data
    private CenterOfMassForce centerMassCommand (Scanner line) {
        double magnitude = line.nextDouble();
        double exp = line.nextDouble();
        return new CenterOfMassForce(magnitude, exp);
    }
    
 // create spring from formatted data
    private WallRepulsionForce wallCommand(Scanner line) {
        int side = line.nextInt();
        double magnitude = line.nextDouble();
        double exp = line.nextDouble();
        return new WallRepulsionForce(exp, magnitude, myCanvas.getWidth(),
                                      myCanvas.getHeight(), side);
    }

}
