package ro.jgal;

import java.util.ArrayList;

/**
 * Order Crossover. Works well for order optimizations.
 * Use it for integer permutations only.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CrossoverOX implements Crossover {

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
            int len = children.get(0).length();

            for (int i = 0; i < parents.length(); i+=2) {
                if (Math.random() < pc) {
                    if (i+1 == parents.length()) {
                        break;
                    }
                    Chromosome<Object> mom = parents.get(i);
                    Chromosome<Object> dad = parents.get(i+1);

                    Chromosome<Object> sister = children.get(i);
                    Chromosome<Object> brother = children.get(i+1);

                    int c1 = Utils.randInt(1, mom.length());
                    int c2 = Utils.randInt(1, mom.length());

                    while (c1 == c2) {
                        c2 = Utils.randInt(1, mom.length());
                    }

                    if (c1 > c2) {
                        int temp = c1;
                        c1 = c2;
                        c2 = temp;
                    }

                    Chromosome[] a_parents = new Chromosome[] {mom, dad};
                    Chromosome[] a_children = new Chromosome[] {sister, brother};

                    for (int k = 0; k < 2; k++) {
                        ArrayList<Object> P1 = new ArrayList<Object>();
                        ArrayList<Object> parent = new ArrayList<Object>(a_parents[k].getGenes());
                        ArrayList<Object> reversedParent = new ArrayList<Object>();
                        reversedParent.addAll(parent.subList(c2, parent.size()));
                        reversedParent.addAll(parent.subList(0, c2));

                        boolean flag;
                        for (Object g : reversedParent) {
                            flag = false;
                            for (int j = c1; j < c2; j++) {
                                if (g.equals(a_parents[(k + 1) % 2].getGene(j))) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                P1.add(g);
                            }
                        }

                        ArrayList<Object> P1G = new ArrayList<Object>();
                        P1G.addAll(P1.subList(len - c2, P1.size()));
                        for (int j = c1; j < c2; j++) {
                            P1G.add(a_parents[(k + 1) % 2].getGene(j));
                        }
                        P1G.addAll(P1.subList(0, len - c2));

                        ArrayList<Object> genes = new ArrayList<Object>();
                        for (int j = 0; j < len; j++) {
                            genes.add(P1G.get(j));
                        }

                        a_children[k].setGenes(genes);
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
