package com.springclass.presentation;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springclass.domain.DVDDetails;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.InvalidDvdIdException;
import com.springclass.service.InventoryManager;
import com.springclass.validation.DVDDetailsValidator;

/**
 * <p/>
 * This component and its source code representation are copyright protected and
 * proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 * <p/>
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Group, Inc.
 * <p/>
 * Copyright (c) 2014 The Trivera Group, LLC. http://www.triveratech.com
 * http://www.triveragroup.com
 * </p>
 *
 * @author The Trivera Group Tech Team.
 */
@Controller
@RequestMapping(value = "/inventory/addDVD")
public class AddDVDController {

    private static final Logger logger = LoggerFactory
            .getLogger(AddDVDController.class);

    @Autowired
    @Qualifier("inventoryManagerImpl")
    protected InventoryManager manager;

    //-------------------------------------------------------------------//
    @Autowired
    private DVDDetailsValidator dvdDetailsValidator;

    //-------------------------------------------------------------------//
    @InitBinder
    public void dataBinder(final WebDataBinder binder) {
    	binder.addValidators(dvdDetailsValidator);
    }

    // TODO: Lab: Update method signature to invoke validation @Valid:
    // TODO: Lab: Add RequestMapping to accept POST RequestMethod:
    // here
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView add(final @ModelAttribute("command") @Valid DVDDetails dvdDetails, final BindingResult result) {

        ModelAndView modelAndView = new ModelAndView("inventory/AddDVD", "command", dvdDetails);

		/*
         * TODO: Lab: use the manager to add the dvd and return to the named view
		 * DVDDetail (or another name you used for the view the displays the
		 * details of a single DVD. Set the model so that the view can render
		 * the newly added dvd.
		 */
        try {

            if (result.hasErrors()) {
            	// return the AddDVD screen for user to correct field errors
            	return new ModelAndView("inventory/AddDVD", "command", dvdDetails);
            }

        	manager.addDvd(dvdDetails);

           // return DVD Details screen for successful adding DVD
        	modelAndView = new ModelAndView("inventory/Detail", "dvd", dvdDetails);

        } catch (InvalidDvdIdException e) {
        	// return the AddDVD screen for user to correct field errors
        	return new ModelAndView("inventory/AddDVD", "command", dvdDetails);
        }

        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm()
            throws Exception {

        logger.info("********************************************* showForm()");

		/*
		 * TODO: Lab: use the manager to add the dvd and return to the named view
		 * DVDDetail (or another name you used for the view the displays the
		 * details of a single DVD. Set the model so that the view can render
		 * the newly added dvd.
		 */
        return new ModelAndView("inventory/AddDVD", "command", new DVDDetails());

    }

    private DVDInfo convertDetailsToInfo(final DVDDetails dvd) {
        DVDInfo info = new DVDInfo();

        info.setId(dvd.getId());
        info.setTitle(dvd.getTitle());
        info.setYearMonthRelease(dvd.getReleaseYear());
        info.setStarring(dvd.getActors());
        info.setUpcNumber(dvd.getUpcNumber());
        return info;
    }


} // The End...