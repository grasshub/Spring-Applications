package com.springclass.jdbc.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springclass.dao.DAOException;
import com.springclass.dao.DVDInfoDAO;
import com.springclass.domain.DVDInfo;
import com.springclass.jdbc.support.DvdInfoRowMapper;

/**
 * <p/>
 * CREATE TABLE DVD( DVDCODE VARCHAR(10) NOT NULL PRIMARY KEY, DVD_TITLE_ID
 * VARCHAR(10) NOT NULL, LOCATION_ID VARCHAR(10), ON_HOLD CHAR(1) DEFAULT 'F',
 * CONSTRAINT FK_DVD_DVDTITLE FOREIGN KEY(DVD_TITLE_ID) REFERENCES
 * DVDTITLE(DVD_TITLE_ID))
 * </p>
 * This component and its source code representation are copyright protected and
 * proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Group, Inc.
 *
 * Copyright (c) 2016 The Trivera Group, LLC. http://www.triveratech.com
 * http://www.triveragroup.com
 * </p>
 * 
 * @author The Trivera Group Tech Team.
 */
@Repository
public class DVDInfoDAOImpl extends JdbcDaoSupport implements DVDInfoDAO {

	// TODO: @Autowired the DataSource attribute here:
	@Autowired
	private DataSource dataSource;

	// init()
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	private List<DVDInfo> execute(String sql, Object[] args) {
		List<DVDInfo> result = new ArrayList<>();

		// obtain the Template
		JdbcTemplate template = getJdbcTemplate();

		// Execute the sql statement, using a DvdInfoRowMapper to obtain the
		// results
		result = template.query(sql, args, new DvdInfoRowMapper());

		return Collections.unmodifiableList(result);
	}

	@Override
	public DVDInfo getDVDByID(String dvdId) {

		// String sql = "SELECT * FROM DVD AS d, DVDTITLE AS t WHERE
		// d.DVD_TITLE_ID=? AND d.DVD_TITLE_ID = t.DVD_TITLE_ID";
		String sql = "SELECT * FROM DVD AS d, DVDTITLE AS t WHERE d.DVDCODE=? AND d.DVD_TITLE_ID = t.DVD_TITLE_ID";

		// from (location join airport on((location.AIRPORT_CODE =
		// airport.AIRPORT_CODE))

		Object[] args = new Object[] { dvdId };

		List<DVDInfo> result = execute(sql, args);

		if (result.size() == 1) {
			return result.get(0);
		}

		throw new DAOException(result.size() + " entries found when searching for DVD_Title_ID " + dvdId);
	}

	@Override
	public List<DVDInfo> getDVDsByLocationID(String locationID) {
		String sql = "SELECT * FROM DVD AS d, DVDTITLE AS t WHERE LOCATION_ID = ? AND d.DVD_TITLE_ID = t.DVD_TITLE_ID";
		Object[] args = new Object[] { locationID };
		return execute(sql, args);
	}

	@Override
	public List<DVDInfo> searchDVDs(String title, String locationID) {
		String sql = "SELECT * FROM DVD AS d, DVDTITLE AS t WHERE t.TITLE LIKE '%" + title
				+ "%' AND d.DVD_TITLE_ID = t.DVD_TITLE_ID AND d.LOCATION_ID=?";
		Object[] args = new Object[] { locationID };

		return execute(sql, args);
	}

	@Override
	public void addDvdInfo(DVDInfo info) {
		// String sql = "SELECT * FROM DVD AS d, DVDTITLE AS t WHERE t.TITLE
		// LIKE '%" + title + "%' AND d.DVD_TITLE_ID = t.DVD_TITLE_ID AND
		// d.LOCATION_ID=?";
		// Object[] args = new Object[]{locationID};

		// return execute(sql, args);
	}

}
