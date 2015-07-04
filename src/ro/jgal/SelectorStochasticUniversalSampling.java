package ro.jgal;

import java.util.Random;

/**
 * Stochastic Universal Sampling Selector.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public class SelectorStochasticUniversalSampling implements Selector {

    double sum;
    double[] probability;
    double[] cumulatedProbability;
    double sumOfProbability;

    /**
     * Selects the parents from a given population.
     * 
     * @param pop - the population from which the parents are chosen
     * @return parents - the parents population
     */
    @Override
    public Population select(Population pop) {
        Population parents = new Population();
        Random r = new Random();

        try {
            sum = 0;
            probability = new double[pop.length()];
            cumulatedProbability = new double[pop.length()];
            sumOfProbability = 0;

            for (Chromosome c : pop) {
                sum += c.getFitnessValue();
            }

            for (int i = 0; i < pop.length(); i++) {
                probability[i] = pop.get(i).getFitnessValue() / sum;
                sumOfProbability += probability[i];
                probability[i] += sumOfProbability;
                cumulatedProbability[i] = sumOfProbability;
            }

            double step = 1.0 / pop.length();
            double rand = r.nextDouble() * step;
            int i = 0;
            while (parents.length() < pop.length()) {
                while (rand < cumulatedProbability[i]) {
                    parents.add((Chromosome) pop.get(i).clone()); 
                    rand += step;
                }
                i++;
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return parents;
    }

}
