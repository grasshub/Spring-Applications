package com.springclass.fixture;

import com.springclass.domain.DVDData;
import com.springclass.domain.DVDInfo;
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
public class DVDDataFixture {

    private String locationUS = "BUR-1";
    private String locationNL = "AMS-1";
    private String locationFR = "CDG-1";

    public List<DVDData> getDvdList() {

        return new ArrayList<DVDData>() {
            {
                add(new DVDData("12475", "B00005JLT5", locationUS));
                add(new DVDData("12476", "B00005JLZN", locationUS));
                add(new DVDData("12477", "B00005JM2Y", locationUS));
                add(new DVDData("12478", "B00005JM4Z", locationUS));
                add(new DVDData("12479", "B00005JM5B", locationUS));

                add(new DVDData("7572", "B00000F5B5", locationFR));
                add(new DVDData("7573", "B00003CXCZ", locationFR));
                add(new DVDData("7574", "B00005JKC3", locationFR));
                add(new DVDData("7575", "B00005JLQN", locationFR));
                add(new DVDData("7576", "B00005JLRQ", locationFR));

                add(new DVDData("9136", "B00005JLI6", locationNL));
                add(new DVDData("9136", "B00005JLQN", locationNL));
                add(new DVDData("9136", "B00005JLZK", locationNL));
                add(new DVDData("9136", "B00005JLZW", locationNL));
                add(new DVDData("9136", "B00005JM0B", locationNL));
            }
        };
    }

}
