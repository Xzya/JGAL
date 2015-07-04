package ro.jgal;


/**
 * Roulette Wheel Selector.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public class SelectorRouletteWheel implements Selector {

    double sum;
    double[] probability;
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
        parents.setMinimax(pop.getMinimax());

        try {

            initialize(pop);

            int lower = 0;
            int upper = pop.length() - 1;
            double cut;

            while (parents.length() < pop.length()) {
                cut = Math.random();

                while (upper >= lower) {
                    int i = lower + ((upper - lower) / 2);
                    if (probability[i] > cut) {
                        upper = i - 1;
                    } else {
                        lower = i + 1;
                    }
                }

                lower = Math.min(pop.length() - 1, lower);
                lower = Math.max(0, lower);

                parents.add((Chromosome) pop.get(lower).clone());
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return parents;
    }

    /**
     * Computes the probability of each individual and the 
     * cumulated probability required by the algorithm.
     * 
     * @param pop
     */
    void initialize(Population pop) {
        int len = pop.length();

        probability = new double[len];

        double pop_min = pop.getWorstValue();
        double pop_max = pop.getBestValue();

        if (pop_max == pop_min) {
            for (int i = 0; i < len; i++) {
                probability[i] = (i + 1) / ((float) len);
            }
        } else if ((pop_max > 0 && pop_min >= 0) || (pop_max <= 0 && pop_min < 0)) {
            pop.sort();

            if (pop.minimax == Consts.MAXIMIZE) {
                sumOfProbability = 0;
                for (int i = 0; i < len; i++) {
                    sumOfProbability += pop.get(i).getFitnessValue();
                    probability[i] = sumOfProbability;
                }
                for (int i = 0; i < len; i++) {
                    probability[i] /= probability[len-1];
                }
            } else {
                sumOfProbability = 0;
                for (int i = 0; i < len; i++) {
                    sumOfProbability += - pop.get(i).getFitnessValue() + pop_max + pop_min;
                    probability[i] = sumOfProbability;
                }
                for (int i = 0; i < len; i++) {
                    probability[i] /= probability[len-1];
                }
            }
        }
    }

}
