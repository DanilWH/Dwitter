package com.example.Quoter;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;

public class StupidPasswordEncoderTest {

    @Test
    public void encode() {
        StupidPasswordEncoder encoder = new StupidPasswordEncoder();

        Assert.assertEquals("secret: '341021dan'", encoder.encode("341021dan"));
        MatcherAssert.assertThat(encoder.encode("341021dan"), containsString("secret: '341021dan'"));
    }
}