package com.springclass.stubs.dao;

import com.springclass.dao.DAOException;
import com.springclass.domain.AirportLocation;
import com.springclass.dao.AirportLocationDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Collections;

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
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
@Component
public class AirportLocationDAOImpl implements AirportLocationDAO {

    @Resource(name = "airportLocations")
    private List<AirportLocation> airportLocations;


    public List<AirportLocation> getLocations() {
        return Collections.unmodifiableList(airportLocations);
    }

    public AirportLocation getLocationByID(String locationId) throws DAOException {
        for (AirportLocation airportLocation : airportLocations) {
            final String currentLocationId = airportLocation.getLocationID();
            if (currentLocationId.equals(locationId)) {
                return airportLocation;
            }
        }
        throw new DAOException("No Locations found");
    }

    public void init(){
        // do some init custom work...
    }

    public void destroy(){
        // do some destroy custom work...
    }
} // the end...
