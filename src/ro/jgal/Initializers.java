package ro.jgal;

public class Initializers {

    /**
     * Real Initializer. Default parameters:
     * minrange = 0, 
     * maxrange = 100, 
     * length = 2
     * 
     * @return - initializer
     */
    public static Initializer real() {
        return new InitializerReal();
    }

    /**
     * Real Initializer.
     * 
     * @param minrange - minimum value (inclusive)
     * @param maxrange - maximum value (exclusive)
     * @param length - length of the chromosome
     * @return - initializer
     */
    public static Initializer real(double minrange, double maxrange, int length) {
        return new InitializerReal(minrange, maxrange, length);
    }

    /**
     * Integer Initializer. Default parameters:
     * minrange = 0, 
     * maxrange = 100, 
     * length = 2
     * 
     * @return - initializer
     */
    public static Initializer integer() {
        return new InitializerInteger();
    }

    /**
     * Integer Initializer.
     * 
     * @param minrange - minimum value (inclusive)
     * @param maxrange - maximum value (exclusive)
     * @param length - length of the chromosome
     * @return - initializer
     */
    public static Initializer integer(int minrange, int maxrange, int length) {
        return new InitializerInteger(minrange, maxrange, length);
    }

    /**
     * Integer Permutation Initializer.
     * 
     * @param minrange - minimum value (inclusive)
     * @param maxrange - maximum value (exclusive)
     * @return - initializer
     */
    public static Initializer integerPermutation(int minrange, int maxrange) {
        return new InitializerIntegerPermutation(minrange, maxrange);
    }

    /**
     * Binary String Initializer. Default parameters: 
     * length - 5
     */
    public static Initializer binaryString() {
        return new InitializerBinaryString();
    }

    /**
     * Binary String Initializer.
     * 
     * @param length - length of the chromosome
     */
    public static Initializer binaryString(int length) {
        return new InitializerBinaryString(length);
    }

}
