package com.springclass.configuration;

import com.springclass.bo.KioskServiceImpl;
import com.springclass.dao.*;
import com.springclass.stubs.dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
@Import({StubConfig.class})
@PropertySource(value = {"classpath:test.properties"})
@ComponentScan(basePackages =
        {
                "com.springclass.configuration",
                "com.springclass.domain",
//                "com.springclass.bo",
                "com.springclass.cache",
                "com.springclass.service",
                "com.springclass.jdbc.dao"
        }
)
public class JavaConfig {

    @Value("${location.id.us}")
    private String locationUS;

    /*
        Define the five (mocked) DAO implemention classes
        all these classes reside in the com.springclass.dao package
     */
    @Bean
    public AirportLocationDAO airportLocationDAO(){
        return new AirportLocationDAOImpl();
    }

    @Bean
    public DVDInfoDAO dvdInfoDAO(){
        return new DVDInfoDAOImpl();
    }

    @Bean
    public DVDLocationDAO dvdLocationDAO(){
        return new DVDLocationDAOImpl();
    }

    @Bean
    public LoanDAO loanDAO(){
        return new LoanDAOImpl();
    }

    @Bean
    public MemberDAO memberDAO(){
        return new MemberDAOImpl();
    }


    /*
        TODO: NOTE: We changed the name of the KioskService to KioskServiceImpl
        We have done this because otherwise, we would have a name & type
        conflict with kioskService in AopConfig.java
    */
    @Bean
    public KioskServiceImpl kioskServiceImpl(){
        KioskServiceImpl kioskService = new KioskServiceImpl(
                airportLocationDAO().getLocationByID(
                        locationUS
                )
        );

        kioskService.setDvdInfoDAO(dvdInfoDAO());
        kioskService.setDvdLocationDAO(dvdLocationDAO());
        kioskService.setLoanDAO(loanDAO());
        kioskService.setMemberDAO(memberDAO());

        return kioskService;
    }






    /**
     * Note: If you want to use @PropertySource, you must create a static
     * PropertySourcesPlaceholderConfigurer with the @Bean as seen here.
     * @return
     * @throws java.io.IOException
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        propertySourcesPlaceholderConfigurer.setProperties(yamlPropertiesFactoryBean().getObject());
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public static YamlPropertiesFactoryBean yamlPropertiesFactoryBean() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        return yaml;
    }
} // The end...
