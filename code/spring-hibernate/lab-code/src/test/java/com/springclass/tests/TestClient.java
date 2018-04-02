package com.springclass.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springclass.configuration.JavaConfig;
import com.springclass.dao.MemberDAO;
import com.springclass.model.Member;

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
 * Copyright (c) 2014 The Trivera Group, LLC. http://www.triveratech.com
 * http://www.triveragroup.com
 * </p>
 * 
 * @author The Trivera Group Tech Team.
 */
@RunWith(JUnit4.class)
public final class TestClient {

	private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

	public void test() {

		final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

		final MemberDAO dao = applicationContext.getBean(MemberDAO.class);

		logger.info("\n\n***Adding Member****\n\n");

		Member member = new Member("1234", "mickknutson", "paraglide", "2204 Fen Drive", "Park City", "84060", "U.S.A.",
				"1010-695-3405", "AAE", "mickknutson@triveratech.com", 'A', null);

		dao.addMember(member);

		logger.info("\n\n***Get 'all' Members****\n\n");
		List<Member> result = dao.getAllMembers();
		assertThat(result.size(), is(1));

		logger.info("\n\n***Deleting Member****\n\n");
		dao.deleteMember(member);
		
		logger.info("\n\n***Get 'all' Members****\n\n");
		List<Member> memembers = dao.getAllMembers();
		assertThat(memembers.size(), is(0));
		
		((ConfigurableApplicationContext)applicationContext).close();
	}

	@Test
	public void testExecuteTestClient() {
		TestClient testClient = new TestClient();
		testClient.test();

	}

}
