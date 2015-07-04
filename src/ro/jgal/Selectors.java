package ro.jgal;

public class Selectors {

    /**
     * Roulette Wheel Selector.
     * 
     * @return - selector
     */
    public static Selector rouletteWheel() {
        return new SelectorRouletteWheel();
    }

    /**
     * Roulette Wheel Selector using stochastic acceptance.
     * 
     * @return - selector
     */
    public static Selector rouletteWheelSA() {
        return new SelectorRouletteWheelSA();
    }

    /**
     * Uniform Selector.
     * 
     * @return - selector
     */
    public static Selector uniform() {
        return new SelectorUniform();
    }

    /**
     * Stochastic Universal Sampling Selector.
     * 
     * @return - selector
     */
    public static Selector stochasticUniversalSampling() {
        return new SelectorStochasticUniversalSampling();
    }

    /**
     * Tournament Selector. Default parameters:
     * poolSize = 2,
     * probability = 1.0
     * 
     * @return - selector
     */
    public static Selector tournament() {
        return new SelectorTournament();
    }

    /**
     * Tournament Selector. 
     * 
     * @param poolSize - the size of the tournament pool
     * @param probability - the probability of the i'th best individual
     * to be chosen (1.0 -> the best one will always be chosen)
     * @return - selector
     */
    public static Selector tournament(int poolSize, double probability) {
        return new SelectorTournament(poolSize, probability);
    }

}
