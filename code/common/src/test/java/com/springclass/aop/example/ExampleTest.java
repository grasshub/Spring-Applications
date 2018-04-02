package com.springclass.aop.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.assertj.core.api.Assertions.atIndex; // for List assertions
import static org.assertj.core.api.Assertions.entry;  // for Map assertions
import static org.assertj.core.api.Assertions.tuple; // when extracting several properties at once
import static org.assertj.core.api.Assertions.fail; // use when writing exception tests
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown; // idem
import static org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import static org.assertj.core.api.Assertions.offset; // for floating number assertions
import static org.assertj.core.api.Assertions.anyOf; // use with Condition
import static org.assertj.core.api.Assertions.contentOf; // use with File assertions

public class ExampleTest {

    private Example example;

    @Before
    public void beforeEachTest(){
        example = new ExampleImpl();
    }

    @After
    public void afterEachTest(){
        example = null;
    }

    @Test
    public void testFindFileOperation() throws Exception{
        String result = example.findFileOperation();

        assertThat(result, is("return findFileOperation"));

        // String specific assertions
        // Assert-J
        assertThat(result)
                .as("Returned String")
                .startsWith("retu")
                .endsWith("ion")
                .isEqualToIgnoringCase("return findFileOperation");
    }

    @Test
    public void testNetworkOperation() throws Exception{
        String result = example.networkOperation();
        assertThat(result, is("return networkOperation"));
    }

    @Test
    public void testFormatNumberOperation() throws Exception{
        Long result = example.formatNumberOperation();
        assertThat(result, is(12345L));
    }

    @Test
    public void testExampleOperation() throws Exception{
        String result = example.exampleOperation();
        assertThat(result, is("return exampleOperation"));
    }

} // The End...
