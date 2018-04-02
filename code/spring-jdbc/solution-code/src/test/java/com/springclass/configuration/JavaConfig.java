package com.springclass.configuration;

import com.springclass.bo.KioskService;
import com.springclass.bo.KioskServiceImpl;
import com.springclass.dao.AirportLocationDAO;

import com.springclass.jdbc.support.DvdInfoQueryTests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@Import({JdbcConfig.class})
@ComponentScan(basePackages =
        {
//                "com.springclass.configuration",
                "com.springclass.domain",
                "com.springclass.service",
                "com.springclass.fixture",
                "com.springclass.jdbc.dao",
                "com.springclass.jdbc.support"
        }
)
@PropertySource(value = {"classpath:test.properties"})
public class JavaConfig {

    private final Logger logger = LoggerFactory
            .getLogger(JavaConfig.class);

    @Autowired
    private Environment environment;


    @Value("${location.id.us}")
    private String locationUS;
    @Value("${location.id.nl}")
    private String locationNL;
    @Value("${location.id.fr}")
    private String locationFR;

    @Autowired
    @Qualifier("airportLocationDAOImpl")
    AirportLocationDAO airportLocationDAO;


    @Bean
    @DependsOn({ "dataSource", "airportLocationDAOImpl" })
    public KioskService kioskServiceUS() {
        return new KioskServiceImpl(
                airportLocationDAO.getLocationByID(locationNL)
        );
    }



    /**
     * Note: If you want to use @PropertySource, you must create a static
     * PropertySourcesPlaceholderConfigurer with the @Bean as seen here.
     * @return
     * @throws java.io.IOException
     */
//    @Primary
    @Bean(name = "propertySourcesPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer propertySourcesDefault() throws IOException {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        pspc.setProperties(
                (new YamlPropertiesFactoryBean(){{
                    setResources(
                            new ClassPathResource("application.yml"),
                            new ClassPathResource("embedded.yml")
                    );
                }}).getObject()
        );

        return pspc;
    }


//    @Primary
//    @Profile(value = "derby")
//    @Bean(name = "propertySourcesPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer propertySources() throws IOException {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        pspc.setProperties(
                (new YamlPropertiesFactoryBean(){{
                    setResources(
                            new ClassPathResource("application.yml"),
                            new ClassPathResource("derby.yml"));
                }}).getObject()
        );

        return pspc;
    }

    @PostConstruct
    public void initApplication() throws IOException {
        if (environment.getActiveProfiles().length == 0) {
            logger.warn("No Spring profile configured, running with default configuration");
        }
        else {
            logger.info("Running with Spring profile(s) : {}",
                    Arrays.toString(environment.getActiveProfiles()));
            logger.debug("...");
        }
    }


} // The End...
