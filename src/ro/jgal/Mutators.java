package ro.jgal;

public class Mutators {

    /**
     * Real Gaussian Mutator. Default paramenters: 
     * minrange = 0, 
     * maxrange = 100.
     * 
     * @return - mutator
     */
    public static Mutator realGaussian() {
        return new MutatorRealGaussian();
    }

    /**
     * Real Gaussian Mutator. 
     * 
     * @param minrange - minimum value
     * @param maxrange - maximum value
     * @return - mutator
     */
    public static Mutator realGaussian(double minrange, double maxrange) {
        return new MutatorRealGaussian(minrange, maxrange);
    }

    /**
     * Real Range Mutator. Default paramenters: 
     * minrange = 0, 
     * maxrange = 100.
     * 
     * @return - mutator
     */
    public static Mutator realRange() {
        return new MutatorRealRange();
    }

    /**
     * Real Range Mutator. 
     * 
     * @param minrange - minimum value
     * @param maxrange - maximum value
     * @return - mutator
     */
    public static Mutator realRange(double minrange, double maxrange) {
        return new MutatorRealRange(minrange, maxrange);
    }

    /**
     * Swap Mutator. 
     * 
     * @return - mutator
     */
    public static Mutator swap() {
        return new MutatorSwap();
    }

    /**
     * Flip Mutator.
     * 
     * @return - mutator
     */
    public static Mutator flip() {
        return new MutatorFlip();
    }

    /**
     * String Mutator.
     * 
     * @param allowedCharacters - allowed characters
     * @return - mutator
     */
    public static Mutator string(String allowedCharacters) {
        return new MutatorString(allowedCharacters);
    }

}
