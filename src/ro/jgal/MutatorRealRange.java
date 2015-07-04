package ro.jgal;

import java.util.Random;

/**
 * Real Range Mutator.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class MutatorRealRange implements Mutator {

    double minrange;
    double maxrange;

    /**
     * Real Range Mutator. Default paramenters: 
     * minrange = 0, 
     * maxrange = 100.
     */
    public MutatorRealRange() {
        this(Consts.MINRANGE, Consts.MAXRANGE);
    }

    /**
     * Real Range Mutator. 
     * 
     * @param minrange - minimum value
     * @param maxrange - maximum value
     */
    public MutatorRealRange(double minrange, double maxrange) {
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

        Random r = new Random();

        for (Chromosome c : pop) {
            int len = c.length();
            double mutations = pm * len;

            if (mutations < 1.0) {
                mutations = 0;
                for (int i = 0; i < len; i++) {
                    if (r.nextDouble() < pm) {
                        c.getGenes().set(
                                i,
                                Utils.randRealUniform(minrange, maxrange)
                                );
                        mutations += 1;
                    }
                }
            } else {
                int bound = (int) Math.round(mutations);
                for (int i = 0; i < bound; i++) {
                    int index = Utils.randInt(len);
                    c.getGenes().set(
                            index,
                            Utils.randRealUniform(minrange, maxrange)
                            );
                }
            }
        }

        return pop;
    }

}
