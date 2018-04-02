package com.springclass.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * <p/>
 * This component and its source code representation are copyright protected and
 * proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Group, Inc.
 *
 * Copyright (c) 2014 The Trivera Group, LLC. http://www.triveratech.com
 * http://www.triveragroup.com
 * </p>
 * 
 * @author The Trivera Group Tech Team.
 */
@Entity
@Table(name = "MEMBER")
@SecondaryTable(name = "ADDRESS")
public final class Member implements Serializable {

	private static final long serialVersionUID = -8913521793474175255L;

	@Id()
	@Column(name = "MEMBER_ID")
	private String id;

	private String userName;
	private String password;

	@Column(table = "ADDRESS")
	private String street;
	@Column(table = "ADDRESS")
	private String city;
	@Column(table = "ADDRESS")
	private String zipCode;
	@Column(table = "ADDRESS")
	private String country;

	private String ffNumber;
	private String airlineCode;
	private String email;
	// private char status;
	private String fullName;

	private Date memberSinceDate;

	private Boolean active = false;

	public Member() {
		this.active = false;
		// this.status = 'I';
	}

	public Member(String memberID) {
		this.id = memberID;
		this.active = false;
		// this.status = 'I';
	}

	public Member(String memberID, String userName, String password, String street, String city, String zipCode,
			String country, String ffNumber, String airlineCode, String email, char status, String fullName) {
		this.id = memberID;
		this.userName = userName;
		this.password = password;
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
		this.ffNumber = ffNumber;
		this.airlineCode = airlineCode;
		this.email = email;
		// this.status = status;
		this.fullName = fullName;
		this.active = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFfNumber() {
		return ffNumber;
	}

	public void setFfNumber(String ffNumber) {
		this.ffNumber = ffNumber;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// public char getStatus() {
	// return status;
	// }
	//
	// public void setStatus(char status) {
	// this.status = status;
	// }

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getMemberSinceDate() {
		return memberSinceDate;
	}

	public void setMemberSinceDate(Date memberSinceDate) {
		this.memberSinceDate = memberSinceDate;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
