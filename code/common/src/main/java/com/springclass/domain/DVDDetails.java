package com.springclass.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
@Entity
@Table
public final class DVDDetails implements Serializable {
    // This class has been completed for you
    private String id;

//    @javax.validation.constraints.NotNull
    private String title;
    private String releaseYear;

    private String starring;

    private String actors;
    private int upcNumber;


    public DVDDetails(){}

    public DVDDetails(String id, String title, String releaseYear, String actors, int upcNumber) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.actors = actors;
        this.upcNumber = upcNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * fixme: added this to bridge DVDDetails, ad DVDInfo objects.
     * @return
     */
    public String getYearMonthRelease() {
        return releaseYear;
    }

    public void setYearMonthRelease(String yearMonthRelease) {
        setReleaseYear(yearMonthRelease);
    }

    /**
     * fixme: added this to bridge DVDDetails, ad DVDInfo objects.
     * @return
     */
    public String getStarring() {
        return actors;
    }

    public void setStarring(String starring) {
        setActors(starring);
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getUpcNumber() {
        return upcNumber;
    }

    public void setUpcNumber(int upcNumber) {
        this.upcNumber = upcNumber;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
