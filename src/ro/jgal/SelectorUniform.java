package ro.jgal;

import java.util.Random;

/**
 * Uniform Selector.
 * 
 * The parents are selected randomly from the population.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public class SelectorUniform implements Selector {

    /**
     * Selects the parents from a given population.
     * 
     * @param pop - the population from which the parents are chosen
     * @return parents - the parents population
     */
    @Override
    public Population select(Population pop) {
        Random rand = new Random();
        Population parents = new Population();

        try {
            for (int i = 0; i < pop.length(); i++) {
                int p1 = rand.nextInt(pop.length());
                int p2 = rand.nextInt(pop.length());
                while (p2 == p1) {
                    p2 = rand.nextInt(pop.length());
                }

                if (pop.get(p1).getFitnessValue() > pop.get(p2).getFitnessValue()) {
                    parents.add((Chromosome) pop.get(p1).clone());
                } else {
                    parents.add((Chromosome) pop.get(p2).clone());
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return parents;
    }

}
