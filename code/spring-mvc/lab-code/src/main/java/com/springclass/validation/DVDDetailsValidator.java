package com.springclass.validation;

import com.springclass.domain.DVDDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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
@Component
public class DVDDetailsValidator implements Validator {
	
	private final int minYear = 1900;
    private final int maxYear = 2017;

	@Override
	public boolean supports(Class<?> clazz) {
		return DVDDetails.class.isAssignableFrom(clazz);
	}

	private boolean isIntNumber(final String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		DVDDetails details = (DVDDetails) target;

		if (details == null) {
			errors.rejectValue("id", "error.not-specified", "Value-required");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "", "id is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "title is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actors", "", "actors is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "releaseYear", "", "releaseYear is empty");
		
		if (!isIntNumber(details.getId())) {
			errors.rejectValue("id", "error.integer", "not integer");
		}
		
		if (!isIntNumber(details.getReleaseYear())) {
			errors.rejectValue("releaseYear", "error.integer", "not integer");
		}
		else {
			int year = Integer.parseInt(details.getReleaseYear());
	
			if (year < minYear) {
				errors.rejectValue("releaseYear", "", "release year too long ago");
			} else if (year > maxYear) {
				errors.rejectValue("releaseYear", "", "release year in the future");
			}
		}
	}

} // The End...
