package ro.jgal;

import java.util.Random;

/**
 * Real Gaussian Mutator.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("unchecked")
public class MutatorRealGaussian implements Mutator {

    double mu;
    double sigma;
    double minrange;
    double maxrange;

    /**
     * Real Gaussian Mutator. Default paramenters: 
     * minrange = 0, 
     * maxrange = 100.
     */
    public MutatorRealGaussian() {
        this(Consts.MINRANGE, Consts.MAXRANGE);
    }

    /**
     * Real Gaussian Mutator. 
     * 
     * @param minrange - minimum value
     * @param maxrange - maximum value
     */
    public MutatorRealGaussian(double minrange, double maxrange) {
        this.minrange = minrange;
        this.maxrange = maxrange;
    }

    /**
     * Mutates the chromosomes from a given population. Each
     * individual has a probability pm of mutating.
     * 
     * @param pop - the population which will be mutated
     * @param pm - the probability of each individual to mutate
     * @return - the mutated population
     */
    @Override
    public Population mutate(Population pop, double pm) {

        if (pm < 0.0) {
            return pop;
        }

        Random r = new Random();
        for (Chromosome<Double> c : pop) {

            double mutations = pm * c.length();

            if (mutations < 1.0) {
                if (r.nextDouble() < pm) {
                    for (int i = 0; i < c.length(); i++) {
                        double finalValue = (Double) c.getGene(i) + r.nextGaussian();

                        finalValue = Math.min(finalValue, maxrange);
                        finalValue = Math.max(finalValue, minrange);

                        c.setGene(i, finalValue);
                    }
                }
            } else {
                int bound = (int) Math.round(mutations);
                for (int i = 0; i < bound; i++) {
                    int index = r.nextInt(c.length());
                    double finalValue = (Double) c.getGene(index) + r.nextGaussian();

                    finalValue = Math.min(finalValue, maxrange);
                    finalValue = Math.max(finalValue, minrange);

                    c.setGene(index, finalValue);
                }
            }
        }

        return pop;
    }

}
