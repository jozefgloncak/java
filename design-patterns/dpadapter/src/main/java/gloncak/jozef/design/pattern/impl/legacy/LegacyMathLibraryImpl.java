package gloncak.jozef.design.pattern.impl.legacy;

import gloncak.jozef.design.pattern.api.legacy.LegacyMathLibrary;

import java.util.ArrayList;
import java.util.List;

public class LegacyMathLibraryImpl implements LegacyMathLibrary {

    @Override
    public List<Integer> decomposeToPrimeNumbers(int number) {
        final ArrayList<Integer> primes = new ArrayList<>();
        int currentRemain = number;
        int currentDivisor = 2;
        while (currentRemain != 1) {
            if (currentRemain % currentDivisor == 0) {
                if (currentDivisor != number) { //no add number from input
                    primes.add(currentDivisor);
                }
                currentRemain = currentRemain / currentDivisor;
            } else {
                currentDivisor++;
            }
        }
        return primes;
    }

    @Override
    public int maximumCommonDivisor(int num1, int num2) {
        while (num1 != num2) {
            if (num1 > num2) {
                num1 = num1 - num2;
            } else {
                num2 = num2 - num1;
            }
        }
        return num1;
    }
}
