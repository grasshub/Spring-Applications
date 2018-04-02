package com.springclass.jdbc.dao.query;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * <p/>
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

public class LoanInsertQuery extends SqlUpdate {

    public LoanInsertQuery(DataSource ds) {
        setDataSource(ds);
        String sql = "INSERT INTO LOAN(MEMBER_ID,DVDCODE,FROM_LOCATION,LOAN_DATE,EXPECTED_RETURN_LOCATION,EXPECTED_RETURN_DATE) VALUES(?,?,?,?,?,?)";
        setSql(sql);
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.DATE));
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.DATE));
        setReturnGeneratedKeys(true);
        compile();
    }
}
