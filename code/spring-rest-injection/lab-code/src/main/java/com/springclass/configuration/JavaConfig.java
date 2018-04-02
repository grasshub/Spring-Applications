package com.springclass.configuration;

import com.springclass.data.UserDatastore;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages =
        {
                "com.springclass.configuration",
        }
)
@PropertySource(value = {"classpath:application.properties"})
public class JavaConfig {

    @Bean
    public UserDatastore userDatastore(){
        return UserDatastore.getuserDatastore();
    }


    /**
     * Note: If you want to use @PropertySource, you must create a static
     * PropertySourcesPlaceholderConfigurer with the @Bean as seen here.
     * @return
     * @throws java.io.IOException
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
            throws IOException {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
                new PropertySourcesPlaceholderConfigurer();
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
