package com.springclass.test;

import com.springclass.configuration.JavaConfig;
import com.springclass.rest.client.SpringRESTClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
        testClient.test();
    }

    public String test() {

       final AnnotationConfigApplicationContext applicationContext =
               new AnnotationConfigApplicationContext(JavaConfig.class);

        SpringRESTClient client = applicationContext.getBean(SpringRESTClient.class);

//        client.removeUser("3");
//
//        client.createUser();
//
//        client.updateUser();
//
//        client.getAllUsers();

        client.getUser();

        return "completed";
    }


    @Test
    public void testExecuteTestClient() {
        TestClient testClient = new TestClient();
        String result = testClient.test();

        assertThat(result, is("completed"));
        logger.info("TestClient result: {}", result);
    }


} // The End...
