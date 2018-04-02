package com.springclass.dao;

import com.springclass.configuration.JavaConfig;
import com.springclass.dao.DVDLocationDAO;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;


@ContextConfiguration(classes = JavaConfig.class)
//@ContextConfiguration("classpath*:applicationContext-kiosk.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DVDLocationDAOTests {

    private final Logger logger = LoggerFactory
            .getLogger(DVDLocationDAOTests.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private DVDLocationDAO dao;

    @Value("${location.id.us}")
    private String locationUS;
    @Value("${location.id.nl}")
    private String locationNL;
    @Value("${location.id.fr}")
    private String locationFR;


    @Before
    public void beforeEachTest() {

    }

    @Test
    public void testGetDVDID() {
        logger.info("testGetDVDID");

        // "B00000F5B5","CDG-1"
        String dvdTitleID = "B00000F5B5";
        String locationID = locationFR;

        String result = dao.getDVDId(dvdTitleID, locationID);

        assertThat(result, is("7572"));
    }


    @Test
    public void testGetDVDID_NullDVDTitleID() {
        logger.info("testGetDVDID");

        String dvdTitleID = null;
        String locationID = locationFR;

        String result = dao.getDVDId(dvdTitleID, locationID);

        assertThat(result, is(nullValue()));
    }


    @Test
    public void testGetDVDID_NullLocationID() {
        logger.info("testGetDVDID");

        String dvdTitleID = "B00000F5B5";
        String locationID = null;

        String result = dao.getDVDId(dvdTitleID, locationID);

        assertThat(result, is(nullValue()));
    }


    @Test
    public void testGetDVDID_NoResults() {
        logger.info("testGetDVDID");

        String dvdTitleID = "";
        String locationID = "";

        String result = dao.getDVDId(dvdTitleID, locationID);

        assertThat(result, is(nullValue()));
    }

} // The End...
