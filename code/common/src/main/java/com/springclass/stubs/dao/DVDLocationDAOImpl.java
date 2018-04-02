package com.springclass.stubs.dao;

import com.springclass.dao.DVDLocationDAO;
import com.springclass.domain.DVDData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
@Component
public class DVDLocationDAOImpl implements DVDLocationDAO {

    @Resource(name = "dvdList")
    private List<DVDData> dvdIDList;

    public String getDVDId(String dvdTitleID, String locationID){

        if(dvdTitleID != null && locationID != null) {
            for (DVDData data : dvdIDList) {
                final String title = data.getDvdTitleID();
                final String location = data.getLocationID();
                if (dvdTitleID.equals(title)
                        && locationID.equals(location)) {
                    return data.getDvdID();
                }
            }
        }
        return null;
    }

} // The End...
