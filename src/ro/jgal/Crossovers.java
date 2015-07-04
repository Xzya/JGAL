package ro.jgal;

public class Crossovers {

    /**
     * Single Point Crossover.
     * 
     * @return - crossover
     */
    public static Crossover singlePoint() {
        return new CrossoverSinglePoint();
    }

    /**
     * Two Point Crossover.
     * 
     * @return - crossover
     */
    public static Crossover twoPoint() {
        return new CrossoverTwoPoint();
    }

    /**
     * Uniform Crossover.
     * 
     * @return - crossover
     */
    public static Crossover uniform() {
        return new CrossoverUniform();
    }

    /**
     * Order Crossover. Works well for order optimizations.
     * Use it for integer permutations only.
     * 
     * @return - crossover
     */
    public static Crossover OX() {
        return new CrossoverOX();
    }

    /**
     * Cycle Crossover. Works well for order optimizations.
     * Use it for integer permutations only.
     * 
     * @return - crossover
     */
    public static Crossover CX() {
        return new CrossoverCX();
    }

    /**
     * Edge Crossover. Works well for order optimizations.
     * Use it for integer permutations only.
     * 
     * @return - crossover
     */
    public static Crossover edge() {
        return new CrossoverEdge();
    }

}
