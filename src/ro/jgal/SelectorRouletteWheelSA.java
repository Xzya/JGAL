package ro.jgal;

import java.util.Random;

/**
 * Roulette Wheel Selector using stochastic acceptance.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public class SelectorRouletteWheelSA implements Selector {

    /**
     * Selects the parents from a given population.
     * 
     * @param pop - the population from which the parents are chosen
     * @return parents - the parents population
     */
    @Override
    public Population select(Population pop) {

        Population parents = new Population();
        Random rand = new Random();
        boolean notAccepted;
        int index = 0;

        try {
            for (int i = 0; i < pop.length(); i++) {
                notAccepted = true;

                while (notAccepted) {
                    index = rand.nextInt(pop.length());
                    if (rand.nextDouble() < Math.abs(pop.get(index).getFitnessValue() / 
                            Math.abs(pop.getBestValue()))) {
                        notAccepted = false;
                    }
                }

                parents.add((Chromosome) pop.get(index).clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return parents;
    }

}
