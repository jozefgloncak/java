package gloncak.jozef.java8.features.functional.interfaces;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

public class OperatorTest {

    /**
     * Lambda expression for {@link BinaryOperator} functional interface
     * <p>
     * Input parameters are of type string. Also returning value is string which is from concatenation of input strings.
     */
    @Test
    public void binaryOperatorTest() {
        BinaryOperator<String> concatenator = (firstName, surname) -> firstName + " " + surname;

        String concated = concatenator.apply("Adam", "Adamovic");
        Assert.assertEquals("Adam Adamovic", concated);
    }

    /**
     * Lambda expression for {@link DoubleBinaryOperator} functional interface
     * <p>
     * Input parameters (2) are of type double and returning value addition of input parameters
     */
    @Test
    public void doubleBinaryOperatorTest() {
        DoubleBinaryOperator additonOfDoubles = (num1, num2) -> num1 + num2;

        double result = additonOfDoubles.applyAsDouble(3.14, 6.86);
        Assert.assertEquals(10.0, result, 0.001);
    }

    /**
     * Lambda expression for {@link DoubleUnaryOperator} functional interface
     * <p>
     * Input parameter (1) is of type double and is negated.
     */
    @Test
    public void doubleUnaryOperatorTest() {
        DoubleUnaryOperator negation = numDouble -> -numDouble;

        Assert.assertEquals(4.3 * -1, negation.applyAsDouble(4.3), 0.01);
    }

    /**
     * Lambda expression for {@link IntBinaryOperator} functional interface
     * <p>
     * Input parameters are of type integer. Returning value is deduction of this parameters.
     */
    @Test
    public void intBinaryOperatorTest() {
        IntBinaryOperator intDeduction = (num1, num2) -> num1 - num2;
        Assert.assertEquals(10, intDeduction.applyAsInt(15, 5));
    }

    /**
     * Lambda expression for {@link IntUnaryOperator} functional interface
     * <p>
     * Input parameters are of type integer. Returning value is negated integer.
     */
    @Test
    public void intUnaryOperatorTest() {
        IntUnaryOperator negation = numInt -> -numInt;
        Assert.assertEquals(1001, negation.applyAsInt(-1001));
    }

    /**
     * Lambda expression for {@link LongBinaryOperator} functional interface
     * <p>
     * Input parameters are of type long. Returning value is sum of parameters.
     */
    @Test
    public void longBinaryOperatorTest() {
        LongBinaryOperator numSum = (numLong1, numLong2) -> numLong1 + numLong2;
        Assert.assertEquals(4000000000L, numSum.applyAsLong(2000000000, 2000000000));
    }

    /**
     * Lambda expression for {@link LongUnaryOperator} functional interface
     * <p>
     * Input parameters are of type long. Returning value is result of applied absolute value function.
     */
    @Test
    public void longUnaryOperatorTest() {
        LongUnaryOperator absoluteValue = Math::abs;
        Assert.assertEquals(9999999999L, absoluteValue.applyAsLong(-9999999999L));
    }

    /**
     * Lambda expression for {@link UnaryOperator} functional interface
     * <p>
     * Input parameters are of type Triangle. Returning value is of type triangle where all attributes are converted
     * to cm
     */
    @Test
    public void unaryOperatorTest() {
        class Triangle {
            int a; //in mm
            int b; //in mm
            int c; //in mm

            public Triangle(int a, int b, int c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }
        }
        UnaryOperator<Triangle> convertToCm = triangle -> {
            triangle.a /= 10;
            triangle.b /= 10;
            triangle.c /= 10;
            return triangle;
        };

        Triangle trian = new Triangle(30, 40, 50);
        convertToCm.apply(trian);
        Assert.assertEquals(3, trian.a);
        Assert.assertEquals(4, trian.b);
        Assert.assertEquals(5, trian.c);

    }
}
