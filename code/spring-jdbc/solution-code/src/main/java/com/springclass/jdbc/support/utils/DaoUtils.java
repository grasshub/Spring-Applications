package com.springclass.jdbc.support.utils;

import com.springclass.domain.AirportLocation;
import com.springclass.domain.DVDInfo;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DaoUtils {

    public static DVDInfo mapRowToDvdInfo(ResultSet rs)throws SQLException {
        // dvd (DVDCODE,DVD_TITLE_ID,LOCATION_ID,ON_HOLD)
        DVDInfo info = new DVDInfo();

        info.setId(rs.getString("DVDCODE"));
//        info.setId(rs.getString("DVD_TITLE_ID"));

        info.setDirector(rs.getString("DIRECTOR"));
        info.setEncoding(rs.getString("ENCODING"));
        info.setFormat(rs.getString("FORMAT"));

        info.setLocationID(rs.getString("LOCATION_ID"));
        info.setRated(rs.getString("RATED"));
        info.setStarring(rs.getString("STARRING"));
        info.setStudio(rs.getString("STUDIO"));
        info.setTitle(rs.getString("TITLE"));
        info.setYearMonthRelease(rs.getString("YEARMONTH_RELEASE"));
        info.setOnHold(rs.getString("ON_HOLD"));
        return info;
    }

    public static AirportLocation mapRowToAirportLocation(ResultSet rs)throws SQLException {

        AirportLocation location = new AirportLocation();
        location.setAirportCode(rs.getString("AIRPORT_CODE"));
        location.setAirportName(rs.getString("AIRPORT_NAME"));
        location.setCity(rs.getString("CITY"));
        location.setCountry(rs.getString("COUNTRY"));
        location.setLocationID(rs.getString("LOCATION_ID"));
        location.setLocationInformation(rs.getString("LOCATION_INFORMATION"));
        location.setTerminal(rs.getString("TERMINAL"));

        return location;

    }

}

