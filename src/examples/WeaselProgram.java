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
 * Richard Dawkins's Weasel Program. 
 * More info at: https://en.wikipedia.org/wiki/Weasel_program
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class WeaselProgram {

    public static void main(String[] args) {

        int n = 28; //number of genes
        int generations = 10000; //number of generations
        int populationSize = 100; //population size
        double targetScore = n; //target score, the evolution will terminate when it is reached
        int precision = 2; //precision of the target score
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        double crossoverProbability = 1; //crossover probability
        double mutationProbability = 0.05; //mutation probability
        int elitismReplacements = 5; //number of elitism replacements

        GeneticAlgorithm ga = new GeneticAlgorithm()
        .setCrossover(Crossovers.singlePoint())
        .setSelector(Selectors.uniform())
        .setMutator(Mutators.string(allowedCharacters))
        .setMinimax(Consts.MAXIMIZE)
        .setInitializer(Initializers.string(allowedCharacters, n))
        .setTerminationCriteria(TerminationCriterias.scoreCriteria(targetScore, precision))
        .setFitnessFunction(new FitnessFunction() {

            String targetString = "METHINKS IT IS LIKE A WEASEL";

            @Override
            public double evaluate(Chromosome chromosome) {
                ArrayList<Character> genes = chromosome.getGenes();

                double score = 0;

                for (int i = 0; i < genes.size(); i++) {
                    if (genes.get(i).equals(targetString.charAt(i))) {
                        score += 1;
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
