package com.springclass.features;

import com.springclass.Calculator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.assertj.core.api.Assertions.fail; // use when writing exception tests
import static org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import static org.assertj.core.api.Assertions.offset; // for floating number assertions
import static org.assertj.core.api.Assertions.anyOf; // use with Condition
import static org.assertj.core.api.Assertions.contentOf; // use with File assertions

public class CalculatorStepDefs {

    @Steps
    private CalculatorSteps calculatorSteps;

    private Calculator calculator = new Calculator();
    private int result;

    @Given("^I want to add '(\\d+)' and '(\\d+)'$")
    public void i_want_to_add_two_numbers(final int a, final int b) throws Throwable {
        calculatorSteps.add(a, b);
    }

    @Given("^I want to subtract '(\\d+)' and '(\\d+)'$")
    public void i_want_to_subtract(final int a, final int b) throws Throwable {
        calculatorSteps.subtract(a, b);
    }

    @Given("^I want to multiply '(\\d+)' and '(\\d+)'$")
    public void i_want_to_multiply(final int a, final int b) throws Throwable {
        calculatorSteps.multiply(a, b);
    }

    @Then("^the result should be '(\\d+)'$")
    public void check_Results(int expected) {
        calculatorSteps.verify_result(expected);
    }

//    @When("I search for items containing '(.*)'")
//    public void searchByKeyword(String keyword) {
//        buyer.searches_for_items_containing(keyword);
//    }
//
//    @Then("I should only see items related to '(.*)'")
//    public void resultsForACategoryAndKeywordInARegion(String keyword) {
//        buyer.should_see_items_related_to(keyword);
//    }

}
