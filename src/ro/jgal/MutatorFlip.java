package ro.jgal;

import java.util.Random;

/**
 * Flip Mutator.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class MutatorFlip implements Mutator {

    /**
     * Mutates the chromosomes from a given population. Each
     * individual has a probability pm of mutating.
     * 
     * @param pop - the population which will be mutated
     * @param pm - the probability of each individual to mutate
     * @return - the mutated population
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public Population mutate(Population pop, double pm) {

        Random r = new Random();

        for (int i = 0; i < pop.length(); i++) {
            if (r.nextDouble() < pm) {
                Chromosome<Integer> mutant = pop.get(i);

                int pos = Utils.randInt(0, mutant.length());

                mutant.setGene(pos, (mutant.getGene(pos) + 1) % 2);
            }
        }

        return pop;
    }

}