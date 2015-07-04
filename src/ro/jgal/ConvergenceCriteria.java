package ro.jgal;

/**
 * Terminates when the population converges.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class ConvergenceCriteria implements TerminationCriteria {

    /**
     * Checks if the termination criteria has been reached.
     * 
     * @param pop - checked population
     * @return - true if criteria is reached
     */
    @Override
    public boolean terminate(Population pop) {
        pop.sort();

        return pop.get(0).equals(pop.get(pop.length()-1));
    }

}
