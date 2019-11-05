package gloncak.jozef.java8.features.functional.interfaces;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.function.*;

public class PredicateTest {

    /**
     * Lambda expression for {@link BiPredicate} functional interface.
     * <p>
     * 2 input parameters of various (or equal) input types are used to produce one boolean return value
     */
    @Test
    public void biPredicateTest() {
        //true if Andrej older than 15
        BiPredicate<String, Integer> predicate1 = (name, age) -> "Andrej".equals(name) && age > 15;
        Assert.assertFalse(predicate1.test("Ben", 14));
        Assert.assertFalse(predicate1.test("Andrej", 14));
        Assert.assertFalse(predicate1.test("Andrej", 15));
        Assert.assertFalse(predicate1.test("Ben", 16));
        Assert.assertTrue(predicate1.test("Andrej", 16));


        //true if NOT David or younger than 30
        BiPredicate<String, Integer> predicate2 = (name, age) -> !"David".equals(name) || age < 30;
        Assert.assertTrue(predicate2.test("Cyril", 45));
        Assert.assertTrue(predicate2.test("Cyril", 29));
        Assert.assertTrue(predicate2.test("David", 29));
        Assert.assertFalse(predicate2.test("David", 32));

        //true if (Andrej older than 15) AND (NOT David || younger than 30)
        BiPredicate<String, Integer> predicate1And2 = predicate1.and(predicate2);
        Assert.assertTrue(predicate1And2.test("Andrej", 16));
        Assert.assertFalse(predicate1And2.test("Ben", 16));
        Assert.assertFalse(predicate1And2.test("Andrej", 14));
        Assert.assertTrue(predicate1And2.test("Andrej", 20));
        Assert.assertTrue(predicate1And2.test("Andrej", 31));

        //true if (Andrej and older than 15) OR (NOT David || younger than 30)
        BiPredicate<String, Integer> predicate1Or2 = predicate1.or(predicate2);
        Assert.assertTrue(predicate1Or2.test("Andrej", 16));
        Assert.assertTrue(predicate1Or2.test("Cyril", 45));
        Assert.assertTrue(predicate1Or2.test("Cyril", 29));
        Assert.assertTrue(predicate1Or2.test("David", 29));
        Assert.assertFalse(predicate1Or2.test("David", 31));
    }

    /**
     * Lambda expression for {@link java.util.function.DoublePredicate} functional interface.
     * <p>
     * Double input parameter is used to produce boolean return value
     */
    @Test
    public void doublePredicateTest() {
        DoublePredicate isBiggerThan100 = num -> num - 100 > 0;
        Assert.assertFalse(isBiggerThan100.test(3.14));
        Assert.assertFalse(isBiggerThan100.test(100));
        Assert.assertTrue(isBiggerThan100.test(100.4));
    }

    /**
     * Lambda expression for {@link IntPredicate} functional interface.
     * <p>
     * Intger input parameter is used to produce boolean return value. True if integer is even.
     * <p>
     * Also through and() method is demonstrated also division by 2 and 3 (it means 6 number).
     */
    @Test
    public void intPredicateTest() {
        IntPredicate isNumberDivisible2 = intNum -> intNum % 2 == 0;

        Assert.assertTrue(isNumberDivisible2.test(4));
        Assert.assertFalse(isNumberDivisible2.test(7));

        IntPredicate isNumberDivisible3 = intNum -> intNum % 3 == 0;

        IntPredicate isNumberDivisible6 = isNumberDivisible2.and(isNumberDivisible3);
        Assert.assertFalse(isNumberDivisible6.test(1));
        Assert.assertFalse(isNumberDivisible6.test(2));
        Assert.assertFalse(isNumberDivisible6.test(3));
        Assert.assertFalse(isNumberDivisible6.test(4));
        Assert.assertFalse(isNumberDivisible6.test(5));
        Assert.assertTrue(isNumberDivisible6.test(6));
    }

    /**
     * Lambda expression for {@link LongPredicate} functional interface.
     * <p>
     * Long input parameter is used to produce boolean return value. True if long greather than 100 and smaller than
     * 200.
     */
    @Test
    public void longPredicateTest() {
        LongPredicate gratherThan100LowerThen200 = longNum -> longNum > 100 && longNum < 200;

        Assert.assertFalse(gratherThan100LowerThen200.test(99));
        Assert.assertFalse(gratherThan100LowerThen200.test(100));
        Assert.assertTrue(gratherThan100LowerThen200.test(101));
        Assert.assertTrue(gratherThan100LowerThen200.test(150));
        Assert.assertTrue(gratherThan100LowerThen200.test(199));
        Assert.assertFalse(gratherThan100LowerThen200.test(200));
        Assert.assertFalse(gratherThan100LowerThen200.test(201));
    }

    /**
     * Lambda expression for {@link Predicate} functional interface.
     * <p>
     * Long input parameter is used to produce boolean return value. True if long greather than 100 and smaller than
     * 200.
     */
    @Test
    public void predicateTest() {
        Predicate<LocalDateTime> isEvenDayOfWeek = timeStamp -> timeStamp.getDayOfWeek().getValue() % 2 == 0;
        LocalDateTime timestamp1 = LocalDateTime.parse("2019-11-05T14:34:53");
        Assert.assertTrue(isEvenDayOfWeek.test(timestamp1));

        LocalDateTime timestamp2 = LocalDateTime.parse("2019-11-06T14:34:53");
        Assert.assertFalse(isEvenDayOfWeek.test(timestamp2));
    }

}
