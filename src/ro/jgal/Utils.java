package ro.jgal;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Utility class.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Utils {

    /**
     * Generates a random real number in the given range. 
     * 
     * @param minrange - minimum value
     * @param maxrange - maximum value
     * @return - random number
     */
    public static double randRealUniform(double minrange, double maxrange) {
        return minrange + (Math.random() * (maxrange - minrange));
    }

    /**
     * Generates a random integer in the range [0,n).
     * 
     * @param n - maximum value (exclusive)
     * @return - random integer
     */
    public static int randInt(int n) {
        return (int) randInt(0, n);
    }

    /**
     * Generates a random integer in the given range.
     * 
     * @param minrange - minumum value (inclusive)
     * @param maxrange - maximum value (exclusive)
     * @return - random integer
     */
    public static int randInt(int minrange, int maxrange) {
        return (int) randRealUniform(minrange, maxrange);
    }

    /**
     * Calculates the edges for EdgeCrossover.
     * 
     * @param mom - first parent
     * @param dad - second parent
     * @return - edges hashmap
     */
    public static HashMap<String, HashMap<Object,ArrayList<Object>>> getEdgesComposite(Chromosome mom, Chromosome dad) {
        HashMap<Object, ArrayList<Object>> mom_edges = getEdges(mom);
        HashMap<Object, ArrayList<Object>> dad_edges = getEdges(dad);

        HashMap<String, HashMap<Object, ArrayList<Object>>> map = new HashMap<String, HashMap<Object,ArrayList<Object>>>();
        map.put("momEdges", mom_edges);
        map.put("dadEdges", dad_edges);
        map.put("edges", mergeEdges(mom_edges, dad_edges));

        return map;
    }

    /**
     * Calculates the edges of a given individual.
     * 
     * @param ind - chromosome for which to calculate the edges
     * @return - edges hashmap
     */
    public static HashMap<Object, ArrayList<Object>> getEdges(Chromosome ind) {
        HashMap<Object, ArrayList<Object>> edg = new HashMap<Object, ArrayList<Object>>();
        ArrayList<Object> genes = ind.getGenes();

        for (int i = 0; i < genes.size(); i++) {
            Object a = genes.get(i);
            Object b = null;
            if (i == 0) {
                b = genes.get(genes.size()-1);
            } else {
                b = genes.get(i - 1);
            }

            if (!edg.containsKey(a)) {
                edg.put(a, new ArrayList<Object>());
            } 
            edg.get(a).add(b);


            if (!edg.containsKey(b)) {
                edg.put(b, new ArrayList<Object>());
            } 
            edg.get(b).add(a);

        }
        return edg;
    }

    /**
     * Merges two edge hashmaps.
     * 
     * @param eda - first map
     * @param edb - second map
     * @return - merged edges hashmap
     */
    public static HashMap<Object, ArrayList<Object>> mergeEdges(HashMap<Object, ArrayList<Object>> eda, HashMap<Object, ArrayList<Object>> edb) {
        HashMap<Object, ArrayList<Object>> edges = new HashMap<Object, ArrayList<Object>>();

        for (Object key : eda.keySet()) {
            edges.put(key, new ArrayList<Object>());
            for (Object g : eda.get(key)) {
                if (!edges.get(key).contains(g)) {
                    edges.get(key).add(g);
                }
            }
        }

        for (Object key : edb.keySet()) {
            for (Object g : edb.get(key)) {
                if (!edges.get(key).contains(g)) {
                    edges.get(key).add(g);
                }
            }
        }

        return edges;
    }

}
