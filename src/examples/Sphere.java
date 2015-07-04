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
 * Sphere Function. The global minimum is f(x) = 0, at 
 * x = (0,..,0).
 * More info at: http://www.sfu.ca/~ssurjano/spheref.html
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class Sphere {

    public static void main(String[] args) {
        
        int n = 5; //number of genes
        double minrange = -100; //minimum value
        double maxrange = 100; //maximum value
        double targetScore = 0.0; //target score, the evolution will terminate when it is reached
        int precision = 2; //precision of the target score
        int generations = 10000; //number of generations
        int populationSize = 100; //population size
        double crossoverProbability = 0.8; //crossover probability
        double mutationProbability = 0.05; //mutation probability
        int elitismReplacements = 5; //number of elitism replacements
        
        GeneticAlgorithm ga = new GeneticAlgorithm()
        .setCrossover(Crossovers.singlePoint())
        .setSelector(Selectors.uniform())
        .setMutator(Mutators.realRange(minrange, maxrange))
        .setMinimax(Consts.MINIMIZE)
        .setInitializator(Initializers.real(minrange, maxrange, n))
        .setTerminationCriteria(TerminationCriterias.scoreCriteria(targetScore, precision))
        .setFitnessFunction(new FitnessFunction() {
            @Override
            public double evaluate(Chromosome chromosome) {
                ArrayList<Double> genes = (ArrayList<Double>)chromosome.getGenes();
                
                double score = 0;
                for (int i = 0; i < genes.size(); i++) {
                    score += genes.get(i) * genes.get(i);
                }
                
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
