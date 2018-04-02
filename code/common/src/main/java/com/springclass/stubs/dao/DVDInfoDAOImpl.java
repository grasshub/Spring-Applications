package com.springclass.stubs.dao;

import com.springclass.dao.DVDInfoDAO;
import com.springclass.domain.DVDInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * <p/>
 * CREATE TABLE DVD(
 * DVDCODE VARCHAR(10) NOT NULL PRIMARY KEY,
 * DVD_TITLE_ID VARCHAR(10) NOT NULL,
 * LOCATION_ID VARCHAR(10),
 * ON_HOLD CHAR(1) DEFAULT 'F',
 * CONSTRAINT FK_DVD_DVDTITLE FOREIGN KEY(DVD_TITLE_ID)
 * REFERENCES DVDTITLE(DVD_TITLE_ID))
 * </p>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
@Component
public class DVDInfoDAOImpl implements DVDInfoDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "titles")
    private List<DVDInfo> titles;

    @Override
    public List<DVDInfo> getDVDsByLocationID(final String locationID) {
        List<DVDInfo> result = new ArrayList<>();

        if (locationID == null) {
            return result;
        }

        for (DVDInfo current : titles) {
            final String location = current.getLocationID();
            if (locationID.equals(location)) {
                result.add(current);
            }
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public DVDInfo getDVDByID(final String dvdId) {
        if (dvdId == null) {
            return null;
        }

        for (DVDInfo title : titles) {
            final String id = title.getId();
            if (dvdId.equals(id)) {
                return title;
            }
        }
        return null;
    }

    @Override
    public List<DVDInfo> searchDVDs(final String title, final String locationID) {
        List<DVDInfo> result = new ArrayList<>();
        for (DVDInfo dvdTitle : titles) {
            final String currentTitle = dvdTitle.getTitle();
            final String currentLocation = dvdTitle.getLocationID();

            if (currentTitle.contains(title) &&
                    currentLocation.equals(locationID)) {
                result.add(dvdTitle);
            }
        }
        return Collections.unmodifiableList(result);
    }

    @Override
    public void addDvdInfo(final DVDInfo info) {
        titles.add(info);
    }

} // The End...
