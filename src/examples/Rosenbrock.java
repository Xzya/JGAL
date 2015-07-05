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

/**
 * Rosenbrock Function. The global minimum is f(x) = 0, at 
 * x=(1,..,1).
 * More info at: http://www.sfu.ca/~ssurjano/rosen.html
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class Rosenbrock {

    public static void main(String[] args) {
        
        int n = 15; //number of genes
        double minrange = -2.048; //minimum value
        double maxrange = 2.048; //maximum value
        int generations = 10000; //number of generations
        int populationSize = 100; //population size
        double crossoverProbability = 0.9; //crossover probability
        double mutationProbability = 0.1; //mutation probability
        int elitismReplacements = 5; //number of elitism replacements
        
        GeneticAlgorithm ga = new GeneticAlgorithm()
        .setCrossover(Crossovers.singlePoint())
        .setSelector(Selectors.uniform())
        .setMutator(Mutators.realRange(minrange, maxrange))
        .setMinimax(Consts.MINIMIZE)
        .setInitializer(Initializers.real(minrange, maxrange, n))
        .setFitnessFunction(new FitnessFunction() {
            @Override
            public double evaluate(Chromosome chromosome) {
                ArrayList<Double> genes = chromosome.getGenes();
                
                double score = 0;
                for (int i = 1; i < genes.size(); i++) {
                    Double x = genes.get(i);
                    Double y = genes.get(i-1);
                    score += 100.0 * Math.pow(x - y*y, 2) + Math.pow(1 - y, 2);
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
