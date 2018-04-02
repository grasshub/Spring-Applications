package com.springclass.presentation;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springclass.domain.DVDInfo;
import com.springclass.service.InventoryManager;

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
//-------------------------------------------------------------------//
// TODO: Lab: Add the @Controller annotation before the class declaration of ViewDVDController

@Controller
@RequestMapping(value = "/inventory")
public class ViewDVDController {

    private static final Logger logger = LoggerFactory
            .getLogger(ViewDVDController.class);


    //-------------------------------------------------------------------//
    // TODO: Lab: Use @Autowired annotation above the declaration of the InventoryManager.
    @Autowired
    @Qualifier("inventoryManagerImpl")
    private InventoryManager manager;


    public InventoryManager getManager() {
        return manager;
    }

    public void setManager(InventoryManager manager) {
        this.manager = manager;
    }

    //-------------------------------------------------------------------//
    /* TODO: Lab: in the first task, add a viewAll method that displays all DVDs
      it uses the InventoryManager to get all the dvds, and passes that on a as model
      to the view named DVDlist (letting the ViewResolver take care of the rest)
    */
    @RequestMapping(value="viewAll", method=RequestMethod.GET)
    public ModelAndView viewAll() throws Exception {
    	
    	String location = "LAS-LC0";
    	Collection<DVDInfo> result = getManager().findAllByLocation(location);
    	logger.info("returning list of DVDs ", result.size() );
    	return new ModelAndView("inventory/ListAll", "catalog", result);
    }


    //-------------------------------------------------------------------//
    /* TODO: Lab: in the second task, add a  method that displays the details of a single DVD
      it uses the InventoryManager to get all the details of dvd identified with the request parameter
      dvdID. Next it passes that on a as model
      to view named  DVDDetail (letting the ViewResolver take care of the rest)
     */
    @RequestMapping(value="details", method=RequestMethod.GET)
    public ModelAndView details(@RequestParam(value = "dvdID", defaultValue = "1") String dvdID) throws Exception {
    	
    	DVDInfo result = getManager().getDvdInfo(dvdID);
    	logger.info("returning DVD ", result.getTitle());
    	return new ModelAndView("inventory/Detail", "dvd", result);
    }

} // The End...
