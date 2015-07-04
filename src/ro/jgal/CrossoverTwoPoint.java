package ro.jgal;

/**
 * Two Point Crossover.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class CrossoverTwoPoint implements Crossover {

    /**
     * Recombines the given parents population. Each
     * individual has a probability pc of recombining.
     * 
     * @param parents - the parents population
     * @param pc - recombination probability
     * @return - children population
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public Population crossover(Population parents, double pc) {
        if (pc == 0.0) {
            return parents;
        }
        if (parents.length() < 2) {
            return parents;
        }
        if (parents.get(0).length() == 1) {
            throw new IllegalStateException("Can't use Two Point Crossover on a chromosome with less than 2 genes.");
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

                    int pos1 = Utils.randInt(1, mom.length());
                    int pos2 = Utils.randInt(1, mom.length());

                    if (pos1 > pos2) {
                        int temp = pos1;
                        pos1 = pos2;
                        pos2 = temp;
                    }

                    for (int j = pos1; j < pos2; j++) {
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
