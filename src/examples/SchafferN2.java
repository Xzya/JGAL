package examples;

import static java.lang.Math.pow;
import static java.lang.Math.sin;

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
 * Schaffer Function 2. The global minimum is f(x) = 0, at 
 * x = (0,0).
 * More info at: http://www.sfu.ca/~ssurjano/schaffer2.html
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class SchafferN2 {

    public static void main(String[] args) {

        int n = 2; //number of genes
        double minrange = -100; //minimum value
        double maxrange = 100; //maximum value
        double targetScore = 0.0; //target score, the evolution will terminate when it is reached
        int precision = 2; //precision of the target score
        int generations = 10000; //number of generations
        int populationSize = 100; //population size
        double crossoverProbability = 0.8; //crossover probability
        double mutationProbability = 0.2; //mutation probability
        int elitismReplacements = 5; //number of elitism replacements

        GeneticAlgorithm ga = new GeneticAlgorithm()
        .setCrossover(Crossovers.singlePoint())
        .setSelector(Selectors.rouletteWheel())
        .setMutator(Mutators.realGaussian(minrange, maxrange))
        .setMinimax(Consts.MINIMIZE)
        .setInitializer(Initializers.real(minrange, maxrange, n))
        .setTerminationCriteria(TerminationCriterias.scoreCriteria(targetScore, precision))
        .setFitnessFunction(new FitnessFunction() {
            @Override
            public double evaluate(Chromosome chromosome) {
                ArrayList<Double> genes = chromosome.getGenes();

                double x = genes.get(0);
                double y = genes.get(1);

                double score = 0.5 + (pow(sin(x * x - y * y), 2) - 0.5) / 
                        pow(1 + 0.001 * (x * x + y * y), 2);

                return score;
            }
        })
        .setGenerations(generations)
        .setPopulationSize(populationSize)
        .setCrossoverProbability(crossoverProbability)
        .setMutationProbability(mutationProbability)
        .setElitismReplacements(elitismReplacements);

        ga.evolve();

        System.out.println(ga.getSolution());
        System.out.println(ga.getSolution().getRawFitnessValue());

    }

}
