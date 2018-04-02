package com.springclass.service;

import com.springclass.dao.DVDInfoDAO;
import com.springclass.domain.DVDDetails;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.InvalidDvdIdException;
import com.springclass.stubs.service.InventoryManagerMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * TODO: would be nice to have a dvdUpdate, and dvdDelete method
 */
@Service
public final class InventoryManagerImpl implements InventoryManager {
    // This class has been completed for you


    private static final Logger logger = LoggerFactory
            .getLogger(InventoryManagerImpl.class);

    @Autowired
    private DVDInfoDAO dao;

    // for the stubs implementation store DVDDetails as apposed to DVD objects
    private Map<String, DVDDetails> catalog = new HashMap<>();


    public Map<String, DVDDetails> getCatalog() {
        return catalog;
    }

    public void setCatalog(final Map<String, DVDDetails> catalog) {
        this.catalog = catalog;
    }

    /**
     * @param details the DVD to add
     * @throws InvalidDvdIdException
     * deprecated Use DVDInfo instead.
     */
    @Override
    public void addDvd(final DVDDetails details) throws InvalidDvdIdException {
        // TODO: Add to dao
        String id = details.getId();
        if (catalog.containsKey(id))
            throw new InvalidDvdIdException(id + " is in use");
        catalog.put(id, details);
    }

    /**
     *
     * @param dvdID
     * @return
     * deprecated Use getDvdInfo instead.
     */
    @Override
    public DVDDetails getDetails(final String dvdID) {
        DVDInfo info = getDvdInfo(dvdID);
        DVDDetails details = new DVDDetails();
        details.setId(info.getId());
        details.setTitle(info.getTitle());
        details.setReleaseYear(info.getYearMonthRelease());
        details.setActors(info.getStarring());

        details.setUpcNumber(info.getUpcNumber());
        details.setUpcNumber(new Random().nextInt());
        return details;
    }

    @Override
    public void addDvdInfo(final DVDInfo info) throws InvalidDvdIdException {
        dao.addDvdInfo(info);
    }

    @Override
    public DVDInfo getDvdInfo(final String id) {
        return dao.getDVDByID(id);
    }

    @Override
    public Collection<DVDInfo> findAll() {

        return findAllByLocation("");
    }

    @Override
    public Collection<DVDInfo> findAllByLocation(final String locationID) {

        logger.info("*******************************************************");
        logger.info("*******************************************************");
        logger.info("*******************************************************");
        logger.info("findAllByLocation({})", locationID);
        return dao.getDVDsByLocationID(locationID);
    }

} // The End...
