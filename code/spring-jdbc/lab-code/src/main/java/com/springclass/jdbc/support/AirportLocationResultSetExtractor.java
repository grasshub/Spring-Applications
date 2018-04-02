package com.springclass.jdbc.support;

import com.springclass.domain.AirportLocation;
import com.springclass.jdbc.support.utils.DaoUtils;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @see RowCallbackHandler (depricated) and this is the new implementation
 */
public class AirportLocationResultSetExtractor implements ResultSetExtractor<AirportLocation> {

    public AirportLocation extractData(ResultSet rs) throws SQLException {
        return DaoUtils.mapRowToAirportLocation(rs);
    }
} // The End...
