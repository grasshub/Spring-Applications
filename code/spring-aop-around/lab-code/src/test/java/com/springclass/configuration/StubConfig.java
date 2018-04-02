package com.springclass.configuration;

import com.springclass.domain.AirportLocation;
import com.springclass.domain.DVDData;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.Member;
import com.springclass.fixture.AirportLocationFixture;
import com.springclass.fixture.DVDDataFixture;
import com.springclass.fixture.DVDInfoFixture;
import com.springclass.fixture.MemberFixture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StubConfig {

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

} // The End..
