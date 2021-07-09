package com.example.Quoter;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void simpleTest() {
        int x = 32;
        int y = 5;

        Assert.assertEquals(160, x * y);
        Assert.assertEquals(37, x + y);
    }

    @Test(expected = ArithmeticException.class)
    public void error() {
        int x = 1 / 0;
    }
}
