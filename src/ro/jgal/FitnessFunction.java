package ro.jgal;

/**
 * Interface for implementing the fitness function.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public interface FitnessFunction {

    /**
     * Computes the fitness value of the given chromosome.
     * 
     * @param chromosome
     * @return - fitness value
     */
    double evaluate(Chromosome chromosome);

}
