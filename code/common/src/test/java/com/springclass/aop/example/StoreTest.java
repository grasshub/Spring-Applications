package com.springclass.aop.example;

//Hamcrest
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.core.IsNull.*;

import com.springclass.configuration.JavaConfig;
import com.springclass.dao.DVDInfoDAO;
import com.springclass.domain.DVDInfo;
import com.springclass.fixture.DVDInfoFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

public class StoreTest {

    private Store store;

    @Before
    public void beforeEachTest(){
        store = new StoreImpl();
    }

    @After
    public void afterEachTest(){
        store = null;
    }

    @Test
    public void testFindFileOperation() throws Exception{
        double result = store.purchaseItem("Mountain Bike");
        assertThat(result, is(greaterThan(0.0D)));
    }

} // The End...
