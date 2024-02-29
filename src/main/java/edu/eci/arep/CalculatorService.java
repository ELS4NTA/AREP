package edu.eci.arep;

/**
 * CalculatorService class to calculate the sin, cos, palindrome and vector magnitude.
 * 
 * @author Daniel Santanilla
 */
public class CalculatorService {

    /**
     * Private constructor to avoid the creation of instances.
     */
    private CalculatorService() {
    }

    /**
     * Calculate the sin of a value.
     * @param value The value to calculate the sin.
     * @return The sin of the value.
     */
    public static double calculateSin(double value) {
        return Math.sin(value);
    }

    /**
     * Calculate the cos of a value.
     * @param value The value to calculate the cos.
     * @return The cos of the value.
     */
    public static double calculateCos(double value) {
        return Math.cos(value);
    }

    /**
     * Check if a string is a palindrome.
     * @param value The string to check if it is a palindrome.
     * @return True if the string is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String value) {
        String clean = value.replaceAll("\\s+", "").toLowerCase();
        int length = clean.length();
        int forward = 0;
        int backward = length - 1;
        while (backward > forward) {
            char forwardChar = clean.charAt(forward++);
            char backwardChar = clean.charAt(backward--);
            if (forwardChar != backwardChar)
                return false;
        }
        return true;
    }

    /**
     * Calculate the magnitude of a vector.
     * @param vector The vector to calculate the magnitude.
     * @return The magnitude of the vector.
     */
    public static double vectorMagnitude(double[] vector) {
        double magnitude = 0;
        for (int i = 0; i < vector.length; i++) {
            magnitude += Math.pow(vector[i], 2);
        }
        return Math.sqrt(magnitude);
    }

}
