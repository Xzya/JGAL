package ro.jgal;

import java.util.ArrayList;

import sun.security.util.Length;

/**
 * Chromosome.
 * 
 * @author Mihail Cristian Dumitru
 *
 * @param <T>
 */
@SuppressWarnings({"unchecked"})
public class Chromosome <T> implements Length, Cloneable, Comparable<Chromosome<T>> {

    ArrayList<T> genes; //the genes
    double fitnessValue; //value used for ranking solutions (if the objective is minimization, it will be equal to -rawFitnessValue)
    double rawFitnessValue; //the result from the fitness function

    /**
     * Empty chromosome.
     */
    public Chromosome() {
        genes = new ArrayList<T>();
        fitnessValue = 0;
    }

    /**
     * Chromosome. 
     * 
     * @param genes - the genes of the chromosome
     */
    public Chromosome(ArrayList<T> genes) {
        setGenes(genes);
    }

    /**
     * Chromosome. 
     * 
     * @param genes - the genes of the chromosome
     */
    public Chromosome(T[] genes) {
        setGenes(genes);
    }

    /**
     * Sets the genes of the chromosome.
     * 
     * @param genes - the genes
     */
    public void setGenes(T[] genes) {
        this.genes = new ArrayList<T>();
        for (int i = 0; i < genes.length; i++) {
            this.genes.add(genes[i]);
        }
        fitnessValue = 0;
    }

    /**
     * Sets the genes of the chromosome.
     * 
     * @param genes - the genes
     */
    public void setGenes(ArrayList<T> genes) {
        this.genes = genes;
    }

    /**
     * Sets the gene at the given index.
     * 
     * @param index - index of the gene
     * @param gene - the gene
     */
    public void setGene(int index, T gene) {
        if (index < 0 || index > length() - 1) {
            throw new IndexOutOfBoundsException();
        }
        genes.set(index, gene);
    }

    /**
     * Getter for the chromosome's genes.
     * 
     * @return - the genes of the chromosome
     */
    public ArrayList<T> getGenes() {
        return genes;
    }

    /**
     * 
     * @param index - index of the gene to be returned
     * @return - the gene at the given index
     */
    public T getGene(int index) {
        return genes.get(index);
    }

    /**
     * Setter for the fitness value.
     * 
     * @param fitnessValue - fitness value
     */
    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    /**
     * Gets the fitness value. This is equal to the raw
     * fitness value if the objective is maximization, and
     * negative raw fitness value if the objective is
     * minimization. It's used for ranking the solutions.
     * 
     * @return - fitness value
     */
    public double getFitnessValue() {
        return fitnessValue;
    }

    /**
     * Setter for the raw fitness value.
     * 
     * @param rawFitnessValue - raw fitness value
     */
    public void setRawFitnessValue(double rawFitnessValue) {
        this.rawFitnessValue = rawFitnessValue;
    }

    /**
     * Gets the raw fitness value. This is equal to 
     * the value returned by the fitness function.
     * 
     * @return - raw fitness value
     */
    public double getRawFitnessValue() {
        return rawFitnessValue;
    }

    @Override
    public String toString() {
        return genes.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){ 
            return false;
        }
        if (this == obj) {
            return true;
        }
        Chromosome<T> that = (Chromosome<T>) obj;
        return genes.equals(that.getGenes());
    }

    @Override
    public int length() {
        if (genes != null) {
            return genes.size();
        }
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ArrayList<T> genesClone = new ArrayList<T>();
        for (T g : genes) {
            genesClone.add(g);
        }
        Chromosome<T> c = new Chromosome<T>(genesClone);
        c.setFitnessValue(fitnessValue);
        return c;
    }

    @Override
    public int hashCode() {
        return genes.hashCode();
    }

    @Override
    public int compareTo(Chromosome<T> o) {
        if (this.fitnessValue > o.getFitnessValue()) {
            return -1;
        } else if (this.fitnessValue < o.getFitnessValue()) {
            return 1;
        }
        return 0;
    }

}
