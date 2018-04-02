package com.springclass.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p/>
 * CREATE VIEW AVAILABLE_DVDINFO_LOCATION (
 * DVD_TITLE_ID,
 * DIRECTOR,
 * ENCODING,
 * FORMAT,
 * RATED,
 * STUDIO,
 * YEARMONTH_RELEASE,
 * TITLE,STARRING,
 * LOCATION_ID)
 * AS SELECT DISTINCT
 * TITLE.DVD_TITLE_ID,
 * TITLE.DIRECTOR,
 * TITLE.ENCODING,
 * TITLE.FORMAT,
 * TITLE.RATED,
 * TITLE.STUDIO,
 * TITLE.YEARMONTH_RELEASE,
 * TITLE.TITLE,
 * TITLE.STARRING,
 * DVD.LOCATION_ID
 * FROM DVDTITLE AS TITLE INNER JOIN DVD AS DVD ON TITLE.DVD_TITLE_ID = DVD.DVD_TITLE_ID WHERE DVD.ON_HOLD='F'
 * </p>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
@Entity
@Table
public final class DVDInfo implements Serializable{

    private String id;
    private String director;
    private String encoding;
    private String format;
    private String rated;
    private String studio;
    private String yearMonthRelease;
    private String title;
    private String starring;
    private String locationID;
    private String onHold;

    private int upcNumber;

    public DVDInfo() {
    }

    public DVDInfo(String id) {
        this.id = id;
    }

    public DVDInfo(String id, String director, String encoding, String format, String rated, String studio, String yearMonthRelease, String title, String starring, String locationID) {
        this.id = id;
        this.director = director;
        this.encoding = encoding;
        this.format = format;
        this.rated = rated;
        this.studio = studio;
        this.yearMonthRelease = yearMonthRelease;
        this.title = title;
        this.starring = starring;
        this.locationID = locationID;
        this.onHold = "N";
        this.upcNumber = 1234;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getYearMonthRelease() {
        return yearMonthRelease;
    }

    public void setYearMonthRelease(String yearMonthRelease) {
        this.yearMonthRelease = yearMonthRelease;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getOnHold() {
        return onHold;
    }

    public void setOnHold(String onHold) {
        this.onHold = onHold;
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
