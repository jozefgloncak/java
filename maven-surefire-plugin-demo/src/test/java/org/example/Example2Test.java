package org.example;


import org.junit.Assert;
import org.junit.Test;

public class Example2Test {

    @Test
    public void example1Test() {
        Assert.fail("Tento test musel spadnut");
    }
    @Test
    public void example2Test() {

    }
    @Test
    public void example3Test() {
        Assert.fail("Tento test spadol z ineho dovodu");
    }
    @Test
    public void example4Test() {

    }
}
