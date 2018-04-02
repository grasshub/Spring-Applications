package com.springclass.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created by mickknutson on 4/26/16.
 */
public class StringUtilsTest {

    // CREATE an addition test.
    private StringUtils stringUtils;

    @Before
    public void beforeEachTest(){
        stringUtils = new StringUtils();
    }

    @After
    public void afterEachTest(){
        stringUtils = null;
    }

    @Test
    public void test__printLinenSeparator(){
        String expected = "\n------------------------------------\n";
        String result = stringUtils.printLineSeparator();

        assertEquals(expected, result);

    }


    @Test
    public void test__printStarLineSeparator(){
        String expected = "\n************************************\n";
        String result = stringUtils.printStarLineSeparator();

        assertEquals(expected, result);

    }

    @Test
    public void test__formatTime(){
        String expected = "1,234,567,890";
        String result = stringUtils.formatTime(1234567890L);

        assertEquals(expected, result);

    }

    @Test
    public void test__formatTime_with_locale(){
        String expected = "1 234 567 890";
        String result = stringUtils.formatTime(1234567890L, Locale.FRENCH);

        assertEquals(expected, result);

    }

}
