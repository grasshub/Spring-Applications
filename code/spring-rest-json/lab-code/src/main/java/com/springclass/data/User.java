package com.springclass.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2016 Trivera Technologies, LLC.
 * http://www.triveratech.com
 * </p>
 * @author Trivera Technologies Tech Team.
 */

@XmlRootElement(name="user")
public class User {

	public User() {
	}

	private String fullName;

	private String userName;

	private String street;

	private String zipcode;

	private String city;

	private Integer id;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String newFullName) {
		fullName = newFullName;
	}

	public void setStreet(String newStreet) {
		street = newStreet;
	}

	public String getStreet() {
		return street;
	}

	public void setZipcode(String newZipcode) {
		zipcode = newZipcode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setCity(String newCity) {
		city = newCity;
	}

	public String getCity() {
		return city;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer newId) {
		this.id = newId;
	}
}
