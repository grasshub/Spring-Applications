package com.springclass.cache;

import com.springclass.domain.DVDDetails;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.InvalidDvdIdException;
import com.springclass.service.InventoryManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p/>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 * <p/>
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 * <p/>
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 *
 * @author The Trivera Group Tech Team.
 */
@Service
@Profile("other")
public class InventoryManagerCachableMock implements InventoryManager {

    //private final Logger logger = LoggerFactory.getLogger(InventoryManagerCachableMock.class);

    // This class has been completed for you

    // for the stubs implementation store DVDDetails as apposed to DVD objects
    Map<String, DVDDetails> catalog = new HashMap<String, DVDDetails>();

    public Map<String, DVDDetails> getCatalog() {
        return catalog;
    }

    public void setCatalog(Map<String, DVDDetails> catalog) {
        this.catalog = catalog;
    }

    @Override
    public void addDvd(DVDDetails details) throws InvalidDvdIdException {
        String id = details.getId();
        if (catalog.containsKey(id))
            throw new InvalidDvdIdException(id + " is in use");
        catalog.put(id, details);
    }

    @Override
    @Cacheable("dvdCache")
    public DVDDetails getDetails(String dvdID) {
        return catalog.get(dvdID);
    }

    //--- DVDInfo below -----------------------------------------------------//
    //--- DVDInfo below -----------------------------------------------------//
    //--- DVDInfo below -----------------------------------------------------//
    //--- DVDInfo below -----------------------------------------------------//

    @Override
    public void addDvdInfo(DVDInfo info) throws InvalidDvdIdException {
        // Stub to allow adding all the DVDInfo objects, into the DVDDetails collection
        DVDDetails details = new DVDDetails();
        details.setId(info.getId());
        details.setTitle(info.getTitle());
        details.setReleaseYear(info.getYearMonthRelease());
        details.setActors(info.getStarring());

        details.setUpcNumber(info.getUpcNumber());
        details.setUpcNumber(new Random().nextInt());

        catalog.put(details.getId(), details);
    }

    @Override
    @Cacheable("dvdCache")
    public DVDInfo getDvdInfo(String dvdId) {
        DVDDetails details = catalog.get(dvdId);
        DVDInfo result = new DVDInfo();

        result.setId(details.getId());
        result.setTitle(details.getTitle());
        result.setYearMonthRelease(details.getReleaseYear());
        result.setStarring(details.getActors());

        result.setUpcNumber(details.getUpcNumber());
        result.setUpcNumber(new Random().nextInt());

        return result;
    }

    @Override
    public Collection<DVDInfo> findAll() {
        Collection<DVDDetails> collection = catalog.values();
        List<DVDInfo> result = new ArrayList<DVDInfo>(collection.size());
        for (DVDDetails details : collection) {
            result.add(new DVDInfo(details.getId()));
        }
        return result;
    }

    @Override
    public Collection<DVDInfo> findAllByLocation(String locationID) {
        Collection<DVDDetails> collection = catalog.values();
        List<DVDInfo> result = new ArrayList<DVDInfo>(collection.size());
        for (DVDDetails details : collection) {
            result.add(new DVDInfo(details.getId()));
        }
        return result;
    }
}
