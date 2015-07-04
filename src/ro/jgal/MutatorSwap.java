package ro.jgal;

import java.util.Random;

/**
 * Swap Mutator.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class MutatorSwap implements Mutator {

    /**
     * Mutates the chromosomes from a given population. Each
     * individual has a probability pm of mutating.
     * 
     * @param pop - the population which will be mutated
     * @param pm - the probability of each individual to mutate
     * @return - the mutated population
     */
    @Override
    @SuppressWarnings({"rawtypes","unchecked"})
    public Population mutate(Population pop, double pm) {

        Random r = new Random();

        for (int i = 0; i < pop.length(); i++) {
            if (r.nextDouble() < pm) {
                Chromosome mutant = pop.get(i);

                int pos1 = Utils.randInt(0, mutant.length());
                int pos2 = Utils.randInt(0, mutant.length());

                Object temp;

                temp = mutant.getGene(pos1);

                mutant.setGene(pos1, mutant.getGene(pos2));
                mutant.setGene(pos2, temp);
            }
        }

        return pop;
    }

}
