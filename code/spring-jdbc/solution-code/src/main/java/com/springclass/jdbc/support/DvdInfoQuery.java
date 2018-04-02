package com.springclass.jdbc.support;

import com.springclass.domain.DVDInfo;
import com.springclass.jdbc.support.utils.DaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Component
@Transactional
public class DvdInfoQuery extends MappingSqlQuery<DVDInfo> {

    private static String SQL = "SELECT * FROM DVD AS d, DVDTITLE AS t WHERE t.TITLE LIKE ? AND d.DVD_TITLE_ID = t.DVD_TITLE_ID";

    @Autowired
    public DvdInfoQuery(DataSource dataSource) {
        super(dataSource, SQL);
        declareParameter(new SqlParameter("pattern", Types.VARCHAR));
    }

    protected DVDInfo mapRow(ResultSet resultSet, int rowNum)throws SQLException {
        return DaoUtils.mapRowToDvdInfo(resultSet);
    }

} // The End...
