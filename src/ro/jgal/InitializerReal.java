package ro.jgal;

import java.util.ArrayList;


/**
 * Real Initializer.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public class InitializerReal implements Initializer {

    double minrange;
    double maxrange;
    int length;

    /**
     * Real Initializer. Default parameters:
     * minrange = 0, 
     * maxrange = 100, 
     * length = 2
     */
    public InitializerReal() {
        this(Consts.MINRANGE, Consts.MAXRANGE, 2);
    }

    /**
     * Real Initializer.
     * 
     * @param minrange - minimum value (inclusive)
     * @param maxrange - maximum value (exclusive)
     * @param length - length of the chromosome
     */
    public InitializerReal(double _minrange, double _maxrange, int _length) {
        minrange = _minrange;
        maxrange = _maxrange;
        length = _length;
    }

    /**
     * Creates a new chromosome.
     * 
     * @return - new chromosome
     */
    @Override
    public Chromosome initialize() {

        ArrayList<Double> genes = new ArrayList<Double>();
        for (int i = 0; i < length; i++) {
            genes.add(Utils.randRealUniform(minrange, maxrange));
        }

        Chromosome<Double> c = new Chromosome<Double>(genes);
        return c;
    }

}
