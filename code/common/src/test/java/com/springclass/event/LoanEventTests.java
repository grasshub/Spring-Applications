package com.springclass.event;

import com.springclass.configuration.JavaConfig;
import com.springclass.domain.DVDDetails;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.InvalidDvdIdException;
import com.springclass.fixture.DVDInfoFixture;
import com.springclass.service.InventoryManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;

@ContextConfiguration(classes = JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public final class LoanEventTests {

    private final Logger logger = LoggerFactory
            .getLogger(this.getClass());

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private LoanEventPublisher publisher;

    @Autowired
    private LoanEventListener listener;


    @Before
    public void beforeEachTest(){
    }


    //--- Tests -------------------------------------------------------------//

    @Test
    public void testPublishLoanEvent() {
        publisher.publishLoanEvent(12345);

        int result = listener.events.size();
        LoanEvent event = listener.events.get(0);
        logger.info("published Loan event: {}", event);
        assertThat(result, is(1));
        assertThat(event.getLoanId(), is(12345));
    }


} // The End...
