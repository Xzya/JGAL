package ro.jgal;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Edge Crossover. Works well for order optimizations.
 * Use it for integer permutations only.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class CrossoverEdge implements Crossover {

    /**
     * Recombines the given parents population. Each
     * individual has a probability pc of recombining.
     * 
     * @param parents - the parents population
     * @param pc - recombination probability
     * @return - children population
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
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
                    Chromosome mom = parents.get(i);
                    Chromosome dad = parents.get(i+1);

                    Chromosome sister = children.get(i);
                    Chromosome brother = children.get(i+1);

                    HashMap<String, HashMap<Object,ArrayList<Object>>> map = Utils.getEdgesComposite(mom, dad);
                    HashMap<Object, ArrayList<Object>> mom_edges = map.get("momEdges");
                    HashMap<Object, ArrayList<Object>> dad_edges = map.get("dadEdges");
                    HashMap<Object, ArrayList<Object>> edges = map.get("edges");

                    ArrayList<Object>[] a_children = new ArrayList[] {
                            new ArrayList<Object>(),
                            new ArrayList<Object>()
                    };
                    ArrayList<Object>[] a_parents = new ArrayList[] {
                            mom.getGenes(), 
                            dad.getGenes()
                    };

                    for (int j = 0; j < 2; j++) {
                        Object current = null;
                        for (int k = 0; k < len; k++) {
                            if (current == null) {
                                current = a_parents[j].get(Utils.randInt(a_parents[j].size()));
                            }
                            a_children[j].add(current);
                            a_parents[j].remove(current);

                            ArrayList<Object> d = new ArrayList<Object>();
                            if (edges.containsKey(current)) {
                                for (Object g : edges.get(current)) {
                                    if (a_parents[j].contains(g)) {
                                        d.add(g);
                                    }
                                }
                            }

                            if (!d.isEmpty()) {
                                current = d.get(Utils.randInt(d.size()));
                            } else {
                                ArrayList<Object> s = new ArrayList<Object>();
                                if (mom_edges.containsKey(current)) {
                                    for (Object g : mom_edges.get(current)) {
                                        if (a_parents[j].contains(g)) {
                                            s.add(g);
                                        }
                                    }
                                }
                                if (dad_edges.containsKey(current)) {
                                    for (Object g : dad_edges.get(current)) {
                                        if (a_parents[j].contains(g)) {
                                            s.add(g);
                                        }
                                    }
                                }
                                if (!s.isEmpty()) {
                                    current = s.get(Utils.randInt(s.size()));
                                } else {
                                    current = null;
                                }
                            }
                        }
                    }

                    sister.setGenes(a_children[0]);
                    brother.setGenes(a_children[1]);

                }
            }

            return children;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
