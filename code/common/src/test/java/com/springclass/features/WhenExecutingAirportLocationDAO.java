package com.springclass.features;

import com.springclass.configuration.JavaConfig;
import com.springclass.dao.AirportLocationDAO;
import com.springclass.features.AirportLocationDAOSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

/*@Narrative(text={"Narrative:" +
        "  In order to find DVDs that I would like to rent" +
        "  As a potential customer" +
        "  I want to be able to search for DVD rental locations"})*/
@RunWith(SerenityRunner.class)
//@Concurrent(threads="4x")
//@UseTestDataFrom(value="testdata/status-levels.csv")
//@UseTestDataFrom(value="test-data/simple-semicolon-data.csv", separator=';')
@ContextConfiguration(classes = JavaConfig.class)
public class WhenExecutingAirportLocationDAO {

    private final Logger logger = LoggerFactory
            .getLogger(this.getClass());

//    @Rule
//    public SpringIntegrationMethodRule springIntegration = new SpringIntegrationMethodRule();

    @Steps
    private AirportLocationDAOSteps steps;

    @Test
    @Title("human readable title for reports")
    public void search_for_all_locations_when_jUnit() {

        // GIVEN
        steps.member_should_have_locations_available(3);

        // WHEN
//        travellerSteps.the_traveller_flies(1000);

        // THEN
//        travellerSteps.traveller_should_have_a_balance_of(10100);

    }

} // The End...
