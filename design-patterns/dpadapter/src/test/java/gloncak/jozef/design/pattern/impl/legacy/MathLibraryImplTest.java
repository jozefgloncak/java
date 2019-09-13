package gloncak.jozef.design.pattern.impl.legacy;

import gloncak.jozef.design.pattern.api.legacy.LegacyMathLibrary;
import gloncak.jozef.design.pattern.impl.adapter.BetterMathLibraryAdapter;
import gloncak.jozef.design.pattern.impl.better.version.BetterMathLibraryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class MathLibraryImplTest {

    @Parameterized.Parameters(name="{index} - {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new LegacyMathLibraryImpl(), "legacy"}
                ,{new BetterMathLibraryAdapter(new BetterMathLibraryImpl()),"better via adapter"}
        });
    }

    public LegacyMathLibrary mathLibrary;

    public MathLibraryImplTest(LegacyMathLibrary mathLibrary, String typeOfMathLibrary) {
        this.mathLibrary = mathLibrary;
    }


    @Test
    public void decomposeToPrimeNumbers() {
        checkNumberPrimeDecomposition(mathLibrary, 1260, new Integer[]{2, 2, 3, 3, 5, 7});
        checkNumberPrimeDecomposition(mathLibrary, 3276, new Integer[]{2, 2, 3, 3, 7, 13});
        checkNumberPrimeDecomposition(mathLibrary, 18252, new Integer[]{2, 2, 3, 3, 3, 13, 13});
        checkNumberPrimeDecomposition(mathLibrary, 13, new Integer[]{});
        checkNumberPrimeDecomposition(mathLibrary, 1, new Integer[]{});
        checkNumberPrimeDecomposition(mathLibrary, 2, new Integer[]{});
        checkNumberPrimeDecomposition(mathLibrary, 4, new Integer[]{2, 2});
    }

    private void checkNumberPrimeDecomposition(LegacyMathLibrary legacyMathLibrary, int numberToDecompose,
                                               Integer[] expectedDecompose) {
        final List<Integer> primes = legacyMathLibrary.decomposeToPrimeNumbers(numberToDecompose);
        List<Integer> expected = Arrays.asList(expectedDecompose);
        Assert.assertEquals(expected, primes);
    }

    @Test
    public void maximumCommonDivisor() {
        Assert.assertEquals(252, mathLibrary.maximumCommonDivisor(1260, 3276));
        Assert.assertEquals(50, mathLibrary.maximumCommonDivisor(800, 250));
        Assert.assertEquals(210, mathLibrary.maximumCommonDivisor(1260, 19110));
        Assert.assertEquals(12, mathLibrary.maximumCommonDivisor(12, 36));
        Assert.assertEquals(1, mathLibrary.maximumCommonDivisor(83, 101));
        Assert.assertEquals(1, mathLibrary.maximumCommonDivisor(1, 3));
    }
}