package ro.jgal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import sun.security.util.Length;

/**
 * Chromosome population. Contains all the chromosomes in a generation and
 * the best/worst chromosomes.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public class Population implements Length, Iterable<Chromosome>, Cloneable {

    ArrayList<Chromosome> population;
    Chromosome bestIndividual;
    Chromosome worstIndividual;
    double bestValue;
    double worstValue;
    
    int minimax;

    /**
     * Chromosome population.
     */
    public Population() {
        population = new ArrayList<Chromosome>();
        minimax = Consts.MAXIMIZE;
    }

    /**
     * Sets the objective of the problem.
     * 
     * @param minimax - the objective (Consts.MINIMIZE/Consts.MAXIMIZE)
     */
    public void setMinimax(int minimax) {
        this.minimax = minimax;
    }
    
    public int getMinimax() {
        return minimax;
    }
    
    public void add(Chromosome chrom) {
        population.add(chrom);
    }

    public Chromosome get(int index) {
        return population.get(index);
    }

    public void set(int index, Chromosome that) {
        population.set(index, that);
    }

    public void setPopulation(Chromosome[] population) {
        this.population = new ArrayList<Chromosome>();
        for (int i = 0; i < population.length; i++) {
            this.population.add(population[i]);
        }
    }

    public void setPopulation(ArrayList<Chromosome> population) {
        this.population = population;
    }

    public ArrayList<Chromosome> getPopulation() {
        return population;
    }

    public void setBestIndividual(Chromosome best) {
        this.bestIndividual = best;
    }

    public Chromosome getBestIndividual() {
        return bestIndividual;
    }

    public void setBestValue(double bestValue) {
        this.bestValue = bestValue;
    }

    public double getBestValue() {
        return bestValue;
    }
    
    public void setWorstIndividual(Chromosome worst) {
        this.worstIndividual = worst;
    }

    public Chromosome getWorstIndividual() {
        return worstIndividual;
    }

    public void setWorstValue(double worstValue) {
        this.worstValue = worstValue;
    }

    public double getWorstValue() {
        return worstValue;
    }
    
    public void sort() {
        Collections.sort(population, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                if (o1.getFitnessValue() > o2.getFitnessValue()) {
                    return minimax == Consts.MAXIMIZE ? -1 : 1;
                } else if (o1.getFitnessValue() < o2.getFitnessValue()) {
                    return minimax == Consts.MAXIMIZE ? 1 : -1;
                } else {
                    return 0;
                }
            };
        });
    }

    @Override
    public int length() {
        if (population != null) {
            return population.size();
        }
        return 0;
    }

    @Override
    public Iterator<Chromosome> iterator() {
        return population.iterator();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Population clone = new Population();
        try {
            for (Chromosome c : population) {
                clone.add((Chromosome) c.clone());
            }
            if (bestIndividual != null) {
                clone.setBestIndividual((Chromosome) bestIndividual.clone());
            }
            clone.setBestValue(bestValue);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        clone.setMinimax(minimax);

        return clone;
    }
    
}
