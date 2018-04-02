package com.springclass.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

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
public class ExceptionController {

    private static final Logger logger = LoggerFactory
            .getLogger(ExceptionController.class);

    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Data already present")
    @ExceptionHandler(SQLException.class)
    public ModelAndView dataConflict(final  SQLException ex) {
        logger.error("Caught SQLException: {}", ex.getMessage(), ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.setViewName("my_sql_error");
        return mav;
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(final IOException ex) {
        logger.error("Exception: {}", ex.getMessage(), ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.setViewName("my_io_error");
        return mav;
    }



} // The End...
