package com.springclass.jdbc.support;

import com.springclass.domain.DVDInfo;
import com.springclass.jdbc.support.utils.DaoUtils;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DvdInfoQuery extends MappingSqlQuery<DVDInfo> {

private static String SQL = "SELECT * FROM dvdtitle WHERE TITLE LIKE ?";

    public DvdInfoQuery(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter("pattern", Types.VARCHAR));
    }

    protected DVDInfo mapRow(ResultSet rs, int rowNum)throws SQLException {
        return DaoUtils.mapRowToDvdInfo(rs);
    }

} // THe End...
