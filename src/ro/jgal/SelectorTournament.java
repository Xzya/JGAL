package ro.jgal;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tournament Selection. 
 * 
 * The parents are selected by creating random tournament pools
 * of n individuals (default is 2) and choosing the i'th best of 
 * them with the probability p^i (default probability is 1.0, 
 * so the best individual will always be chosen).
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public class SelectorTournament implements Selector {

    int poolSize;
    double probability;

    /**
     * Tournament Selector. Default parameters:
     * poolSize = 2,
     * probability = 1.0
     */
    public SelectorTournament() {
        this(Consts.TOURNAMENT_SIZE, Consts.TOURNAMENT_P);
    }

    /**
     * Tournament Selector. 
     * 
     * @param poolSize - the size of the tournament pool
     * @param probability - the probability of the i'th best individual
     * to be chosen (1.0 -> the best one will always be chosen)
     */
    public SelectorTournament(int poolSize, double probability) {
        this.poolSize = poolSize;
        this.probability = probability;
    }

    /**
     * Selects the parents from a given population. 
     * 
     * @param pop - the population from which the parents are chosen
     * @return parents - the parents population
     */
    @Override
    public Population select(Population pop) {

        if (probability < 0.0) {
            return pop;
        }
        if (poolSize < 1) {
            return pop;
        }

        Population parents = new Population();
        parents.setMinimax(pop.getMinimax());

        try {
            ArrayList<Chromosome> tournamentPool;

            int len = pop.length();

            while (parents.length() < pop.length()) {

                tournamentPool = new ArrayList<Chromosome>();

                //add random individuals to the pool
                for (int i = 0; i < poolSize; i++) {
                    tournamentPool.add(pop.get(Utils.randInt(len)));
                }

                //sort the individuals in the pool in descending order
                sortDescendingOrder(tournamentPool);

                //select i'th best individual with the probability p^i
                double p = Math.random();
                for (int i = 0; i < poolSize; i++) {
                    if (p < (Math.pow(probability, i+1))) {
                        parents.add((Chromosome) tournamentPool.get(i).clone());
                        break;
                    }
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return parents;
    }

    /**
     * Sorts the given array in descending order by their fitness value.
     * 
     * @param tournamentPool
     */
    private void sortDescendingOrder(ArrayList<Chromosome> tournamentPool) {
        Collections.sort(tournamentPool, (x,y) -> {
            if (x.getFitnessValue() > y.getFitnessValue()) {
                return -1;
            } else if (x.getFitnessValue() < y.getFitnessValue()) {
                return 1;
            }
            return 0;
        });
    }

}
