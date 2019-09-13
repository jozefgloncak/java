package gloncak.jozef.design.pattern;

import gloncak.jozef.design.pattern.api.legacy.LegacyMathLibrary;
import gloncak.jozef.design.pattern.impl.adapter.BetterMathLibraryAdapter;
import gloncak.jozef.design.pattern.impl.better.version.BetterMathLibraryImpl;
import gloncak.jozef.design.pattern.impl.legacy.LegacyMathLibraryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class Main
{
    private static final Logger LOG  = LoggerFactory.getLogger(Main.class);

    public static void main( String[] args )
    {
        /**
         * This variable represents reference which is injected in system in many places (hundrends) and can't be
         * easily rewritten.
         */
        LegacyMathLibrary legacyMathLibrary;

        //example with legacy implementation
        legacyMathLibrary= new LegacyMathLibraryImpl();
        usageInLegacySystem(legacyMathLibrary, "legacy");

        //just emphasize  that there will be assigned new instance
        legacyMathLibrary = null;

        //example with better - new - implementation. nothing change only instance of LegacyMathLibrary.
        legacyMathLibrary = new BetterMathLibraryAdapter(new BetterMathLibraryImpl());
        usageInLegacySystem(legacyMathLibrary, "better via adapter");
    }

    private static void usageInLegacySystem(LegacyMathLibrary legacyMathLibrary, String mathLibrary) {
        LOG.info("{} math library - prime decomposition of number 16380 is {}", mathLibrary,
                legacyMathLibrary.decomposeToPrimeNumbers(16380));
        LOG.info("{} math library - max common divisor of numbers 1260 and 3276 is {}", mathLibrary,
                legacyMathLibrary.maximumCommonDivisor(1260, 3276));
    }
}
