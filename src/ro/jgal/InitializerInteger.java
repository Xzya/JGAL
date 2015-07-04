package ro.jgal;

import java.util.ArrayList;

/**
 * Integer Initializer.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes"})
public class InitializerInteger implements Initializer {

    int minrange;
    int maxrange;
    int length;

    /**
     * Integer Initializer. Default parameters:
     * minrange = 0, 
     * maxrange = 100, 
     * length = 2.
     */
    public InitializerInteger() {
        this((int) Consts.MINRANGE, (int) Consts.MAXRANGE, 2);
    }

    /**
     * Integer Initializer.
     * 
     * @param minrange - minimum value (inclusive)
     * @param maxrange - maximum value (exclusive)
     * @param length - length of the chromosome
     */
    public InitializerInteger(int minrange, int maxrange, int length) {
        this.minrange = minrange;
        this.maxrange = maxrange;
        this.length = length;
    }

    /**
     * Creates a new chromosome.
     * 
     * @return - new chromosome
     */
    @Override
    public Chromosome initialize() {
        ArrayList<Integer> genes = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            genes.add(Utils.randInt(minrange, maxrange));
        }

        Chromosome<Integer> c = new Chromosome<Integer>(genes);
        return c;
    }

}
