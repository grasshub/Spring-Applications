package com.springclass.fixture;

import com.springclass.domain.DVDDetails;
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
public class DVDInfoFixture {

    private String locationUS = "BUR-1";
    private String locationNL = "AMS-1";
    private String locationFR = "CDG-1";

    public List<DVDInfo> getTitles() {

        return new ArrayList<DVDInfo>() {
            {
                add(new DVDInfo("B00005JLT5", "Roman Polanski", "Region-2", "Widescreen, Dolby, DTS Surround Sound", "R (Restricted)", "Universal Studios", "200301", "The Pianist (Widescreen Edition)", "Adrien Brody - Thomas Kretschmann - Frank Finlay", locationUS));
                add(new DVDInfo("B00005JLZN", "Alan Parker", "Region-0", "Color, Closed-captioned, Widescreen, Dolby", "R (Restricted)", "Universal Studios", "200302", "The Life of David Gale (Widescreen Edition)", "Kevin Spacey - Kate Winslet - Laura Linney", locationUS));
                add(new DVDInfo("B00005JM2Y", "Gurinder Chadha", "Region-2", "Color, Closed-captioned, Widescreen, Dolby", "PG-13 (Parental Guidance Suggested)", "Twentieth Century Fox Home Video", "200308", "Bend It Like Beckham (Widescreen Edition)", "Parminder Nagra - Keira Knightley - Jonathan Rhys-Meyers", locationUS));

                add(new DVDInfo("B00005JM4Z", "John Singleton", "Region-1", "Widescreen, Color, Widescreen, Dolby", "PG-13 (Parental Guidance Suggested)", "Universal Studios", "200306", "2 Fast 2 Furious (Widescreen Edition)", "Paul Walker - Tyrese - Cole Hauser", locationUS));

                add(new DVDInfo("B00005JM5B", "Stephen Norrington", "Region-0", "Color, Closed-captioned, Widescreen, Dolby", "PG-13 (Parental Guidance Suggested)", "Twentieth Century Fox Home Video", "200307", "The League of Extraordinary Gentlemen (Widescreen Edition)", "Sean Connery - Peta Wilson - Tony Curran - Stuart Townsend - Shane West - Jason Flemyng", locationUS));

                add(new DVDInfo("B00000F5B5", "\u00c9mile Gaudreault", "Region-1", "Widescreen, Dolby, DTS Surround Sound", "R (Restricted)", "Columbia Tristar Hom", "200301", "Mambo Italiano", "Luke Kirby - Ginette Reno - Paul Sorvino - Peter Miller (XII)", locationFR));
                add(new DVDInfo("B00003CXCZ", "Hayao Miyazaki", "Region-0", "Animated, Color", "G (General Audience)", "Twentieth Century Fox Home Video", "200408", "My Neighbor Totoro", "Hitoshi Takagi - Noriko Hidaka", locationFR));
                add(new DVDInfo("B00005JKC3", "Ang Lee", "Region-0", "Color, Closed-captioned, Widescreen, Dolby", "PG-13 (Parental Guidance Suggested)", "Universal Studios", "200306", "Hulk (Widescreen Special Edition)", "Eric Bana - Jennifer Connelly - Sam Elliott", locationFR));
                add(new DVDInfo("B00005JLQN", "Joel Schumacher", "Region-2", "Color, Closed-captioned, Widescreen, Dolby", "R (Restricted)", "Twentieth Century Fox Home Video", "200304", "Phone Booth", "Colin Farrell - Kiefer Sutherland - Forest Whitaker - Radha Mitchell - Katie Holmes", locationFR));
                add(new DVDInfo("B00005JLRQ", "David Dobkin", "Region-0", "Color, Closed-captioned, Widescreen, Dolby", "PG-13 (Parental Guidance Suggested)", "Buena Vista Home Vid", "200302", "Shanghai Knights", "Jackie Chan - Owen Wilson - Fann Wong", locationFR));

                add(new DVDInfo("B00005JLI6", "Gary Hardwick", "Region-1", "Color, Closed-captioned, Widescreen, Dolby", "R (Restricted)", "Universal Studios", "200302", "Deliver Us From Eva (Widescreen Edition)", "LL Cool J - Gabrielle Union - Essence Atkins", locationNL));
                add(new DVDInfo("B00005JLQN", "Joel Schumacher", "Region-2", "Color, Closed-captioned, Widescreen, Dolby", "R (Restricted)", "Twentieth Century Fox Home Video", "200304", "Phone Booth", "Colin Farrell - Kiefer Sutherland - Forest Whitaker - Radha Mitchell - Katie Holmes", locationNL));
                add(new DVDInfo("B00005JLZK", "David R. Ellis", "Region-1", "Widescreen, Dolby, DTS Surround Sound", "R (Restricted)", "New Line Home Entertainment", "200301", "Final Destination 2 (Infinifilm Edition)", "A.J. Cook - Ali Larter - Tony Todd", locationNL));
                add(new DVDInfo("B00005JLZW", "Peyton Reed", "Region-1", "Color, Closed-captioned, Widescreen, Dolby", "PG-13 (Parental Guidance Suggested)", "Twentieth Century Fox Home Video", "200305", "Down with Love (Widescreen Edition)", "Ren\u00e9e Zellweger - Ewan McGregor - David Hyde Pierce", locationNL));
                add(new DVDInfo("B00005JM0B", "Jonathan Mostow", "Region-0", "Color, Closed-captioned, Widescreen, Dolby", "R (Restricted)", "Warner Home Video", "200307", "Terminator 3 - Rise of the Machines (Widescreen Edition)", "Arnold Schwarzenegger - Nick Stahl - Kristanna Loken", locationNL));
            }
        };
    }

    public List<DVDDetails> getDVDDetails() {

        return new ArrayList<DVDDetails>() {
            {
                add(new DVDDetails("100","24 - Season Five (2005)","2006","Kiefer Sutherland, Dennis Haysbert, James Badge Dale, William Devane, Alberta Watson", 1));
                add(new DVDDetails("102", "The Pianist (Widescreen Edition)", "2003", "Adrien Brody - Thomas Kretschmann - Frank Finlay", 2));
                add(new DVDDetails("103", "Office Space (1999)", "1999", "Ron Livingston, Jennifer Aniston, David Herman", 3));
            }
        };
    }
}
