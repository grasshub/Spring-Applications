package com.springclass.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.springclass.configuration.JavaConfig;
import com.springclass.rest.client.SpringRESTClient;

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

        client.removeUser();

        client.updateUser();

        client.createUser(new RestTemplate());

        client.getAllUsers();

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
