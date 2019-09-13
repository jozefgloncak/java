package gloncak.jozef.design.pattern.impl.adapter;

import gloncak.jozef.design.pattern.api.better.version.BetterMathLibrary;
import gloncak.jozef.design.pattern.api.legacy.LegacyMathLibrary;

import java.util.Arrays;
import java.util.List;

public class BetterMathLibraryAdapter implements LegacyMathLibrary {

    private BetterMathLibrary betterMathLibrary;

    public BetterMathLibraryAdapter(BetterMathLibrary betterMathLibrary) {
        this.betterMathLibrary = betterMathLibrary;
    }

    @Override
    public List<Integer> decomposeToPrimeNumbers(int number) {
        return betterMathLibrary.splitToPrimes(number);
    }

    @Override
    public int maximumCommonDivisor(int num1, int num2) {
        return betterMathLibrary.maxMutualDivisor(num1, num2);
    }
}
