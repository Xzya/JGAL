package ro.jgal;

/**
 * String Mutator.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class MutatorString implements Mutator {

    char[] chars;

    /**
     * String Mutator. 
     * 
     * @param allowedCharacters - allowed characters
     */
    public MutatorString(String allowedCharacters) {
        this.chars = new char[allowedCharacters.length()];
        for (int i = 0; i < allowedCharacters.length(); i++) {
            chars[i] = allowedCharacters.charAt(i);
        }
    }

    /**
     * Mutates the chromosomes from a given population. Each
     * individual has a probability pm of mutating.
     * 
     * @param pop - the population which will be mutated
     * @param pm - the probability of each individual to mutate
     * @return - the mutated population
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public Population mutate(Population pop, double pm) {

        for (int i = 0; i < pop.length(); i++) {
            if (Math.random() < pm) {
                Chromosome<Character> mutant = pop.get(i);

                int pos = Utils.randInt(0, mutant.length());

                mutant.setGene(pos, chars[Utils.randInt(chars.length)]);
            }
        }

        return pop;
    }

}
