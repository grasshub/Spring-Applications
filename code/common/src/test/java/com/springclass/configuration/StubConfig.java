package com.springclass.configuration;

import com.springclass.dao.*;
import com.springclass.domain.AirportLocation;
import com.springclass.domain.DVDData;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.Member;
import com.springclass.fixture.AirportLocationFixture;
import com.springclass.fixture.DVDDataFixture;
import com.springclass.fixture.DVDInfoFixture;
import com.springclass.fixture.MemberFixture;
import com.springclass.stubs.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StubConfig {


    /*
     * Define the five (mocked) DAO implemention classes
     * all these classes reside in the com.springclass.dao package
     */

//    @Bean
    public AirportLocationDAO airportLocationDAOBean(){
        return new AirportLocationDAOImpl();
    }

//    @Bean
    public DVDInfoDAO dvdInfoDAOBean(){
        return new DVDInfoDAOImpl();
    }

//    @Bean
    public DVDLocationDAO dvdLocationDAOBean(){
        return new DVDLocationDAOImpl();
    }

//    @Bean
    public LoanDAO loanDAOBean(){
        return new LoanDAOImpl();
    }

//    @Bean
    public MemberDAO memberDAOBean(){
        return new MemberDAOImpl();
    }



    //--- These are STUBs, not MOCKs ----------------------------------------//
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

}
