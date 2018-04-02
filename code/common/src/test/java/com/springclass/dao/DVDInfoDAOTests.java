package com.springclass.dao;

//Hamcrest
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.core.IsNull.*;

import com.springclass.configuration.JavaConfig;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.InvalidDvdIdException;
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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;


@ContextConfiguration(classes = JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DVDInfoDAOTests {

    private final Logger logger = LoggerFactory
            .getLogger(DVDInfoDAOTests.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private DVDInfoDAO dao;

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
    public void testGetDVDsByLocationID() {
        String locationId = locationUS;

        List<DVDInfo> result = dao.getDVDsByLocationID(locationId);

        logger.info("DVD's by locationId '{}': {}", locationId, result);
        assertThat(result.size(), is(5));
    }

    @Test
    public void testGetDVDsByLocationID_Null_LocationID() {
        String locationId = null;

        List<DVDInfo> result = dao.getDVDsByLocationID(locationId);

        logger.info("DVD's by locationId '{}': {}", locationId, result);
        assertThat(result.size(), is(0));
    }

    @Test
    public void testGetDVDsByLocationID_NoResults() {
        String locationId = "FooBarLocation";

        List<DVDInfo> result = dao.getDVDsByLocationID(locationId);

        logger.info("DVD's by locationId '{}': {}", locationId, result);
        assertThat(result.size(), is(0));
    }


    @Test
    public void testGetDVDByID() {
        logger.info("testGetDVDsByLocationID");
        String dvdId = "B00005JLT5";

        DVDInfo result = dao.getDVDByID(dvdId);

        logger.info("DVD Id '{}'", result);
        assertThat(result.getId(), is(dvdId));
    }

    @Test
    public void testGetDVDByID_NullId() {
        logger.info("testGetDVDByID_NullId");
        String dvdId = null;

        DVDInfo result = dao.getDVDByID(dvdId);

        logger.info("DVD Id '{}'", result);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void testGetDVDByID_NoResults() {
        String dvdId = "FooID";

        DVDInfo result = dao.getDVDByID(dvdId);

        logger.info("DVD Id '{}'", result);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void testAddDvdInfo() throws InvalidDvdIdException{
        String dvdId = "FooID";

        DVDInfo dvd = new DVDInfo();
        dao.addDvdInfo(dvd);

//        DVDInfo result = dao.getDVDByID(dvdId);
//
//        logger.info("DVD Id '{}'", result);
//        assertThat(result, is(nullValue()));
    }


} // The End...
