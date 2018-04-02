package com.springclass.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.springclass.bo.KioskService;
import com.springclass.bo.KioskServiceImpl;
import com.springclass.dao.AirportLocationDAO;
import com.springclass.dao.DVDInfoDAO;
import com.springclass.dao.DVDLocationDAO;
import com.springclass.dao.LoanDAO;
import com.springclass.dao.MemberDAO;

@Import({StubConfig.class})
public class JavaConfig {

    /*
     * Set the value for the dvd location to be "BUR-1"
     */
	@Value("BUR-1")
    private String location;

    /*
     * Autowire the 5 DAO's into this configuration class:
     * airportLocationDAO, dvdInfoDAO, dvdLocationDAO, loanDAO, and memberDAO
     */
    @Autowired
    AirportLocationDAO airportLocationDAO;
    @Autowired
    DVDInfoDAO dvdInfoDAO;
    @Autowired
    DVDLocationDAO dvdLocationDAO;
    @Autowired
    LoanDAO loanDAO;
    @Autowired
    MemberDAO memberDAO;

    /*
     *  Complete the KioskService definition.
     *  The KioskService requires a single parameter in the constructor of
     *  type AirportLocation which can be obtained by invoking
     *  the (non-static) getLocationByID method on the AirportLocationDAO

     *  In addition, the KioskService contains four properties that must be set
     *  dvdInfoDAO, dvdLocationDAO, loanDAO and memberDAO
     */

    @Bean
    public KioskService kioskService(){
        KioskServiceImpl kioskService = new KioskServiceImpl(
                airportLocationDAO.getLocationByID(
                        location
                )
        );

        kioskService.setDvdInfoDAO(dvdInfoDAO);
        kioskService.setDvdLocationDAO(dvdLocationDAO);
        kioskService.setLoanDAO(loanDAO);
        kioskService.setMemberDAO(memberDAO);

        return kioskService;
    }

} // the End...
