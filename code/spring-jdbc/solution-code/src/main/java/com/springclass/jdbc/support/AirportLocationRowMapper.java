package com.springclass.jdbc.support;

import com.springclass.domain.AirportLocation;
import com.springclass.jdbc.support.utils.DaoUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportLocationRowMapper implements RowMapper<AirportLocation> {

    public AirportLocation mapRow(ResultSet rs, int rowNum)throws SQLException {
        return DaoUtils.mapRowToAirportLocation(rs);
    }
} // The End...
