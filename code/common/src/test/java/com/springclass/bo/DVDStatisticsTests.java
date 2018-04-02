package com.springclass.bo;

//Hamcrest
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.core.IsNull.*;

import com.springclass.configuration.JavaConfig;
import com.springclass.dao.DVDInfoDAO;
import com.springclass.domain.DVDInfo;
import com.springclass.fixture.DVDInfoFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.fail;

@ContextConfiguration(classes = JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DVDStatisticsTests {

    private final Logger logger = LoggerFactory
            .getLogger(this.getClass());

    @Autowired
    private DVDStatistics dvdStatistics;

    @Autowired
    private DVDInfoFixture fixture;

    //-----------------------------------------------------------------------//

    @Before
    public void beforeEachTest() {
//        dvdStatistics = new DVDStatisticsImpl();
    }

    @After
    public void afterEachTest() {
//        dvdStatistics = null;
    }

    //--- UNIT TESTS --------------------------------------------------------//

    @Test
    public void testAddDvd() {

        List<DVDInfo> infoList = fixture.getTitles();

        dvdStatistics.addData(infoList);
        Map<String, Integer> result = dvdStatistics.getStatistics();

        assertThat(result.size(), is(14));


        if (result.size() == 0) {
            logger.info("Error, the DVD '2 Fast 2 Furious' should be available at location 'BUR-2'");
            fail();
        }

//        DVDInfo dvdInfo = result.get(0);
//
//        int loandID = kioskService.loanDVD(dvdInfo.getId(), "j.wirth", "whoknows", new Date(), "AMS-1");
//        logger.info("*******************************************************");
//        logger.info("Loan added loan id: {}", loandID);
//        logger.info("*******************************************************");
//
//
//        assertThat(result.size(), is(1));
//        assertThat(loandID, is(9494));

    }

    @Test
    public void testAddData() {

        List<DVDInfo> infoList = fixture.getTitles();

        dvdStatistics.addData(infoList);
        dvdStatistics.addData(infoList);

        Map<String, Integer> result = dvdStatistics.getStatistics();

        logger.info("result.size() {}", result.size());
        assertThat(result.size(), is(14));
    }

    @Test
    public void test__getInstance() {

        DVDStatistics result = DVDStatisticsImpl.getInstance();
        DVDStatistics expected = DVDStatisticsImpl.getInstance();

        assertThat(result, is(sameInstance(expected)));
    }

    @Test
    public void testGetStatistics() {
        dvdStatistics.addData(fixture.getTitles());

        Map<String, Integer> result = dvdStatistics.getStatistics();

        logger.info("result.size() {}", result.size());
        assertThat(result.size(), is(14));
    }

    @Test
    public void testGetStatistics_empty_list() {
        dvdStatistics.addData(new ArrayList<DVDInfo>());

        Map<String, Integer> result = dvdStatistics.getStatistics();

        logger.info("result.size() {}", result.size());
        assertThat(result.size(), is(0));
    }

} // The End...
