package gloncak.jozef.java8.optional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Checks that if Optional contains null than other type of exception is raised
 */
public class OptionalExceptionRaisingTest {

    /**
     * Tests state when value is null
     */
    @Test(expected = IllegalStateException.class)
    public void throwOtherExceptionIfValueNullTest() {
        Optional<String> string = Optional.ofNullable(null);
        string.orElseThrow(IllegalStateException::new).toUpperCase();
    }

    /**
     * Tests state when value isn't null
     */
    @Test
    public void executeOperationIfValueNotNullTest() {
        Optional<String> string = Optional.ofNullable("string");
        String upperCasedString = string.orElseThrow(IllegalStateException::new).toUpperCase();
        Assert.assertEquals("STRING", upperCasedString);
    }

}
