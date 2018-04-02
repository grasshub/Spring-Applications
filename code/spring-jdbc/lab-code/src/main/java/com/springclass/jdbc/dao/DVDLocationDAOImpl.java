package com.springclass.jdbc.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springclass.dao.DAOException;
import com.springclass.dao.DVDLocationDAO;

/**
 * <p/>
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
public class DVDLocationDAOImpl extends JdbcDaoSupport implements DVDLocationDAO {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public String getDVDId(String dvdTitleID, String locationID) {

		String sql = "SELECT DVDCODE FROM DVD WHERE DVD_TITLE_ID=? AND LOCATION_ID=?";

		Object[] args = new Object[] { dvdTitleID, locationID };
		
		try {
			
			String dvdID = getJdbcTemplate().queryForObject(sql, args, String.class);
			return dvdID;
			
		} catch (IncorrectResultSizeDataAccessException e) {
			if (e.getActualSize() == 0) {
				
				throw new DAOException("No DVD found with dvdID!");
			}
			
			throw new DAOException("Multiple DVD found with dvdID!");
		}

	}

}
