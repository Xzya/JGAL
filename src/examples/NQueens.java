package examples;

import java.util.ArrayList;

import ro.jgal.Chromosome;
import ro.jgal.Consts;
import ro.jgal.Crossovers;
import ro.jgal.FitnessFunction;
import ro.jgal.GeneticAlgorithm;
import ro.jgal.Initializers;
import ro.jgal.Mutators;
import ro.jgal.Selectors;
import ro.jgal.TerminationCriterias;

/**
 * N-Queens Problem. 
 * 
 * The N-Queens is the problem of placing N chess queens on an
 * N x N chessboard so that no two queens threaten each other.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class NQueens {

    public static void printSolution(Chromosome solution) {
        ArrayList<Integer> genes = solution.getGenes();

        System.out.println();
        for (int i = 0; i < genes.size(); i++) {
            System.out.printf("%3d", (i+1));
        }
        System.out.println();

        for (int i = 0; i < genes.size(); i++) {
            int q = genes.get(i);
            System.out.printf("%d", (i+1));
            for (int j = 0; j < genes.size(); j++) {
                if (q == j) {
                    System.out.printf("%2s", "Q");
                } else {
                    System.out.printf("   ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int n = 8; //number of genes (queens)
        int minrange = 0; //minimum value
        int maxrange = n; //maximum value
        double targetScore = 0.0; //target score, the evolution will terminate when it is reached
        int precision = 2; //precision of the target score
        int generations = 10000; //number of generations
        int populationSize = 100; //population size
        double crossoverProbability = 1; //crossover probability
        double mutationProbability = 0.1; //mutation probability
        int elitismReplacements = 5; //number of elitism replacements

        GeneticAlgorithm ga = new GeneticAlgorithm()
        .setCrossover(Crossovers.CX())
        .setSelector(Selectors.uniform())
        .setMutator(Mutators.swap())
        .setMinimax(Consts.MINIMIZE)
        .setInitializer(Initializers.integerPermutation(minrange, maxrange))
        .setTerminationCriteria(TerminationCriterias.scoreCriteria(targetScore, precision))
        .setFitnessFunction(new FitnessFunction() {
            @Override
            public double evaluate(Chromosome chromosome) {
                ArrayList<Integer> genes = chromosome.getGenes();

                int n = genes.size();

                double score = 0;

                for (int i = 0; i < n-1; i++) {
                    for (int j = i + 1; j < n; j++) {
                        if (Math.abs(i-j) == Math.abs(genes.get(i) - genes.get(j))) {
                            score += 1;
                        }
                    }
                }

                return score;
            }
        })
        .setGenerations(generations)
        .setPopulationSize(populationSize)
        .setCrossoverProbability(crossoverProbability)
        .setMutationProbability(mutationProbability)
        .setElitismReplacements(elitismReplacements);

        ga.evolve(500);

        System.out.println(ga.getSolution());
        System.out.println(ga.getSolution().getRawFitnessValue());

        printSolution(ga.getSolution());
    }

}
