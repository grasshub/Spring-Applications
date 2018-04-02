package com.springclass.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.fail;

public class PojoTest {

    Pojo pojo;

    //-----------------------------------------------------------------------//

    @Before
    public void beforeEachTest() {
        pojo = new Pojo();
    }

    @After
    public void afterEachTest() {
        pojo = null;
    }

    @Test
    public void test__createPojo(){
        assertThat(pojo, is(notNullValue()));
    }

} // The End...
