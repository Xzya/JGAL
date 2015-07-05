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
 * A simple problem demonstrating the real initializer and the
 * real gaussian mutation operator. The fitness function consists
 * in maximizing the function f(xi)= sum of 0.1 for all xi < 0, 
 * xi=[-5,5]. The global maximum is 5.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class RealGauss {

    public static void main(String[] args) {

        int n = 50; //number of genes
        double minrange = -5; //minimum value
        double maxrange = 5; //maximum value
        double targetScore = n / 10; //target score, the evolution will terminate when it is reached
        int precision = 2; //precision of the target score
        int generations = 10000; //number of generations
        int populationSize = 100; //population size
        double crossoverProbability = 1; //crossover probability
        double mutationProbability = 0.05; //mutation probability
        int elitismReplacements = 5; //number of elitism replacements

        GeneticAlgorithm ga = new GeneticAlgorithm()
        .setCrossover(Crossovers.singlePoint())
        .setSelector(Selectors.uniform())
        .setMutator(Mutators.realGaussian(minrange, maxrange))
        .setInitializer(Initializers.real(minrange, maxrange, n))
        .setMinimax(Consts.MAXIMIZE)
        .setTerminationCriteria(TerminationCriterias.scoreCriteria(targetScore, precision))
        .setFitnessFunction(new FitnessFunction() {
            @Override
            public double evaluate(Chromosome chromosome) {
                ArrayList<Double> genes = chromosome.getGenes();

                double score = 0;

                for (int i = 0; i < genes.size(); i++) {
                    if (genes.get(i) < 0) {
                        score += 0.1;
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

    }

}
