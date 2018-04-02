package com.springclass.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p/>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 * <p/>
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 * <p/>
 * Copyright (c) 2016 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 *
 * @author The Trivera Group Tech Team.
 */
@RunWith(JUnit4.class)
public final class TestClient {

    private static final Logger logger = LoggerFactory
            .getLogger(TestClient.class);

    public static void main(String args[]) {
        TestClient testClient = new TestClient();
   
        String result = testClient.test();
        logger.info("Encrypted Password: '{}'", result);
       
        
    }

    public String test() {

        final StringBuilder sb = new StringBuilder();

        return sb.toString();

    }

    @Test
    public void testExecuteTestClient() {
        TestClient testClient = new TestClient();
//        String result = testClient.test();
        String result = testClient.test();

        logger.info("TestClient Result: '{}'", result);
//        assertThat(result, is("foo bar"));
    }

} // The End...
