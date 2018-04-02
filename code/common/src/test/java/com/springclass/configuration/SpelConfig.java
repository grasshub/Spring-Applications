package com.springclass.configuration;

import com.springclass.LogInjector;
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
@PropertySource(value = { "classpath:test.properties" })
public class SpelConfig {

    @Value("${location.id.us}")
    private String locationUS;


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

} // The end...
