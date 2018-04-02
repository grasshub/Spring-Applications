package com.springclass.features;

import com.springclass.features.AirportLocationDAOSteps;
import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serenity Stories
 */
public class StoryExample extends SerenityStories {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Steps
    AirportLocationDAOSteps steps;

    @Test
    @Pending
    public void placeholder__StoryExample_test() {
        logger.info("-------------------------------------------------");
        logger.info("placeholder");

        // GIVEN
        steps.member_should_have_locations_available(3);

        // WHEN
//        travellerSteps.the_traveller_flies(1_000);

        // THEN
//        travellerSteps.traveller_should_have_a_balance_of(10_100);

    }

}
