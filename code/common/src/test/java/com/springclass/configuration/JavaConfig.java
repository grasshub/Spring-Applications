package com.springclass.configuration;

import com.springclass.LogInjector;
import com.springclass.bo.DVDStatistics;
import com.springclass.bo.DVDStatisticsImpl;
import com.springclass.bo.KioskService;
import com.springclass.bo.KioskServiceImpl;
import com.springclass.dao.AirportLocationDAO;
import com.springclass.service.InventoryManager;
import com.springclass.stubs.service.InventoryManagerMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages =
        {"com.springclass.bo",
                "com.springclass.domain",
                "com.springclass.service",
                "com.springclass.event",
//                "com.springclass.fixture",
                "com.springclass.stubs",
                "com.springclass.stubs.dao",
        }
)
@Import(StubConfig.class)
@PropertySource(value = { "classpath:test.properties" })
public class JavaConfig {

    @Value("${location.id.us}")
    private String locationUS;
    @Value("${location.id.nl}")
    private String locationNL;
    @Value("${location.id.fr}")
    private String locationFR;

    @Autowired
    AirportLocationDAO airportLocationDAO;

    /**
     * Note: If you dont want to use @PropertySource, and/or need more custom PropertySourcesPlaceholderConfigurer
     * configuration, you can create a @Bean as seen here.
     * @return
     * @throws IOException
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:*.properties"));
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public InventoryManager manager(){
        return new InventoryManagerMock();
    }


    @Bean
    public DVDStatistics dvdStatistics(){
        return new DVDStatisticsImpl();
    }

    @Bean
    public KioskService kioskServiceUS(){
        return new KioskServiceImpl(
                airportLocationDAO.getLocationByID(
                        locationUS
                )
        );
    }

    @Bean
    public KioskService kioskServiceNL(){
        return new KioskServiceImpl(airportLocationDAO.getLocationByID(locationNL));
    }

    @Bean
    public KioskService kioskServiceFR(){
        return new KioskServiceImpl(airportLocationDAO.getLocationByID(locationFR));
    }

//    @Bean
//    public DVDStatistics DVDStatistics(){
//        return new DVDStatisticsImpl();
//    }

    @Bean
    public LogInjector logInjector(){
        return new LogInjector();
    }


} // The end...
