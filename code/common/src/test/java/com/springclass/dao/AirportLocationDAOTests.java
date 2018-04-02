package com.springclass.dao;

import com.springclass.configuration.JavaConfig;
import com.springclass.domain.AirportLocation;
import com.springclass.stubs.dao.AirportLocationDAOImpl;
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
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;


@ContextConfiguration(classes = JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AirportLocationDAOTests {

    private final Logger logger = LoggerFactory
            .getLogger(this.getClass());

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private AirportLocationDAO dao;

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
    public void testGetLocations() {
        logger.info("testGetLocations");

        List<AirportLocation> result = dao.getLocations();

        assertThat(result.size(), is(3));
        assertThat(result.get(0).getAirportName(), is("Burbank Glendale Pasadena Airport"));
    }

    @Test
    public void testGetLocationByID() {

        String location = locationFR;

        AirportLocation result = dao.getLocationByID(location);

        assertThat(result.getAirportName(), is("Charles De Gaulle"));
    }


    @Test(expected = DAOException.class)
    public void testGetLocationByID_NullId() {

        String location = null;

        AirportLocation result = dao.getLocationByID(location);
    }

    @Test(expected = DAOException.class)
    public void testGetLocationByID_DAOException() {

        String location = "foo";

        AirportLocation result = dao.getLocationByID(location);
    }

    @Test
    public void testDAOException() {
        DAOException result = new DAOException();

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testDAOException_message() {
        DAOException result = new DAOException("test dao exception");

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testDAOException_message_throwable() {
        DAOException result = new DAOException("test dao exception", new Exception("test exception"));

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testDAOException_throwable() {
        DAOException result = new DAOException(new Exception("test exception"));

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void test__init() {

        assertThat(dao instanceof AirportLocationDAOImpl, is(true));
        AirportLocationDAOImpl daoImpl = (AirportLocationDAOImpl)dao;
        daoImpl.init();
    }

    @Test
    public void test__destroy() {

        assertThat(dao instanceof AirportLocationDAOImpl, is(true));
        AirportLocationDAOImpl daoImpl = (AirportLocationDAOImpl)dao;
        daoImpl.destroy();
    }


} // The End...
