package com.trivera;

/**
 * <p>
 * This component and its source code representation are copyright protected and
 * proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera
 * Technologies
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of Trivera
 * Technologies, LLC
 *
 * Copyright (c) 2016 Trivera Technologies, LLC. http://www.triveratech.com
 * </p>
 * 
 * @author Trivera Technologies Tech Team.
 * 
 * 
 * Create a semi-colon separated String where each entry in the string is exactly 40 characters long
 * 
 */
public class StringAssembler {

	private StringBuilder state = new StringBuilder();

	/**
	 * Returns the value that has been assembled
	 * 
	 * @return A semi-colon separated String with all the values
	 */
	public String toString() {
		return state.toString();
	}

	/**
	 * Add a value to be assembled 
	 * 
	 * - When adding a value all leading and trailing spaces will be stripped from the value 
	 * 
	 * - The length of each value that is added to the comma-seperated list and will be exactly 40
	 * characters long. When the value that added is more that 40 characters,
	 * only the first 40 characters will be added. When the value that added is
	 * less than 40 characters, the value is appeneded with dots until it is
	 * exactly 40 characters 
	 * 
	 * - When the value added is a null pointer, nothing
	 * is added - When the value added contains a semi-colon, the semi-colon is
	 * replaced by a # character 
	 * 
	 * - The resulting String will not start or end
	 * with a semi-colon
	 * 
	 * @param value
	 *            The String to be added
	 */
	public void add(String value) {
		if (value == null)
			return;

		value = value.replaceAll(";", "#");

		StringBuilder work = new StringBuilder(value.trim());

		if (work.length() > 40) {
			work.delete(40, work.length());
		} else {
			while (work.length() < 40) {
				work.append(".");
			}
		}

		if (state.length() > 0) {
			state.append(";");
		}
		state.append(work.toString());

	}
}
