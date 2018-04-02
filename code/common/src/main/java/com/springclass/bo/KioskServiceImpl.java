package com.springclass.bo;

import com.springclass.dao.*;
import com.springclass.domain.AirportLocation;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.Loan;
import com.springclass.domain.MemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

/**
 * <p/>
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

@Component // (name = "kioskServiceImpl")
           // ("kioskService")
public class KioskServiceImpl implements KioskService {

    private AirportLocation airportLocation;

    @Autowired
    private AirportLocationDAO airportLocationDAO;

    @Autowired
    private DVDInfoDAO dvdInfoDAO;

    @Autowired
    private DVDLocationDAO dvdLocationDAO;

    @Autowired
    private LoanDAO loanDAO;

    @Autowired
    MemberDAO memberDAO;

    public KioskServiceImpl() {}

    public KioskServiceImpl(final AirportLocation airportLocation) {
        this.airportLocation = airportLocation;
    }


    @Override
    public List<DVDInfo> getDVDsByLocationID(final String locationId) {
        return dvdInfoDAO.getDVDsByLocationID(locationId);
    }

    @Override
    public List<DVDInfo> searchByTitle(String title) {
        return dvdInfoDAO.searchDVDs(title, airportLocation.getLocationID());
    }

    @Override
    public Integer loanDVD(final String dvdTitleID,
                       final String userName,
                       final String password,
                       final Date returnDate,
                       final String returnLocationID) {

        final String dvdCode = dvdLocationDAO.getDVDId(dvdTitleID, airportLocation.getLocationID());
        String memberID = null;

        try {
            memberID = memberDAO.getMemberID(userName, password);
        } catch (MemberException e) {
            return -1;
        }


        Loan loan = new Loan();
        loan.setDvdCode(dvdCode);
        loan.setExpectedReturnDate(returnDate);
        loan.setExpectedReturnLocation(returnLocationID);
        loan.setFromLocation(airportLocation.getLocationID());
        loan.setLoanDate(new Date());
        loan.setMemberID(memberID);

        return loanDAO.addLoan(loan);
    }

    //---- Setter & Getter --------------------------------------------------//

    public AirportLocation getAirportLocation() {
        return airportLocation;
    }

    public void setAirportLocation(AirportLocation airportLocation) {
        this.airportLocation = airportLocation;
    }

    public DVDInfoDAO getDvdInfoDAO() {
        return dvdInfoDAO;
    }

    public void setDvdInfoDAO(DVDInfoDAO dvdInfoDAO) {
        this.dvdInfoDAO = dvdInfoDAO;
    }

    public DVDLocationDAO getDvdLocationDAO() {
        return dvdLocationDAO;
    }

    public void setDvdLocationDAO(DVDLocationDAO dvdLocationDAO) {
        this.dvdLocationDAO = dvdLocationDAO;
    }

    public LoanDAO getLoanDAO() {
        return loanDAO;
    }

    public void setLoanDAO(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }

    public MemberDAO getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

} // The End...
