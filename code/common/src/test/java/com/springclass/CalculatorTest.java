package com.springclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    // CREATE an addition test.
    private Calculator calculator = new Calculator();

    @Before
    public void beforeEachTest(){
        calculator = new Calculator();
    }

    @After
    public void afterEachTest(){
        calculator = null;
    }

    @Test
    public void test__Addition(){
        int expected = 50;
        int result = calculator.add(49, 1);

        assertEquals(expected, result);

    }

    @Test
    public void test__Subtraction(){
        int expected = 75;
        int result = calculator.subtract(99, 24);

        assertEquals(expected, result);

    }

    @Test
    public void test__Multiplication(){
        int expected = 1764;
        int result = calculator.multiply(42, 42);

        assertEquals(expected, result);

    }

    @Test
    public void test__Division(){
        int expected = 1;
        int result = calculator.divide(7, 7);

        assertEquals(expected, result);

    }

    @Test
    public void test_main_method_execution(){

        Calculator.main(new String[]{"1", "2"});
    }


} // The End...
