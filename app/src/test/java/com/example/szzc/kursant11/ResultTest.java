package com.example.szzc.kursant11;


import org.junit.Test;

import static com.example.szzc.kursant11.Round.round;
import static junit.framework.Assert.assertEquals;

public class ResultTest {

    @Test
    public void ResultTest(){
    Double restEuro = 4 * 4.9545 - 13;

    assertEquals(6.82,round(restEuro,2));
    }
}
