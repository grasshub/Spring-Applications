package com.springclass.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.springclass.bo.KioskService;
import com.springclass.bo.KioskServiceImpl;
import com.springclass.dao.AirportLocationDAO;
import com.springclass.dao.DVDInfoDAO;
import com.springclass.dao.DVDLocationDAO;
import com.springclass.dao.LoanDAO;
import com.springclass.dao.MemberDAO;

@Configuration
@ComponentScan(basePackages = {"com.springclass.configuration", "com.springclass.cache"})
@PropertySource(value={"classpath:test.properties"})
public class JavaConfig {

    /*
    Create a String attribute for the location US and NL,
    and assign the value placeholders from the property file.
     */
	@Value("${location.id.us}")
	private String locationUS;
	
	@Value("${location.id.nl}")
	private String locationNL;

    @Autowired
    @Qualifier("airportLocationDAOBean")
    private AirportLocationDAO airportLocationDAO;

    @Autowired
    @Qualifier("dvdInfoDAOBean")
    private DVDInfoDAO dvdInfoDAO;

    @Autowired
    @Qualifier("dvdLocationDAOBean")
    private DVDLocationDAO dvdLocationDAO;

    @Autowired
    @Qualifier("loanDAOBean")
    private LoanDAO loanDAO;

    @Autowired
    @Qualifier("memberDAOBean")
    MemberDAO memberDAO;


    /*
        Complete the KioskService definition.
        The KioskService requires a single parameter in the constructor of
        type AirportLocation which can be obtained by invoking
        the (non-static) getLocationByID method on the AirportLocationDAO

        In addition, the KioskService contains four properties that must be set
        dvdInfoDAO, dvdLocationDAO, loanDAO and memberDAO

        Make each bean a Profile bean
    */
    @Profile("US")
    @Bean
    public KioskService kioskServiceUS(){
    	KioskServiceImpl kioskService = new KioskServiceImpl(airportLocationDAO.getLocationByID(locationUS));

        kioskService.setDvdInfoDAO(dvdInfoDAO);
        kioskService.setDvdLocationDAO(dvdLocationDAO);
        kioskService.setLoanDAO(loanDAO);
        kioskService.setMemberDAO(memberDAO);

        return kioskService;
    }


    @Profile("NL")
    @Bean
    public KioskService kioskServiceNL(){
        KioskServiceImpl kioskService = new KioskServiceImpl(airportLocationDAO.getLocationByID(locationNL));

        kioskService.setDvdInfoDAO(dvdInfoDAO);
        kioskService.setDvdLocationDAO(dvdLocationDAO);
        kioskService.setLoanDAO(loanDAO);
        kioskService.setMemberDAO(memberDAO);

        return kioskService;
    }

    /**
     * Note: If you want to use @PropertySource, you must create a static
     * PropertySourcesPlaceholderConfigurer with the @Bean as seen here.
     * @return
     * @throws java.io.IOException
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer pspc() throws IOException {
    	
    	PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
    	return pspc;
    }

} // the End...
