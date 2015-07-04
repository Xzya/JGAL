package ro.jgal;

/**
 * Interface for implementing initializers.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public interface Initializer {

    /**
     * Creates a new chromosome.
     * 
     * @return - new chromosome
     */
    public Chromosome initialize();

}
