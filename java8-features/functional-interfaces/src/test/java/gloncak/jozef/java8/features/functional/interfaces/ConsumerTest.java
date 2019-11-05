package gloncak.jozef.java8.features.functional.interfaces;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.*;

public class ConsumerTest {

    /**
     * Lambda expression for {@link BiConsumer} functional interface
     * <p>
     * Input parameters (2 various types) are evaluated. First parameter is collection to which is second parameter
     * added.
     */
    @Test
    public void biConsumerTest() {
        final List<String> names = new ArrayList<>();
        BiConsumer<List<String>, String> biConsumer = (nameLst, name) -> nameLst.add(name); //accept method

        biConsumer.accept(names, "Martin");
        biConsumer.accept(names, "Juraj");

        Assert.assertEquals("Martin", names.get(0));
        Assert.assertEquals("Juraj", names.get(1));
        Assert.assertEquals(2, names.size());
    }

    /**
     * Lambda expression for {@link Consumer} functional interface.
     * <p>
     * Input parameter is array to which is added random integer number.
     */
    @Test
    public void consumerTest() {
        List<Integer> nums = new ArrayList<>();
        Random random = new Random();
        Consumer<List<Integer>> addRandomInteger = numbers -> numbers.add(random.nextInt());

        Assert.assertEquals(0, nums.size());
        addRandomInteger.accept(nums);
        Assert.assertEquals(1, nums.size());
        addRandomInteger.accept(nums);
        Assert.assertEquals(2, nums.size());
    }

    /**
     * Lambda expression for {@link DoubleConsumer} functional interface.
     * <p>
     * Input double parameter is converted to long. Long numbers are collected in standalone array.
     */
    @Test
    public void doubleConsumerTest() {
        List<Long> longs = new ArrayList<>();
        DoubleConsumer addDoubleAsLong = num -> longs.add(Math.round(num));

        Assert.assertEquals(0, longs.size());
        addDoubleAsLong.accept(4.13);
        addDoubleAsLong.accept(5.354);

        Assert.assertEquals((Long) 4L, longs.get(0));
        Assert.assertEquals((Long) 5L, longs.get(1));
        Assert.assertEquals(2, longs.size());
    }

    /**
     * Lambda expression for {@link IntConsumer} functional interface.
     * <p>
     * Input int parameter is stored to higher defined array.
     */
    @Test
    public void intConsumerTest() {
        List<Integer> integers = new ArrayList<>();
        IntConsumer intArrayAppender = integers::add;

        intArrayAppender.accept(4);
        intArrayAppender.accept(3);

        Assert.assertEquals(0, integers.size());
        Assert.assertEquals((Integer) 4, integers.get(0));
        Assert.assertEquals((Integer) 3, integers.get(1));
        Assert.assertEquals(2, integers.size());
    }

    /**
     * Lambda expression for {@link LongConsumer} functional interface.
     * <p>
     * Input long parameter is added to higher defined set.
     */
    @Test
    public void longConsumerTest() {
        Set<Long> longs = new HashSet<>();
        LongConsumer longConsumer = numLong -> longs.add(numLong);

        Assert.assertEquals(0, longs.size());
        longConsumer.accept(53);
        Assert.assertEquals(1, longs.size());
    }

    /**
     * Lambda expression for {@link ObjDoubleConsumer} functional interface.
     * <p>
     * Input String and Double parameters are concatenated and class inner attribute is set.
     */
    @Test
    public void objDoubleConsumerTest() {
        StringWrapper stringWrapper = new StringWrapper();
        Assert.assertEquals("", stringWrapper.value);
        ObjDoubleConsumer<String> doubleStringConcatenate =
                (suffix, doubleNum) -> stringWrapper.setValue(doubleNum + " " + suffix);
        doubleStringConcatenate.accept("EUR", 4.3);
        Assert.assertEquals("4.3 EUR", stringWrapper.value);
    }

    /**
     * Lambda expression for {@link ObjIntConsumer} functional interface.
     * <p>
     * Input String and Integer parameters are concatenated and class inner attribute is set.
     */
    @Test
    public void objIntConsumerTest() {
        StringWrapper strWrapper = new StringWrapper();
        Assert.assertEquals("", strWrapper.value);
        ObjIntConsumer<String> intStringConcatenate = (prefix, intNum) -> strWrapper.setValue(prefix + " " + intNum);
        intStringConcatenate.accept("napr.:", 4);
        Assert.assertEquals("napr.: 4", strWrapper.value);
    }

    /**
     * Lambda expression for {@link ObjLongConsumer} functional interface.
     * <p>
     * Input String and Long parameters are concatenated and class inner attribute is set.
     */
    @Test
    public void objLongConsumerTest() {
        StringWrapper strWrapper = new StringWrapper();
        Assert.assertEquals("", strWrapper.value);
        ObjLongConsumer<String> longStringConcatenate = (prefix, intNum) -> strWrapper.setValue(prefix + " " + intNum);
        longStringConcatenate.accept("napr.:", 12345678910L);
        Assert.assertEquals("napr.: 12345678910", strWrapper.value);
    }

    class StringWrapper {
        String value;

        public StringWrapper() {
            this.value = "";
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
