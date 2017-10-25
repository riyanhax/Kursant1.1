package com.example.szzc.kursant11;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

/**
 * Created by szzc on 15.10.17.
 */

public class RoundTest {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Test
    public void doubleroundTest(){

        double value = 3.3453443;
        int places = 2;
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        BigDecimal b = new BigDecimal(3.35);
        b = b.setScale(places, RoundingMode.HALF_UP);
        bd = bd.setScale(places, RoundingMode.HALF_UP);


        assertEquals(b,bd);
    }

}
