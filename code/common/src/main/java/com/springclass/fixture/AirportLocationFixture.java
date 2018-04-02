package com.springclass.fixture;

import com.springclass.domain.AirportLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * CREATE VIEW AIRPORT_LOCATIONS (
 * LOCATION_ID,
 * AIRPORT_CODE,
 * AIRPORT_NAME,
 * CITY,
 * COUNTRY,
 * TERMINAL,
 * LOCATION_INFORMATION) AS SELECT DISTINCT
 * LOCATION.LOCATION_ID,
 * LOCATION.AIRPORT_CODE,
 * AIRPORT.AIRPORT_NAME,
 * AIRPORT.CITY,
 * AIRPORT.COUNTRY,
 * LOCATION.TERMINAL,
 * LOCATION.LOCATION_INFORMATION
 * FROM LOCATION AS LOCATION INNER JOIN AIRPORT AS AIRPORT
 * ON LOCATION.AIRPORT_CODE = AIRPORT.AIRPORT_CODE
 * </p>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 * <p/>
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 * <p/>
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 *
 * @author The Trivera Group Tech Team.
 */
@Component
public class AirportLocationFixture {

    private final Logger logger = LoggerFactory
            .getLogger(this.getClass());

    private String locationUS = "BUR-1";
    private String locationNL = "AMS-1";
    private String locationFR = "CDG-1";


    public List<AirportLocation> getLocations() {

        return new ArrayList<AirportLocation>() {
            {
                add(new AirportLocation(locationUS, "BUR", "Burbank Glendale Pasadena Airport", "Burbank, CA", "USA", "Terminal-1", "Inside the Business Lounge"));
                add(new AirportLocation(locationFR, "CDG", "Charles De Gaulle", "Paris", "France", "Terminal-1", "At the airport gift-shop"));
                add(new AirportLocation(locationNL, "AMS", "Schiphol", "Amsterdam", "Netherlands", "Main Terminal", "Next to Delta check-in"));
            }
        };
    }
}
