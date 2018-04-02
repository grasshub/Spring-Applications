package com.springclass.service;

import com.springclass.configuration.JavaConfig;
import com.springclass.domain.DVDDetails;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.InvalidDvdIdException;
import com.springclass.fixture.DVDInfoFixture;
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

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@ContextConfiguration(classes = JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class InventoryManagerTests {

    private final Logger logger = LoggerFactory
            .getLogger(this.getClass());

    @Autowired
    private InventoryManager manager;

    @Value("${location.id.us}")
    private String locationUS;
    @Value("${location.id.nl}")
    private String locationNL;

    DVDInfoFixture fixture = new DVDInfoFixture();

    @Before
    public void beforeEachTest(){
        manager.setCatalog(new HashMap<String, DVDDetails>());
    }


    //--- Tests -------------------------------------------------------------//

    @Test
    public void testGetSetCatalog() throws InvalidDvdIdException {
        Map<String, DVDDetails> result = manager.getCatalog();
        assertThat(result.size(), is(0));

        manager.setCatalog(
                new HashMap<String, DVDDetails>(){{
                    put("1", fixture.getDVDDetails().get(0));
                    put("2", fixture.getDVDDetails().get(1));
                }}
        );

        result = manager.getCatalog();
        assertThat(result.size(), is(2));
    }

    @Test
    public void testAddDvdInfo() throws InvalidDvdIdException {

        for(DVDInfo info: fixture.getTitles()) {
            manager.addDvdInfo(info);
        }

        DVDInfo result = manager.getDvdInfo("B00005JLT5");
        int upcNumber1 = result.getUpcNumber();

        result = manager.getDvdInfo("B00005JLT5");
        int upcNumber2 = result.getUpcNumber();

        assertThat(upcNumber1, is(not(upcNumber2)));

        logger.info("result.getTitle() {}", result.getTitle());
        assertThat(result.getTitle(), is("The Pianist (Widescreen Edition)"));
    }

    @Test
    public void testAddDvdDetails() throws InvalidDvdIdException {

        manager.addDvd(fixture.getDVDDetails().get(0));

        DVDInfo result = manager.getDvdInfo("100");

        logger.info("result.getTitle() {}", result.getTitle());
        assertThat(result.getTitle(), is("24 - Season Five (2005)"));
    }

    @Test(expected = InvalidDvdIdException.class)
    public void testAddDvdDetails_Failure() throws InvalidDvdIdException {

        manager.addDvd(fixture.getDVDDetails().get(0));
        manager.addDvd(fixture.getDVDDetails().get(0));
        fail();
    }


    @Test
    public void testGetDvdDetails() throws InvalidDvdIdException {

        manager.addDvd(fixture.getDVDDetails().get(0));
        manager.addDvd(fixture.getDVDDetails().get(1));

        DVDDetails result = manager.getDetails("100");

        logger.info("result.getTitle() {}", result.getTitle());
        assertThat(result.getTitle(), is("24 - Season Five (2005)"));
    }

    @Test
    public void testFindAll() throws InvalidDvdIdException {

        Collection<DVDInfo> result = manager.findAll();

        logger.info("result.size() {}", result.size());
        assertThat(result.size(), is(0));

        for(DVDInfo info: fixture.getTitles()) {
            manager.addDvdInfo(info);
        }

        result = manager.findAll();
        assertThat(result.size(), is(14));

    }

    @Test
    public void testFindAllByLocation() throws InvalidDvdIdException {

        Collection<DVDInfo> result = manager.findAllByLocation(locationUS);

        logger.info("result.size() {}", result.size());
        assertThat(result.size(), is(0));

        for(DVDInfo info: fixture.getTitles()) {
            manager.addDvdInfo(info);
        }

        result = manager.findAllByLocation(locationNL);
        assertThat(result.size(), is(14));

    }

} // The End...
