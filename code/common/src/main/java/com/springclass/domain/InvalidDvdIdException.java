package com.springclass.domain;

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
public class InvalidDvdIdException extends Exception {

    public InvalidDvdIdException() {
    }

    public InvalidDvdIdException(String message) {
        super(message);
    }

    public InvalidDvdIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDvdIdException(Throwable cause) {
        super(cause);
    }
}
