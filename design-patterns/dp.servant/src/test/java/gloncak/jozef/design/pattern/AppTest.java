package gloncak.jozef.design.pattern;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println(ZonedDateTime.now().get(ChronoField.YEAR) - 1985);
//        assertTrue( true );
    }
}
