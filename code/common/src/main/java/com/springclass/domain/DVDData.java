package com.springclass.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public final class DVDData implements Serializable {
    String dvdID;
    String dvdTitleID;
    String locationID;

    public DVDData(String dvdID, String dvdTitleID, String locationID) {
        this.dvdID = dvdID;
        this.dvdTitleID = dvdTitleID;
        this.locationID = locationID;
    }

    public String getDvdID() {
        return dvdID;
    }

    public void setDvdID(String dvdID) {
        this.dvdID = dvdID;
    }

    public String getDvdTitleID() {
        return dvdTitleID;
    }

    public void setDvdTitleID(String dvdTitleID) {
        this.dvdTitleID = dvdTitleID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
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
