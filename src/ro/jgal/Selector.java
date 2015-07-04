package ro.jgal;

/**
 * Interface for implementing selection operators.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public interface Selector {

    /**
     * Selects the parents from a given population
     * 
     * @param pop - the population from which the parents are chosen
     * @return parents - the parents population
     */
    public Population select(Population pop);

}
