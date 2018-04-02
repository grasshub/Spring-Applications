package com.springclass.configuration;

import com.springclass.bo.KioskService;
import com.springclass.bo.KioskServiceImpl;
import com.springclass.dao.AirportLocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan(basePackages =
        {
                "com.springclass.configuration",
                "com.springclass.dao",
                "com.springclass.jdbc.dao",
                "com.springclass.bo",
                "com.springclass.service",

                //*** MUST ADD THIS ONE ENTRY *****************************************************/
                "com.springclass.stubs.service",
                "com.springclass.fixture",
        }
)
@PropertySource(value = {"classpath:application.properties", "classpath:test.properties"})
public class JavaConfig {

    @Value("${location.id.us}")
    private String locationUS;

    @Value("${location.id.nl}")
    private String locationNL;

    @Value("${location.id.fr}")
    private String locationFR;

    @Autowired
    AirportLocationDAO airportLocationDAOImpl;

    @Bean
    @DependsOn({ "dataSource", "airportLocationDAOImpl" })
    public KioskService kioskServiceUS() {
        return new KioskServiceImpl(
                airportLocationDAOImpl.getLocationByID(
                        locationNL
                )
        );
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
