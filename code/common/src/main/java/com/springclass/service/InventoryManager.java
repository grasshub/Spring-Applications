package com.springclass.service;

import com.springclass.domain.*;
import com.springclass.domain.InvalidDvdIdException;

import java.util.Collection;
import java.util.Map;

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
public interface InventoryManager {
    // This class has been completed for you

    /**
     * Adds a new DVD to the inventory
     *
     * @param details the DVD to add
     * @throws InvalidDvdIdException when the dvdId is already in use
     * deprecated Use DVDInfo instead.
     */
    void addDvd(DVDDetails details) throws InvalidDvdIdException;

    /**
     * Obtains details for a single DVD
     *
     * @param dvdID
     * @return a All the DVD Details or null when
     * the dvdID is invalid
     * deprecated Use DVDInfo instead.
     */
    DVDDetails getDetails(String dvdID);


    Map<String, DVDDetails> getCatalog();

    void setCatalog(Map<String, DVDDetails> catalog);

    //--- DVDInfo below -----------------------------------------------------//
    //--- DVDInfo below -----------------------------------------------------//
    //--- DVDInfo below -----------------------------------------------------//
    //--- DVDInfo below -----------------------------------------------------//

//    Map<String, DVDInfo> getCatalogInfo();
//
//    void setCatalogInfo(Map<String, DVDInfo> catalogInfo);


    /**
     * Adds a new DVD to the inventory
     *
     * @param info the DVD to add
     * @throws InvalidDvdIdException when the dvdId is already in use
     */
    void addDvdInfo(DVDInfo info) throws InvalidDvdIdException;

    /**
     * Obtains info for a single DVD
     *
     * @param dvdID
     * @return a All the DVD Details or null when
     * the dvdID is invalid
     */
    DVDInfo getDvdInfo(String dvdID);

    /**
     * Returns all DVD
     *
     * @return a DVDInfo for each DVD (only contains the id and the title)
     */
    Collection<DVDInfo> findAll();

    /**
     * Returns all DVD
     *
     * @return a DVDInfo for each DVD (only contains the id and the title)
     */
    Collection<DVDInfo> findAllByLocation(String locationID);
}

