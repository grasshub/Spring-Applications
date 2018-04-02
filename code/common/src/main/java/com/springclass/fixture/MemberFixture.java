package com.springclass.fixture;

import com.springclass.domain.AirportLocation;
import com.springclass.domain.Member;
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
public class MemberFixture {

    public List<Member> getMembers() {

        return new ArrayList<Member>() {
            {
//                add(new Member("93941", "j.smith", "hiking", "w 49th St.", "New York", "10019", "U.S.A.", "1010-695-3405", "AAE", "jsmith@xs4all.com", null));
//                add(new Member("93947", "j.wirth", "whoknows", "121 Grandview Rd", "Braintree", "2184", "U.S.A.", "1003-345-8754", "ATN", "jwirth@tiscali.com", null));
                add(new Member("93941", "j.smith", "hiking", "w 49th St.", "New York", "10019", "U.S.A.", "1010-695-3405", "AAE", "jsmith@xs4all.com", 'A', null));
                add(new Member("93947", "j.wirth", "whoknows", "121 Grandview Rd", "Braintree", "2184", "U.S.A.", "1003-345-8754", "ATN", "jwirth@tiscali.com", 'A', null));
            }
        };
    }
}
