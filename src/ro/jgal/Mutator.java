package ro.jgal;

/**
 * Interface for implementing mutators.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public interface Mutator {

    /**
     * Mutates the chromosomes from a given population. Each
     * individual has a probability pm of mutating.
     * 
     * @param pop - the population which will be mutated
     * @param pm - the probability of each individual to mutate
     * @return - the mutated population
     */
    public Population mutate(Population pop, double pm);

}
