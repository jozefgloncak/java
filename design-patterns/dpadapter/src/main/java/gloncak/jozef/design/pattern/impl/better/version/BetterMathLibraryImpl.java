package gloncak.jozef.design.pattern.impl.better.version;

import gloncak.jozef.design.pattern.api.better.version.BetterMathLibrary;

import java.util.ArrayList;
import java.util.List;

public class BetterMathLibraryImpl implements BetterMathLibrary {
    @Override
    public List<Integer> splitToPrimes(int number) {
        final List<Integer> primes = new ArrayList<>();
        int currentRemain = number;
        int currentDivisor = 2;
        while (currentDivisor <= Math.sqrt(currentRemain)) {
            if (currentRemain % currentDivisor == 0) {
                primes.add(currentDivisor);
                currentRemain = currentRemain / currentDivisor;
            } else {
                currentDivisor++;
            }
        }
        if (currentRemain != number) {
            primes.add(currentRemain);
        }
        return primes;
    }

    @Override
    public int maxMutualDivisor(int n1, int n2) {
        int temp;
        while (n2 != 0) {
            temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        return n1;
    }
}
