package gloncak.jozef.design.pattern.api.better.version;

import java.util.List;

public interface BetterMathLibrary {

    /**
     * Splits {@param number} to list of primes
     *
     * @param number is number which should be splitted
     *
     * @return array of primes. By multiplying of all array elements you will get {@param number}
     */
    List<Integer> splitToPrimes(int number);

    /**
     * Finds max mutual divisor of two numbers
     *
     * @param n1
     * @param n2
     * @return max mutual divisor of specified numbers
     */
    int maxMutualDivisor(int n1, int n2);
}
