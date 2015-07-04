package ro.jgal;

/**
 * Constants class.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class Consts {

    public static final int MINIMIZE = 1;
    public static final int MAXIMIZE = 2;

    public static final double MINRANGE = 0;
    public static final double MAXRANGE = 100;

    //probability of gene swap for uniform crossover
    public static final double UNIFORM_CX = 0.5;

    //number of elitism replacements
    public static final int NELITISM = 1;

    //remove duplicates after n iterations
    public static final int NITERATIONS = 100;

    //tournament size
    public static final int TOURNAMENT_SIZE = 2;
    //probability to select the best individual from
    //the tournament pool
    public static final int TOURNAMENT_P = 1;

    //binary string length
    public static final int BINARY_STRING_LENGTH = 5;

}
