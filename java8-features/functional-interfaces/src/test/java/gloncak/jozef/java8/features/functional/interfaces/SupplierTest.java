package gloncak.jozef.java8.features.functional.interfaces;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.function.*;

public class SupplierTest {

    /**
     * Lambda expression for {@link BooleanSupplier} functinal interface
     * <p>
     * No input parameters. Lambda expression return boolean value which represents whether day of specified date is
     * even.
     */
    @Test
    public void booleanSupplierTest() {
        String date = "2019-10-31";
        BooleanSupplier isEvenDay = () -> LocalDate.parse(date).getDayOfMonth() % 2 == 0;

        Assert.assertFalse(isEvenDay.getAsBoolean());
    }

    /**
     * Lambda expression for {@link DoubleSupplier} functional interface.
     * <p>
     * No input parameters. Lambda expression return double value which represents number of seconds from start of
     * epoch.
     */
    @Test
    public void doubleSupplierTest() {
        String time = "2019-04-04T14:43:54.334";
        long msFromEpoch = LocalDateTime.parse(time).toInstant(ZoneOffset.UTC).toEpochMilli();
        DoubleSupplier provideSecondsFromEpoch = () -> msFromEpoch / 1000;

        Assert.assertEquals(msFromEpoch / 1000, provideSecondsFromEpoch.getAsDouble(), 0.01);
    }

    /**
     * Lambda expression for {@link IntSupplier} functional interface.
     * <p>
     * No input parameters. Lambda expression return integer value which represents current month.
     */
    @Test
    public void intSupplierTest() {
        IntSupplier currentMonthOrder = () -> LocalDate.now().getMonthValue();
        Assert.assertEquals(LocalDate.now().getMonthValue(), currentMonthOrder.getAsInt());
    }

    /**
     * Lambda expression for {@link LongSupplier} functional interface.
     * <p>
     * No input parameters. Lambda expression return long value which represents number of seconds from start of
     * epoch.
     */
    @Test
    public void longSupplierTest() {
        LongSupplier currentTimeInMiliSeconds = () -> LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();

        //test that between time from lambda and from System is less than 100 ms.
        Assert.assertTrue(System.currentTimeMillis() - currentTimeInMiliSeconds.getAsLong() < 100);
    }

    /**
     * Lambda expression for {@link Supplier} functional interface.
     * <p>
     * No input parameters. Lambda expression instance of Random class.
     */
    @Test
    public void supplierTest() {
        Supplier<Random> randomSupplier = Random::new;
        Random randomGenerator1 = randomSupplier.get();
        Random randomGenerator2 = randomSupplier.get();
        Assert.assertTrue(randomGenerator1 instanceof Random);
        Assert.assertTrue(randomGenerator2 instanceof Random);
        Assert.assertNotSame(randomGenerator1, randomGenerator2);
    }

}
