package com.springclass.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springclass.configuration.JavaConfig;
import com.springclass.messenger.MessengerLooper;

@RunWith(JUnit4.class)
public class TestClient {

    private final Logger logger = LoggerFactory.getLogger(TestClient.class);

    public String testMessengerLooperAnnotation() {

        //-------------------------------------------------------------------//
        // Lab: Create Spring IoC instance (ApplicationContext)
    	final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        //-------------------------------------------------------------------//
        // Lab: Get MessageLooper from IoC Instance

    	MessengerLooper messengerLooper = applicationContext.getBean(MessengerLooper.class);
    	String result = messengerLooper.doIt();

        logger.info("messengeLooper result is '{}", result);
        ((ConfigurableApplicationContext)applicationContext).close();
        return result;
    }

    @Test
    public void testExecuteTestClient() {
        TestClient testClient = new TestClient();
        String result = testClient.testMessengerLooperAnnotation();

        assertThat(result, is("Hello World!"));        
        //assertThat(result, is("Goodbye World!"));
    }

} // The End...
