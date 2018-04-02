package com.springclass.features;

import com.springclass.Calculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by mickknutson on 4/29/16.
 */
public class CalculatorSteps {

    private Calculator calculator = new Calculator();
    private int result;

    public void add(int a, int b){
        result = calculator.add(a, b);
    }

    public void subtract(int a, int b){
        result = calculator.subtract(a, b);
    }

    public void multiply(int a, int b){
        result = calculator.multiply(a, b);
    }

    public void verify_result(int expected){
        assertThat(result, is(expected));
    }

}
