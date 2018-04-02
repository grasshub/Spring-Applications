package com.springclass.aop.advice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springclass.bo.KioskService;
import com.springclass.bo.KioskServiceImpl;
import com.springclass.configuration.AopConfig;
import com.springclass.domain.DVDInfo;

@ContextConfiguration(classes = AopConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BeforeAfterLoggingAdviceTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private KioskService kioskService;

	@Mock
	private KioskService kioskServiceMocked;
	
	@Before
	public void beforeEachTest() {
		MockitoAnnotations.initMocks(this);
	}

	// --- UNIT TESTS --------------------------------------------------------//

	@Test
	public void testBeanNameAutoProxyCreator_BeforeAdvice() {

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
	}

	@Test
	public void testBeanNameAutoProxyCreator_AfterAdvice() {

		String searchTitle = "2 Fast 2 Furious";
		List<DVDInfo> result = kioskService.searchByTitle(searchTitle);

		DVDInfo dvdInfo = result.get(0);
		// kioskService returns -1, but BeforeAfterLoggingAdvice.afterReturning
		// modifies the result.
		int loanId = kioskService.loanDVD(dvdInfo.getId(), "j.wirth", "whoknows", new Date(), "AMS-1");

		assertThat(loanId, is(9494));
	}

	@Test
	public void testBeanNameAutoProxyCreator_AfterThrowing() {
		logger.info("testBeanNameAutoProxyCreator_AfterThrowing");

		String searchTitle = "2 Fast 2 Furious";
		List<DVDInfo> result = kioskService.searchByTitle(searchTitle);

		DVDInfo dvdInfo = result.get(0);

		String message = "MemberException:: invalid credentials";

		// Expectations
		doThrow(new RuntimeException(message)).when(kioskServiceMocked).loanDVD(anyString(), anyString(), anyString(),
				(Date) anyObject(), anyString());

		try {
			Integer loanId = kioskServiceMocked.loanDVD(dvdInfo.getId(), "j.wirth", "wrongPassword", new Date(), "AMS-1");
		} catch (Exception e) {
			assertThat(e, is(notNullValue()));
			assertThat(e.getMessage(), is(message));
		}
		verify(kioskServiceMocked).loanDVD(anyString(), anyString(), anyString(), (Date) anyObject(), anyString());

	}

} // The End...
