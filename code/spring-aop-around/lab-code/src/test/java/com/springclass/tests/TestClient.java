package com.springclass.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springclass.bo.KioskService;
import com.springclass.configuration.AopConfig;
import com.springclass.domain.DVDInfo;

/**
 * <p/>
 * This component and its source code representation are copyright protected and
 * proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 * <p/>
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Group, Inc.
 * <p/>
 * Copyright (c) 2014 The Trivera Group, LLC. http://www.triveratech.com
 * http://www.triveragroup.com
 * </p>
 *
 * @author The Trivera Group Tech Team.
 */
public class TestClient {

	private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

	// --- Tests -------------------------------------------------------------//

	/**
	 * Test for Exercise: SPRING AOP – AROUND ADVICE lab.
	 * 
	 * @return
	 */
	public int test__profilerAdviceProxy__AroundAdvice() {
		
		final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
		KioskService kioskService = applicationContext.getBean("kioskService", KioskService.class);

		String searchTitle = "2 Fast 2 Furious";
		List<DVDInfo> result = kioskService.searchByTitle(searchTitle);
		if (result.size() == 0) {
			logger.error("Error, the DVD '2 Fast 2 Furious' should be available at location 'BUR-2'");
			fail();
		}

		DVDInfo dvdInfo = result.get(0);
		// This normally returns -1, which we will correct inside
        // com.springclass.aop.advice.BeforeAfterLoggingAdvice::before(..)
		int loanId = kioskService.loanDVD(dvdInfo.getId(), "j.wirth", "whoknows", new Date(), "AMS-1");

		// Correct:
		// int loanId = kioskService.loanDVD(dvdInfo.getId(), "j.wirth",
		// "whoknows", new Date(), "AMS-1");

		assertThat(dvdInfo.getId(), is("B00005JM4Z"));
		assertThat(loanId, is(9494));
		return loanId;
	}

	@Test
	public void test__ExecuteTestClient() {
		TestClient testClient = new TestClient();

		logger.info("-------------------------");

		int loanId = testClient.test__profilerAdviceProxy__AroundAdvice();
		assertThat(loanId, is(9494));
		logger.info("result: {}", loanId);
		logger.info("-------------------------");

	}

} // The End...
