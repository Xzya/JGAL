package examples;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

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
 * Cross-In-Tray Function. Global minimum is f(x) = -2.06261 for
 * x=(1.3491,-1.3491), (1.3491,1.3491), (-1.3491,1.3491).
 * More info at: http://www.sfu.ca/~ssurjano/crossit.html
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class CrossInTray {

    public static void main(String[] args) {

        int n = 2; //number of genes
        double minrange = -10; //minimum value
        double maxrange = 10; //maximum value
        double targetScore = -2.06261; //target score, the evolution will terminate when it is reached
        int precision = 5; //precision of the target score
        int generations = 10000; //number of generations
        int populationSize = 100; //population size
        double crossoverProbability = 0.8; //crossover probability
        double mutationProbability = 0.05; //mutation probability
        int elitismReplacements = 5; //number of elitism replacements

        GeneticAlgorithm ga = new GeneticAlgorithm()
        .setCrossover(Crossovers.singlePoint())
        .setSelector(Selectors.uniform())
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

                double score = -0.0001 * pow(abs(sin(x) * sin(y) * 
                        exp(abs(100 - sqrt(x*x + y*y) / PI))) + 1, 0.1);

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
