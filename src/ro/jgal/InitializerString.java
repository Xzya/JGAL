package ro.jgal;

import java.util.ArrayList;

/**
 * String Initializer.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
@SuppressWarnings({"rawtypes"})
public class InitializerString implements Initializer {

    char[] chars;
    int length;

    public InitializerString() {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZ ", 5);
    }

    /**
     * String Initializer.
     * 
     * @param allowedCharacters - allowed characters
     * @param length - length of the string
     */
    InitializerString(String allowedCharacters, int length) {
        this.length = length;
        this.chars = new char[allowedCharacters.length()];
        for (int i = 0; i < allowedCharacters.length(); i++) {
            chars[i] = allowedCharacters.charAt(i);
        }
    }

    /**
     * Creates a new chromosome.
     * 
     * @return - new chromosome
     */
    @Override
    public Chromosome initialize() {
        ArrayList<Character> string = new ArrayList<Character>();
        while (string.size() < length) {
            char c = chars[Utils.randInt(chars.length)];
            string.add(c);
        }

        return new Chromosome<Character>(string);
    }

}
