package com.springclass.jdbc.support;

import com.springclass.bo.KioskService;
import com.springclass.configuration.JavaConfig;
import com.springclass.domain.DVDInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Other things I would like to cover here:
 *
 * With the JDBC 2.0 API, you will be able to do the following:
 Scroll forward and backward in a result set or move to a specific row
 Make updates to database tables using methods in the Java programming language instead of using SQL commands
 Send multiple SQL statements to the database as a unit, or batch
 Use the new SQL3 datatypes as column values
 */
@ContextConfiguration(classes = JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)

// Must have External Derby Database installed and running to use this
// profile:
//@ActiveProfiles({"derby"})
//@ActiveProfiles({"embedded"})
public class DvdInfoQueryTests {

    private final Logger logger = LoggerFactory
            .getLogger(DvdInfoQueryTests.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JdbcTemplate template;

    @Autowired
    DvdInfoQuery query;


    //--- UNIT TESTS --------------------------------------------------------//

    @Test
    public void test___MapRow() {

        //Execute the sql statement, using a RowCallbackHandler to obtain the results
        List<DVDInfo> result = query.execute("%fast%");

        assertThat(result.size(), is(87));
    }

    @Test
    public void test___DVDSearch() {
        logger.info("---------------------------------------------------------");

        final String location = "AMS-LC0";

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
        logger.info("---------------------------------------------------------");

    }


} // The End...
