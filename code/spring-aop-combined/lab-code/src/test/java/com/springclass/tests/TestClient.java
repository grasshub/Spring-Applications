package com.springclass.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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

	/**
	 * Test for Exercise: SPRING AOP – COMBINED ADVICE lab.
	 * 
	 * @return
	 */
	private int testBeanNameAutoProxyCreator_BeforeAdvice() {
		final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
		KioskService kioskService = applicationContext.getBean("kioskService", KioskService.class);

		String searchTitle = "2 Fast 2 Furious";
		List<DVDInfo> result = kioskService.searchByTitle(searchTitle);
		if (result.size() == 0) {
			System.out.println("Error, the DVD '2 Fast 2 Furious' should be available at location 'BUR-2'");
			fail();
		}

		DVDInfo dvdInfo = result.get(0);
		// returns -1
		int loanId = kioskService.loanDVD(dvdInfo.getId(), "j.wirth", "whoknows", new Date(), "AMS-1");
		// Correct:
		// int loanId = kioskService.loanDVD(dvdInfo.getId(), "j.wirth",
		// "whoknows", new Date(), "AMS-1");

		assertThat(dvdInfo.getId(), is("B00005JM4Z"));
		assertThat(loanId, is(9494));
		return loanId;
	}

	private int testBeanNameAutoProxyCreator_AfterAdvice() {
		final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
		KioskService kioskService = applicationContext.getBean("kioskService", KioskService.class);

		String searchTitle = "2 Fast 2 Furious";
		List<DVDInfo> result = kioskService.searchByTitle(searchTitle);

		DVDInfo dvdInfo = result.get(0);
		// kioskService returns -1, but BeforeAfterLoggingAdvice.afterReturning
		// modifies the result.
		int loanId = kioskService.loanDVD(dvdInfo.getId(), "j.wirth", "whoknows", new Date(), "AMS-1");

		assertThat(loanId, is(9494));
		return loanId;
	}
	
	private void testBeanNameAutoProxyCreator_ThrowsAdvice() {
		final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
		KioskService kioskService = applicationContext.getBean("kioskService", KioskService.class);

		String searchTitle = "2 Fast 2 Furious";
		List<DVDInfo> result = kioskService.searchByTitle(searchTitle);

		DVDInfo dvdInfo = result.get(0);
		String message = "MemberException:: invalid credentials";
		
		try {
			kioskService.loanDVD(dvdInfo.getId(), "j.wirth", "wrongPassword", new Date(), "AMS-1");
		} catch (Exception e) {
			assertThat(e, is(notNullValue()));
			assertThat(e.getMessage(), is(message));
		}
	}

	@Test
	public void testExecuteTestClient() {
		TestClient testClient = new TestClient();

		logger.info("-------------------------");

		int loanId = testClient.testBeanNameAutoProxyCreator_BeforeAdvice();
		assertThat(loanId, is(9494));
		logger.info("result: {}", loanId);
		logger.info("-------------------------");

		loanId = testClient.testBeanNameAutoProxyCreator_AfterAdvice();
		assertThat(loanId, is(9494));
		logger.info("result: {}", loanId);
		logger.info("-------------------------");
		
		testClient.testBeanNameAutoProxyCreator_ThrowsAdvice();
	}

} // The End...
