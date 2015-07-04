package ro.jgal;

/**
 * Interface for implementing crossover operators.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public interface Crossover {

    /**
     * Recombines the given parents population. Each
     * individual has a probability pc of recombining.
     * 
     * @param parents - the parents population
     * @param pc - recombination probability
     * @return - children population
     */
    public Population crossover(Population parents, double pc);

}
