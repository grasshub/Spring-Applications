package com.springclass.tests;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import com.springclass.bo.KioskService;
import com.springclass.configuration.JavaConfig;
import com.springclass.domain.DVDInfo;

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
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 *
 * @author The Trivera Group Tech Team.
 */
@RunWith(JUnit4.class)
public class TestClient {

    private final Logger logger = LoggerFactory.getLogger(TestClient.class);

    public String test() {

        /*
        Set Active Profiles to "US"
         */
    	System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "NL");
    
        //final String searchTitle = "2 Fast 2 Furious";
        final String searchTitle = "Phone Booth";

        final StringBuilder sb = new StringBuilder();
        sb.append(searchTitle);

        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        // set active profile to "US"
        
        /*
        applicationContext.getEnvironment().setActiveProfiles("NL");
        applicationContext.register(JavaConfig.class);
        applicationContext.refresh();
        */
        
        KioskService kioskService = applicationContext.getBean(KioskService.class);

        List<DVDInfo> result = kioskService.searchByTitle(searchTitle);
        if (result.size() == 0) {
            logger.info("Error, the DVD '2 Fast 2 Furious' should be available at location 'BUR-1'");
            sb.append(" NotFound");
        }

        DVDInfo dvdInfo = result.get(0);

        int loandID = kioskService.loanDVD(dvdInfo.getId(), "j.wirth", "whoknows", new Date(), "AMS-1");
        logger.info("*******************************************************");
        logger.info("Loan added loan id: {}", loandID);
        logger.info("*******************************************************");

        sb.append(": ");
        sb.append(loandID);

        ((ConfigurableApplicationContext)applicationContext).close();
        return sb.toString();
    }

    @Test
    public void testExecuteTestClient() {
        TestClient testClient = new TestClient();
        String result = testClient.test();

        //assertThat(result, is("2 Fast 2 Furious: 9494"));
        assertThat(result, is("Phone Booth: 9494"));
    }

} // The End...
