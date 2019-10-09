package gloncak.jozef.java8.optional;

import gloncak.jozef.java8.optional.api.Season;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

/**
 * Contains test which compare behaviour of methods orElse() and orElseGet() of Optional class.
 */
public class OptionalElseTest {

    /*
        Tests that with method orElse of Optional object else part evaluated also if it isn't
        needed (IF part is matched).
    */
    @Test
    public void matchIfInOrElseMethod() {
        Season mockSeason = Mockito.mock(Season.class);
        String season = provideSeason(1).orElse(mockSeason.noSeason());
        Assert.assertEquals("spring", season);
        Mockito.verify(mockSeason, Mockito.times(1)).noSeason();
    }

    @Test
    public void matchElseInOrElseMethod() {
        Season mockSeason = Mockito.mock(Season.class);
        Mockito.when(mockSeason.noSeason()).thenReturn("no season");
        String season = provideSeason(5).orElse(mockSeason.noSeason());

        Assert.assertEquals("no season", season);
        Mockito.verify(mockSeason, Mockito.times(1)).noSeason();
    }

    /**
     * Tests that else part of orElseGet() method is called only if IF part doesn' match.
     */
    @Test
    public void matchIfInOrElseGetMethod() {
        Season mockSeason = Mockito.mock(Season.class);
        String season = provideSeason(1).orElseGet(() -> mockSeason.noSeason());
        Assert.assertEquals("spring", season);
        Mockito.verify(mockSeason, Mockito.times(0)).noSeason();
    }

    @Test
    public void matchElseInOrElseGetMethod() {
        Season mockSeason = Mockito.mock(Season.class);
        Mockito.when(mockSeason.noSeason()).thenReturn("no season");
        String season = provideSeason(5).orElse(mockSeason.noSeason());

        Assert.assertEquals("no season", season);
        Mockito.verify(mockSeason, Mockito.times(1)).noSeason();
    }

    private Optional<String> provideSeason(int season) {
        switch (season) {
            case 1: return Optional.of("spring");
            case 2: return Optional.of("summer");
            case 3: return Optional.of("autmn");
            case 4: return Optional.of("winter");
            default: return Optional.empty();
        }
    }


}
