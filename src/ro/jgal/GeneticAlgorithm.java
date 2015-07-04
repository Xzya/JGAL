package ro.jgal;

import java.util.ArrayList;

/**
 * The genetic algorithm.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes"})
public class GeneticAlgorithm {

    ArrayList<Statistics> statistics; //holds statistics object for each generation

    Crossover crossover;
    Mutator mutator;
    Selector selector;
    Initializer initializer;
    FitnessFunction fitnessFunction;
    Population population;
    TerminationCriteria terminationCriteria; //termination criteria

    double p_crossover; //crossover probability
    double p_mutation; //mutation probability
    int popSize; //population size
    int generations; //number of iterations
    int minimax; //minimize/maximize
    boolean elitism = true; //perform elitism ?
    int nElitism = Consts.NELITISM; //number of elitism replacements

    public GeneticAlgorithm() {
        this(100, 1.0, 0.02, 200);
    }

    /**
     * 
     * @param pop_size - Population size
     * @param p_cx - Crossover probability
     * @param p_mut - Mutation probability
     * @param ngen - Generations number
     */
    public GeneticAlgorithm(int pop_size, double p_cx, double p_mut, int ngen) {
        this.popSize = pop_size;
        this.p_crossover = p_cx;
        this.p_mutation = p_mut;
        this.generations = ngen;
        this.minimax = 0;
        this.statistics = new ArrayList<Statistics>();
    }

    /**
     * Evolves the population. Default parameters: 
     * statsFrequency = 100
     */
    public void evolve() {
        evolve(100);
    }

    /**
     * Evolves the population. 
     * 
     * @param statsFrequency - prints the statistics after the given number of generations
     */
    public void evolve(int statsFrequency) {
        //check to make sure all operators are set
        check();

        //initialize population
        initialize();

        //calculate the fitness of the population
        calculateFitness(population);

        for (int i = 0; i < generations; i++) {

            //check if a termination criteria exists
            if (terminationCriteria != null) {
                //check if it has been reached
                if (terminationCriteria.terminate(population)) {
                    System.out.println("Termination criteria reached after " + i + " generations.");
                    return;
                }
            }

            //print stats
            if (i % statsFrequency == 0) {
                System.out.println(statistics.get(statistics.size()-1).toString());
            }

            Population newPopulation = null;

            //select parents
            newPopulation = selector.select(population);

            //perform crossover
            newPopulation = crossover.crossover(newPopulation, p_crossover);

            //perform mutation
            newPopulation = mutator.mutate(newPopulation, p_mutation);

            //perform elitism
            if (elitism) {
                elitism(newPopulation);
            }

            //assign the new population to the current population
            population = newPopulation;


            //calculate the fitness of the new population
            calculateFitness(population);

        }

    }

    /**
     * Initializes the population.
     */
    public void initialize() {
        population = new Population();

        for (int i = 0; i < popSize; i++) {
            population.add(initializer.initialize());
        }

        population.sort();
    }

    /**
     * Calculates the fitness of the given population.
     * 
     * @param population
     */
    public void calculateFitness(Population population) {
        if (population.length() < 1) {
            return;
        }

        double sum = 0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        Chromosome bestChromosome = null;
        Chromosome worstChromosome = null;
        double bestValue = Double.NEGATIVE_INFINITY;
        double worstValue = Double.POSITIVE_INFINITY;

        for (Chromosome c : population) {
            double val = fitnessFunction.evaluate(c);
            double rawVal = val;

            if (minimax == Consts.MINIMIZE) {
                val = -1 * val;
            }

            c.setFitnessValue(val);
            c.setRawFitnessValue(rawVal);

            sum += rawVal;

            if (val > bestValue) {
                bestChromosome = c;
                bestValue = val;
            }
            if (val < worstValue) {
                worstChromosome = c;
                worstValue = val;
            }

            if (rawVal > max) {
                max = rawVal;
            }
            if (rawVal < min) {
                min = rawVal;
            }
        }

        Statistics stat = new Statistics();
        stat.setSum(sum);
        stat.setAvg(sum / popSize);
        stat.setMin(min);
        stat.setMax(max);
        stat.setGen(statistics.size());
        statistics.add(stat);

        population.setBestIndividual(bestChromosome);
        population.setBestValue(bestValue);
        population.setWorstIndividual(worstChromosome);
        population.setWorstValue(worstValue);
    }

    /**
     * Performs elitism. 
     * 
     * @param pop
     */
    private void elitism(Population pop) {
        population.sort();
        pop.sort();
        for (int i = 0; i < nElitism; i++) {
            population.get(i).setFitnessValue(
                    fitnessFunction.evaluate(population.get(i))
                    );

            if (population.get(i).getFitnessValue() > pop.get(i).getFitnessValue()) {
                pop.set(pop.length() - i - 1, population.get(i));
            }
        }
    }

    public Chromosome getSolution() {
        return population.getBestIndividual();
    }

    void check() {
        if (crossover == null) {
            throw new IllegalStateException("Crossover operator not set.");
        }
        if (mutator == null) {
            throw new IllegalStateException("Mutation operator not set.");
        }
        if (selector == null) {
            throw new IllegalStateException("Selection operator not set.");
        }
        if (initializer == null) {
            throw new IllegalStateException("Initializer not set.");
        }
        if (fitnessFunction == null) {
            throw new IllegalStateException("Fitness function not set.");
        }
        if (minimax == 0) {
            throw new IllegalStateException("Minimax condition not set.");
        }
    }

    public GeneticAlgorithm setCrossover(Crossover crossover) {
        this.crossover = crossover;
        return this;
    }

    public Crossover getCrossover() {
        return crossover;
    }

    public GeneticAlgorithm setCrossoverProbability(double p) {
        this.p_crossover = p;
        return this;
    }

    public double getCrossoverProbability() {
        return p_crossover;
    }

    public GeneticAlgorithm setMutator(Mutator mutator) {
        this.mutator = mutator;
        return this;
    }

    public Mutator getMutator() {
        return mutator;
    }

    public GeneticAlgorithm setMutationProbability(double p) {
        this.p_mutation = p;
        return this;
    }

    public double getMutationProbability() {
        return p_mutation;
    }

    public GeneticAlgorithm setSelector(Selector selector) {
        this.selector = selector;
        return this;
    }

    public Selector getSelector() {
        return selector;
    }

    public GeneticAlgorithm setInitializator(Initializer initializer) {
        this.initializer = initializer;
        return this;
    }

    public Initializer getInitializer() {
        return initializer;
    }

    public GeneticAlgorithm setFitnessFunction(FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        return this;
    }

    public FitnessFunction getFitnessFunction() {
        return fitnessFunction;
    }

    public GeneticAlgorithm setPopulationSize(int pop_size) {
        this.popSize = pop_size;
        return this;
    }

    public int getPopulationSize() {
        return popSize;
    }

    public GeneticAlgorithm setGenerations(int generations) {
        this.generations = generations;
        return this;
    }

    public int getGenerations() {
        return generations;
    }

    public GeneticAlgorithm setPopulation(Population population) {
        this.population = population;
        return this;
    }

    public Population getPopulation() {
        return population;
    }

    public GeneticAlgorithm setMinimax(int minimax) {
        this.minimax = minimax;
        return this;
    }

    public int getMinimax() {
        return minimax;
    }

    public GeneticAlgorithm setElitism(boolean elitism) {
        this.elitism = elitism;
        return this;
    }

    public boolean getElitism() {
        return elitism;
    }

    public GeneticAlgorithm setElitismReplacements(int n) {
        this.nElitism = n;
        return this;
    }

    public int getElitismReplacements() {
        return nElitism;
    }

    public GeneticAlgorithm setTerminationCriteria(TerminationCriteria criteria) {
        this.terminationCriteria = criteria;
        return this;
    }

    public TerminationCriteria getTerminationCriteria() {
        return terminationCriteria;
    }

}
