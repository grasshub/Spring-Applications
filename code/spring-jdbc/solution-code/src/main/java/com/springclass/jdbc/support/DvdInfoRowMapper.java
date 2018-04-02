package com.springclass.jdbc.support;

import com.springclass.domain.AirportLocation;
import com.springclass.domain.DVDInfo;
import com.springclass.jdbc.support.utils.DaoUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DvdInfoRowMapper implements RowMapper<DVDInfo> {

    public DVDInfo mapRow(ResultSet rs, int rowNum)throws SQLException {
        return DaoUtils.mapRowToDvdInfo(rs);
    }
} // The End...
