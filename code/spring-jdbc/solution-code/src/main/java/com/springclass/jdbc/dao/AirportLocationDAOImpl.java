package com.springclass.jdbc.dao;

import com.springclass.dao.AirportLocationDAO;
import com.springclass.dao.DAOException;
import com.springclass.domain.AirportLocation;

import com.springclass.jdbc.support.AirportLocationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2016 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
@Repository
@Transactional
public class AirportLocationDAOImpl extends JdbcDaoSupport implements AirportLocationDAO {

    @Autowired
    private DataSource dataSource;


    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }
    @PostConstruct
    public void destroy(){}


    @Override
    public List<AirportLocation> getLocations() {
        String sql = "SELECT * FROM AIRPORT_LOCATIONS";

        //Execute the sql statement, using a RowMapper to obtain the results.
        List<AirportLocation> result = getJdbcTemplate().query(sql, new AirportLocationRowMapper());



        /*final List<AirportLocation> result = new ArrayList<>();

        //Obtain the JdbcTemplate
        JdbcTemplate template = getJdbcTemplate();
//continued on next page


//Execute the sql statement, using a RowCallbackHandler to handle the results
        template.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet)
                    throws SQLException {
                AirportLocation location = populate(resultSet);
                result.add(location);
            }
        });*/

        return Collections.unmodifiableList(result);
    }

    @Override
    public AirportLocation getLocationByID(final String locationId) {

        String sql = "SELECT * FROM AIRPORT_LOCATIONS WHERE LOCATION_ID = ?";
        Object[] queryParams = new Object[]{locationId};
        List<AirportLocation> result = null;


        //Execute the sql statement, using a AirportLocationRowMapper to obtain the results.
        result = getJdbcTemplate().query(sql, queryParams, new AirportLocationRowMapper());


//        if (result.size() > 1) {
//            throw new DAOException("Multiple Locations found for LocationID " + locationId);
//
//        }
        if (result.size() == 0) {
            throw new DAOException("No Locations found for LocationID " + locationId);
        }
        return result.get(0);
    }
} // The end...
