package examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ro.jgal.Chromosome;
import ro.jgal.Consts;
import ro.jgal.Crossovers;
import ro.jgal.FitnessFunction;
import ro.jgal.GeneticAlgorithm;
import ro.jgal.Initializers;
import ro.jgal.Mutators;
import ro.jgal.Selectors;

/**
 * Traveling Salesman Problem with 58 cities.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class TSP {

    /**
     * Reads the distance matrix from a file.
     * 
     * @return - distance matrix
     */
    private double[][] readMatrix() {
        try {
            FileReader fr = new FileReader(new File("").getAbsolutePath() + "/src/examples/58cities.txt");
            BufferedReader bf = new BufferedReader(fr);

            int n = Integer.parseInt(bf.readLine());
            double[][] matrix = new double[n][n];
            for (int i = 0; i < n-1; i++) {
                String line = bf.readLine();
                String[] row = line.split(",");
                int k = 0;
                for (int j = i; j < n; j++) {
                    if (i == j) {
                        matrix[i][j] = 0;
                    } else {
                        matrix[i][j] = Integer.parseInt(row[k++]);
                        matrix[j][i] = matrix[i][j];
                    }
                }
            }

            bf.close();
            fr.close();

            return matrix;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * Fitness function for TSP. 
     * 
     * @author Mihail Cristian Dumitru
     *
     */
    class FitnessFunctionTSP implements FitnessFunction {

        double[][] matrix;

        public FitnessFunctionTSP(double[][] matrix) {
            this.matrix = matrix;
        }

        @Override
        public double evaluate(Chromosome chromosome) {
            ArrayList<Integer> genes = chromosome.getGenes();

            double score = matrix[0][genes.get(0)];

            for (int i = 0; i < genes.size() - 1; i++) {
                int from = genes.get(i);
                int to = genes.get(i+1);

                score += matrix[from][to];
            }

            return score;
        }
    }

    public Chromosome<Integer> execute() {
        double[][] matrix = readMatrix();

        int n = matrix.length; //number of cities
        int minrange = 0; //minimum value
        int maxrange = n; //maximum value
        int generations = 10000; //number of generations
        int populationSize = 100; //population size
        double crossoverProbability = 1; //crossover probability
        double mutationProbability = 0.5; //mutation probability
        int elitismReplacements = 5; //number of elitism replacements

        GeneticAlgorithm ga = new GeneticAlgorithm()
        .setCrossover(Crossovers.OX())
        .setSelector(Selectors.uniform())
        .setMutator(Mutators.swap())
        .setMinimax(Consts.MINIMIZE)
        .setInitializator(Initializers.integerPermutation(minrange, maxrange))
        .setFitnessFunction(new FitnessFunctionTSP(matrix))
        .setGenerations(generations)
        .setPopulationSize(populationSize)
        .setCrossoverProbability(crossoverProbability)
        .setMutationProbability(mutationProbability)
        .setElitismReplacements(elitismReplacements);

        ga.evolve(500);

        return ga.getSolution();
    }

    public static void main(String[] args) {
        TSP tsp = new TSP();

        Chromosome<Integer> best = tsp.execute();

        System.out.println(best);
        System.out.println(best.getRawFitnessValue());
    }

}
