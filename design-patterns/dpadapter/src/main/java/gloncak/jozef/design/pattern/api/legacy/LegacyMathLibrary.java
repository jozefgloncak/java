package gloncak.jozef.design.pattern.api.legacy;

import java.util.List;

/**
 * Represents legacy library with methods which solve basic mathematical problems
 */
public interface LegacyMathLibrary {
    /**
     * Decompose {@param number} to list of prime numbers.
     *
     * @param number is number which should be decomposed to primes
     * @return list of primes. If {@param number} is prime then empty list.
     */
    List<Integer> decomposeToPrimeNumbers(int number);

    /**
     * Finds maximum common divisor of numbers {@param num1} and {@param num2}
     *
     * @param num1
     * @param num2
     * @return number which is maximum common divisor
     */
    int maximumCommonDivisor(int num1, int num2);
}
