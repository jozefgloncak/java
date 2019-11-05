package gloncak.jozef.java8.features.functional.interfaces;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.function.*;

public class FunctionTest {

    /**
     * Lambda expression for {@link BiFunction} functional interface.
     * <p>
     * Input parameters of various (or equal) types. Lambda expression returns concatenation of this parameters.
     */
    @Test
    public void biFunctionTest() {
        BiFunction<Integer, String, String> concatenator = (age, name) -> age + " " + name;

        String concatenated = concatenator.apply(14, "Martin Kralik");
        Assert.assertEquals("14 Martin Kralik", concatenated);
    }

    /**
     * Lambda expression for {@link DoubleFunction} functional interface.
     * <p>
     * Input double parameter is converted to Long instance.
     */
    @Test
    public void doubleFunctionTest() {
        DoubleFunction<Long> roundDouble = Math::round;

        Assert.assertEquals((Long) 3L, roundDouble.apply(3.141592));
    }

    /**
     * Lambda expression for {@link DoubleToIntFunction} functional interface.
     * <p>
     * Input parameter is of type double. LE returns value of type int. In this case conversion is just rounding
     * double and casting to int.
     */
    @Test
    public void doubleToIntFunctionTest() {
        DoubleToIntFunction roundDoubleToInt = doubleNum -> (int) Math.round(doubleNum);

        Assert.assertEquals(3, roundDoubleToInt.applyAsInt(3.14));
        Assert.assertEquals(123456789, roundDoubleToInt.applyAsInt(123456789.14));
    }

    /**
     * Lambda expression for {@link DoubleToLongFunction} functional interface.
     * <p>
     * Input parameter is of type double. LE returns value of type long. In this case conversion is just rounding
     * double to long
     */
    @Test
    public void doubleToLongFunctionTest() {
        DoubleToLongFunction roundDoubleToLong = doubleNum -> Math.round(doubleNum);

        Assert.assertEquals(3, roundDoubleToLong.applyAsLong(3.14));
        Assert.assertEquals(123456789, roundDoubleToLong.applyAsLong(123456789.14));
    }

    /**
     * Lambda expression for {@link Function} functional interface.
     * <p>
     * Input parameter of int type convert to string
     * Also usage of compose() and andThen() methods for assembling of function is demonstrated.
     */
    @Test
    public void functionTest() {
        Function<Integer, String> convertIntToString = numInt -> Integer.toString(numInt);

        Assert.assertEquals("45", convertIntToString.apply(45));

        Function<Integer, Integer> constantAdditon = numInt -> numInt + 10;
        Function<Integer, String> comosedFunctionWithAddition = convertIntToString.compose(constantAdditon);
        Assert.assertEquals("55", comosedFunctionWithAddition.apply(45));

        Function<String, String> prefixAddition = originStr -> "PREFIX: " + originStr;
        Function<Integer, String> composedFunctionWithPrefixing = convertIntToString.andThen(prefixAddition);
        Assert.assertEquals("PREFIX: 45", composedFunctionWithPrefixing.apply(45));
    }

    /**
     * Lambda expression for {@link IntFunction} functional interface.
     * <p>
     * Input parameter of int type convert to string.
     */
    @Test
    public void intFunctionTest() {
        IntFunction<String> convertIntToString = String::valueOf;
        Assert.assertEquals("101", convertIntToString.apply(101));
    }

    /**
     * Lambda expression for {@link IntToDoubleFunction} functional interface.
     * <p>
     * Input parameter of int type convert to double. counting area of circle.
     */
    @Test
    public void intToDoubleFunctionTest() {
        IntToDoubleFunction circleArea = r -> Math.PI * r * r;

        Assert.assertEquals(Math.PI * Math.pow(2, 2), circleArea.applyAsDouble(2), 0.01);
        Assert.assertEquals(Math.PI * Math.pow(3, 2), circleArea.applyAsDouble(3), 0.01);
    }

    /**
     * Lambda expression for {@link IntToLongFunction} functional interface.
     * <p>
     * Input parameter of int type convert to long. count number of China inhabitants where 2 000 000 000 is number of
     * inhabitants in year 2030;
     */
    @Test
    public void intToLongFunctionTest() {
        IntToLongFunction chineseCounter = numInt -> numInt + 2000000000L;

        Assert.assertEquals(2300000000L, chineseCounter.applyAsLong(300000000));
    }

    /**
     * Lambda expression for {@link LongFunction} functional interface.
     * <p>
     * Input parameter of long type convert to String.
     */
    @Test
    public void longFunctionTest() {
        LongFunction<String> convertToString = String::valueOf;
        Assert.assertEquals("4000000001", convertToString.apply(4000000001L));
    }

    /**
     * Lambda expression for {@link LongToDoubleFunction} functional interface.
     * <p>
     * Input parameter of long type is used to calculate area of circle
     */
    @Test
    public void longToDoubleFunctionTest() {
        LongToDoubleFunction circleArea = r -> Math.PI * Math.pow(r, 2);
        Assert.assertEquals(100L * 100L * Math.PI, circleArea.applyAsDouble(100L), 0.01);
    }

    /**
     * Lambda expression for {@link LongToIntFunction} functional interface.
     * <p>
     * Input parameter of long type is used to calculate figures sum.
     */
    @Test
    public void longToIntFunctionTest() {
        LongToIntFunction sumFigures = longNum -> {
            int sum = 0;
            do {
                sum += longNum % 10;
            } while ((longNum = longNum / 10) != 0);
            return sum;
        };
        Assert.assertEquals(46, sumFigures.applyAsInt(12345678910L));
        Assert.assertEquals(99, sumFigures.applyAsInt(99999999999L));
        Assert.assertEquals(0, sumFigures.applyAsInt(0L));
        Assert.assertEquals(4, sumFigures.applyAsInt(4L));
    }

    /**
     * Lambda expression for {@link ToDoubleBiFunction} functional interface.
     * <p>
     * 2 input parameters converted to double value. Calculate cilinder volume.
     */
    @Test
    public void toDoubleBiFunctionTest() {
        ToDoubleBiFunction<Double, Double> cilinderVolume = (r, v) -> Math.PI * r * r * v;
        Assert.assertEquals(Math.PI * 4 * 10, cilinderVolume.applyAsDouble(2.0, 10.0), 0.001);
    }

    /**
     * Lambda expression for {@link ToDoubleFunction} functional interface.
     * <p>
     * 1 input parameters converted to double value. Calculate cilinder volume.
     */
    @Test
    public void toDoubleFunctionTest() {
        class Square {
            private double a;
        }
        ToDoubleFunction<Square> squareArea = (square) -> square.a * square.a;

        Square square = new Square();
        square.a = 1.5;

        Assert.assertEquals(2.25, squareArea.applyAsDouble(square), 0.001);
    }

    /**
     * Lambda expression for {@link ToIntBiFunction} functional interface.
     * <p>
     * 2 input parameters of LocalTime converted to integer value. Calculate diference of nano seconds.
     */
    @Test
    public void toIntBiFunctionTest() {
        ToIntBiFunction<LocalTime, LocalTime> nanoStampDifference = (stamp1, stamp2) -> Duration.between(stamp2,
                stamp1).getNano();

        LocalTime stamp1 = LocalTime.parse("14:34:32.123");
        LocalTime stamp2 = LocalTime.parse("14:43:32.241");

        Assert.assertEquals(118000000, nanoStampDifference.applyAsInt(stamp2, stamp1));
    }

    /**
     * Lambda expression for {@link ToIntFunction} functional interface.
     * <p>
     * 1 input parameters of type Rectangle converted to int value. Calculate circuit of rectangle.
     */
    @Test
    public void toIntFunctionTest() {
        class Rectangle {
            int a;
            int b;

            public Rectangle(int a, int b) {
                this.a = a;
                this.b = b;
            }
        }

        Rectangle rectangle = new Rectangle(4, 3);

        ToIntFunction<Rectangle> calculateCircuit = rect -> rect.a * 2 + rect.b * 2;

        Assert.assertEquals(14, calculateCircuit.applyAsInt(rectangle));
    }

    /**
     * Lambda expression for {@link ToLongBiFunction} functional interface.
     * <p>
     * 2 input parameters of LocalTime converted to long value. Calculate dfference of time stamps in seconds.
     */
    @Test
    public void toLongBiFunctionTest() {
        ToLongBiFunction<LocalTime, LocalTime> secondStampDifference = (stamp1, stamp2) -> Duration.between(stamp2,
                stamp1).getSeconds();

        LocalTime stamp1 = LocalTime.parse("14:34:32.123");
        LocalTime stamp2 = LocalTime.parse("14:43:33.241");

        Assert.assertEquals(541, secondStampDifference.applyAsLong(stamp2, stamp1));
    }

    /**
     * Lambda expression for {@link ToLongFunction} functional interface.
     * <p>
     * Input parameters of LocalDateTime converted to long value. Provide ms from epoch.
     */
    @Test
    public void toLongFunctionTest() {
        ToLongFunction<LocalDateTime> msFromEpoch = stamp -> stamp.toInstant(ZoneOffset.UTC).getEpochSecond();
        LocalDateTime stamp = LocalDateTime.parse("2019-04-04T04:04:04");
        Assert.assertEquals(1554350644, msFromEpoch.applyAsLong(stamp));
    }
}
