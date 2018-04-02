package com.springclass.features;

import com.springclass.configuration.JavaConfig;
import com.springclass.dao.AirportLocationDAO;
import com.springclass.domain.AirportLocation;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
//import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.CoreMatchers.startsWith;

/**
 * This is a JBehave test, but the support comes from the serenity libraries.
 */
@ContextConfiguration(classes = JavaConfig.class)
public class AirportLocationDAOSteps {

    private final Logger logger = LoggerFactory
            .getLogger(this.getClass());

    @Autowired
    private AirportLocationDAO dao;

//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"cats"},
//                {"dogs"},
//                {"ferrets"},
//                {"rabbits"},
//                {"canaries"}
//        });
//    }


//    @Step("The traveller should have {0} locations available")
    @Given("The member should have {0} locations available jUnit")
    public void member_should_have_locations_available(final int numberOfLocations) {
        List<AirportLocation> result = dao.getLocations();
        assertThat(result.size(), is(numberOfLocations));
    }

    @Then("the dvd rental location for $id is $location")
    public void the_dvd_rental_location_for_id(String id, String location) {
        logger.info("-------------------------------------------------");
        logger.info("the_dvd_rental_location_for_id");

//        Serenity.takeScreenshot();

        AirportLocation result = dao.getLocationByID(id);

        assertThat(result.getAirportName(), is(location));
    }


    @Given("The member should have $numberOfLocations locations available story")
    public void given_member_should_have_locations_available_story(int numberOfLocations) {
        logger.info("given_member_should_have_locations_available: {}", numberOfLocations);

        this.member_should_have_locations_available(numberOfLocations);

    }


} // The End...
