package com.springclass.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.springclass.configuration.JavaConfig;
import com.springclass.messenger.Messenger;
import com.springclass.messenger.MessengerLooper;

@ContextConfiguration(classes=JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCase {
	
	private final Logger logger = LoggerFactory.getLogger(TestCase.class);
	
	private final String MOCKED_GREETING = "Mocked Greeting";
	private final int TEN = 10;
	
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	@InjectMocks
	private MessengerLooper messengerLooper;
	@Mock
	private Messenger mockedMessenger;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testMockedMessenger() {
		
		logger.info("testMockedMessenger");
		
		// Stubbing (Set mock behavior)
		when(mockedMessenger.greet()).thenReturn(MOCKED_GREETING);
		
		messengerLooper = applicationContext.getBean(MessengerLooper.class);
		ReflectionTestUtils.setField(messengerLooper, "messenger", mockedMessenger);
		
		String result = messengerLooper.doIt();
		
		assertThat(result, is(MOCKED_GREETING));
		verify(mockedMessenger, times(TEN)).greet();
		
		logger.info("MockedMessenger result is {}", result);	
	}
}
