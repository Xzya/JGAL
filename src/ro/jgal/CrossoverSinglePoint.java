package ro.jgal;

/**
 * Single Point Crossover.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"unchecked"})
public class CrossoverSinglePoint implements Crossover {

    /**
     * Recombines the given parents population. Each
     * individual has a probability pc of recombining.
     * 
     * @param parents - the parents population
     * @param pc - recombination probability
     * @return - children population
     */
    @Override
    public Population crossover(
            Population parents, double pc) {
        if (pc < 0.0) {
            return parents;
        }
        if (parents.length() < 2) {
            return parents;
        }
        if (parents.get(0).length() == 1) {
            throw new IllegalStateException("Can't use Single Point Crossover on a chromosome with 1 gene.");
        }

        try {

            Population children = (Population) parents.clone();

            for (int i = 0; i < parents.length(); i+=2) {
                if (Math.random() < pc) {
                    if (i+1 == parents.length()) {
                        break;
                    }
                    Chromosome<Object> mom = parents.get(i);
                    Chromosome<Object> dad = parents.get(i+1);

                    Chromosome<Object> sister = children.get(i);
                    Chromosome<Object> brother = children.get(i+1);

                    int pos = (int) (Math.random() * mom.length());

                    for (int j = pos; j < sister.length(); j++) {
                        sister.setGene(j, dad.getGene(j));
                        brother.setGene(j, mom.getGene(j));
                    }
                }
            }

            return children;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

}
