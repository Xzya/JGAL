package ro.jgal;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Integer Permutation Initializer.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes"})
public class InitializerIntegerPermutation implements Initializer {

    int minrange;
    int maxrange;

    /**
     * Integer Permutation Initializer.
     * 
     * @param minrange - minimum value (inclusive)
     * @param maxrange - maximum value (exclusive)
     */
    public InitializerIntegerPermutation(int minrange, int maxrange) {
        this.minrange = minrange;
        this.maxrange = maxrange;
    }

    /**
     * Creates a new chromosome.
     * 
     * @return - new chromosome
     */
    @Override
    public Chromosome initialize() {
        ArrayList<Integer> genes = new ArrayList<Integer>();

        for (int i = minrange; i < maxrange; i++) {
            genes.add(i);
        }
        Collections.shuffle(genes);

        Chromosome<Integer> c = new Chromosome<Integer>(genes);
        return c;
    }

}
