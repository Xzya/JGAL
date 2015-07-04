package ro.jgal;

/**
 * Interface for implementing termination criterias.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public interface TerminationCriteria {

    /**
     * Checks if the termination criteria has been reached.
     * 
     * @param pop - checked population
     * @return - true if criteria is reached
     */
    public boolean terminate(Population pop);

}
