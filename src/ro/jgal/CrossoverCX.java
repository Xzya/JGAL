package ro.jgal;

import java.util.ArrayList;

/**
 * Cycle Crossover. Works well for order optimizations.
 * Use it for integer permutations only.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class CrossoverCX implements Crossover {

    int index;

    /**
     * Recombines the given parents population. Each
     * individual has a probability pc of recombining.
     * 
     * @param parents - the parents population
     * @param pc - recombination probability
     * @return - children population
     */
    @Override
    public Population crossover(Population parents, double pc) {
        if (pc == 0.0) {
            return parents;
        }
        if (parents.length() < 2) {
            return parents;
        }

        try {

            Population children = (Population) parents.clone();

            for (int i = 0; i < parents.length(); i+=2) {
                if (Math.random() < pc) {
                    if (i+1 == parents.length()) {
                        break;
                    }
                    Chromosome mom = parents.get(i);
                    Chromosome dad = parents.get(i+1);

                    Chromosome sister = children.get(i);
                    Chromosome brother = children.get(i+1);

                    int[] cycle = findCycles(mom.getGenes(), dad.getGenes());

                    for (int j = 1; j < index; j+=2) {
                        for (int k = 0; k < cycle.length; k++) {
                            if (cycle[k] == j) {
                                sister.setGene(k, dad.getGene(k));
                                brother.setGene(k, mom.getGene(k));
                            }
                        }
                    }
                }
            }

            return children;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds the cycles in two given parents genes.
     * 
     * @param mom - first parent's genes
     * @param dad - second parent's genes
     * @return - integer array of cycles
     */
    private int[] findCycles(ArrayList<Object> mom, ArrayList<Object> dad) {
        int i = 0;
        int j = 0;
        index = 0;
        boolean notDone = true;
        int[] cycle = new int[mom.size()];

        while (notDone) {
            Object a = dad.get(i);
            cycle[i] = index;
            while (!mom.get(i).equals(a)) {
                for (int k = 0; k < cycle.length; k++) {
                    if (mom.get(k).equals(a)) {
                        j = k;
                        break;
                    }
                }
                cycle[j] = index;
                a = dad.get(j);
            }

            notDone = false;

            for (int k = 0; k < cycle.length; k++) {
                if (cycle[k] == 0) {
                    i = k;
                    index++;
                    notDone = true;
                    break;
                }
            }
        }
        return cycle;
    }

}
