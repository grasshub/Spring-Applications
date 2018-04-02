package com.springclass.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springclass.bo.KioskService;
import com.springclass.configuration.JavaConfig;
import com.springclass.domain.DVDInfo;

/**
 * <p/>
 * This component and its source code representation are copyright protected and
 * proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Group, Inc.
 *
 * Copyright (c) 2016 The Trivera Group, LLC. http://www.triveratech.com
 * http://www.triveragroup.com
 * </p>
 * 
 * @author The Trivera Group Tech Team.
 */
@RunWith(JUnit4.class)
public final class TestClient {

	private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

	public static void main(String args[]) {
		TestClient testClient = new TestClient();
		testClient.test();
	}

	public String test() {

		final String location = "AMS-LC0";

		final StringBuilder sb = new StringBuilder();
		sb.append(location);

		final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				JavaConfig.class);

		KioskService kioskService = applicationContext.getBean(KioskService.class);

		Collection<DVDInfo> result = kioskService.getDVDsByLocationID(location);
		assertThat(result.size(), is(124));
		logger.info("number of DVDInfo results on Location: {}", result.size());

		List<DVDInfo> searchByTitle = kioskService.searchByTitle("x");
		assertThat(searchByTitle.size(), is(4));
		logger.info("number of DVD with letter 'x' in title {}", searchByTitle.size());

		DVDInfo dvdToLoan = searchByTitle.get(0);
		logger.info("Adding loan for dvd {}", dvdToLoan);
		Integer loadID = kioskService.loanDVD(dvdToLoan.getId(), "Fred", "secret", new Date(), "AMS-LC0");
		assertThat(loadID, notNullValue());
		logger.info("Added loan for dvd {} with Loan ID {}", dvdToLoan, loadID);

		// implicit shutdown hook
		applicationContext.registerShutdownHook();

		// or explicit close method
		applicationContext.close();

		return sb.toString();

	}

	@Test
	public void testExecuteTestClient() {
		TestClient testClient = new TestClient();
		String result = testClient.test();

		// assertThat(result, is("2 Fast 2 Furious: 9494"));
		logger.info("TestClient result: {}", result);

	}

} // The End...
