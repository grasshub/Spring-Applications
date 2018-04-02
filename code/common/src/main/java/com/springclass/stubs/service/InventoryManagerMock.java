package com.springclass.stubs.service;

import com.springclass.domain.DVDDetails;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.InvalidDvdIdException;
import com.springclass.service.InventoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
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
//@Component
public class InventoryManagerMock implements InventoryManager {

    private static final Logger logger = LoggerFactory
            .getLogger(InventoryManagerMock.class);

    // This class has been completed for you

    // for the stubs implementation store DVDDetails as apposed to DVD objects
    private Map<String, DVDDetails> catalog = new HashMap<>();

    @Override
    public Map<String, DVDDetails> getCatalog() {
        return catalog;
    }

    @Override
    public void setCatalog(final Map<String, DVDDetails> catalog) {
        this.catalog = catalog;
    }

    @Override
    public void addDvd(final DVDDetails details) throws InvalidDvdIdException {
        String id = details.getId();
        if (catalog.containsKey(id))
            throw new InvalidDvdIdException(id + " is in use");
        this.getCatalog().put(id, details);
    }

    @Override
    @Cacheable("dvdCache")
    public DVDDetails getDetails(final String dvdID) {
        return getCatalog().get(dvdID);
    }


    @Override
    public void addDvdInfo(final DVDInfo info) throws InvalidDvdIdException {
        // Stub to allow adding all the DVDInfo objects, into the DVDDetails collection
        DVDDetails details = new DVDDetails();
        details.setId(info.getId());
        details.setTitle(info.getTitle());
        details.setReleaseYear(info.getYearMonthRelease());
        details.setActors(info.getStarring());

        details.setUpcNumber(info.getUpcNumber());
        details.setUpcNumber(new Random().nextInt());

        this.getCatalog().put(details.getId(), details);
    }

    @Override
    @Cacheable("dvdCache")
    public DVDInfo getDvdInfo(final String id) {
        DVDDetails details = this.getCatalog().get(id);
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
        Collection<DVDDetails> collection = this.getCatalog().values();
        List<DVDInfo> result = new ArrayList<>(collection.size());
        for (DVDDetails details : collection) {
            result.add(new DVDInfo(details.getId()));
        }
        return result;
    }


    /**
     * TODO: Need to make DVDDetails have a LocationID!!!!
     * @param locationID
     * @return
     */
    @Override
    public Collection<DVDInfo> findAllByLocation(final String locationID) {
        logger.debug("*******************************************************");
        logger.info("InventoryManagerMock:: findAllByLocation({})", locationID);

        Collection<DVDDetails> collection = this.getCatalog().values();
        List<DVDInfo> result = new ArrayList<>(collection.size());
        for (DVDDetails details : collection) {
            result.add(new DVDInfo(details.getId()));
        }
        return result;
    }

} // The End...
