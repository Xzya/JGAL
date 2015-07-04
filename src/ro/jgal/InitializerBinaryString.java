package ro.jgal;

import java.util.ArrayList;

/**
 * Binary String Initializer.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings("rawtypes")
public class InitializerBinaryString implements Initializer {

    int length;

    /**
     * Binary String Initializer. Default parameters: 
     * length - 5
     */
    public InitializerBinaryString() {
        this(Consts.BINARY_STRING_LENGTH);
    }

    /**
     * Binary String Initializer.
     * 
     * @param length - length of the chromosome
     */
    public InitializerBinaryString(int length) {
        this.length = length;
    }

    /**
     * Creates a new chromosome.
     * 
     * @return - new chromosome
     */
    @Override
    public Chromosome initialize() {
        ArrayList<Integer> genes = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            int  bit = Utils.randInt(2);
            genes.add(bit);
        }

        Chromosome<Integer> c = new Chromosome<Integer>(genes);
        return c;
    }

}
