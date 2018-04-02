package com.springclass.configuration;

import com.springclass.dao.*;
import com.springclass.domain.*;
import com.springclass.fixture.AirportLocationFixture;
import com.springclass.fixture.DVDDataFixture;
import com.springclass.fixture.DVDInfoFixture;
import com.springclass.fixture.MemberFixture;
import com.springclass.stubs.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StubConfig {

    /*
     * Define the five (mocked) DAO implementation classes
     * all these classes reside in the 'com.springclass.stubs.dao'
     * package.
     */


    /**
     * AirportLocationDAO has been completed for you.
     * @return
     */
    @Bean
    public AirportLocationDAO airportLocationDAO(){
        return new AirportLocationDAOImpl();
    }

    // Next, complete DVDInfoDAO, DVDLocationDAO, LoanDAO, MemberDAO
    
    /**
     * DVDInfoDAO has been completed for you.
     * @return
     */
    @Bean
    public DVDInfoDAO DVDInfoDAO(){
        return new DVDInfoDAOImpl();
    }
    
    /**
     * DVDLocationDAO has been completed for you.
     * @return
     */
    @Bean
    public DVDLocationDAO DVDLocationDAO(){
        return new DVDLocationDAOImpl();
    }
    
    /**
     * LoanDAO has been completed for you.
     * @return
     */
    @Bean
    public LoanDAO LoanDAO(){
        return new LoanDAOImpl();
    }
    
    /**
     * MemberDAO has been completed for you.
     * @return
     */
    @Bean
    public MemberDAO MemberDAO(){
        return new MemberDAOImpl();
    }
    

    //--- These are STUBs, not MOCKs ---//
    //--- These are used to provide the data for the above DAO beans. ---//

    @Bean  //Returned Type of Bean   // name of bean
    public AirportLocationFixture airportLocationFixture(){
        return new AirportLocationFixture();
    }

    @Bean
    public List<AirportLocation> airportLocations(){
        return airportLocationFixture().getLocations();
    }

    @Bean
    public DVDInfoFixture dvdInfoFixture(){
        return new DVDInfoFixture();
    }

    @Bean
    public List<DVDInfo> titles(){
        return dvdInfoFixture().getTitles();
    }

    @Bean
    public DVDDataFixture dvdDataFixture(){
        return new DVDDataFixture();
    }

    @Bean
    public List<DVDData> dvdList(){
        return dvdDataFixture().getDvdList();
    }

    @Bean
    public MemberFixture memberFixture(){
        return new MemberFixture();
    }

    @Bean
    public List<Member> members(){
        return memberFixture().getMembers();
    }


} // the end...
