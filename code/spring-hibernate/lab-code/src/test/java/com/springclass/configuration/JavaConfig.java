package com.springclass.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;

@Configuration
@Import(HibernateConfig.class)
@ComponentScan(basePackages =
        {
                "com.springclass.configuration",
                "com.springclass.domain",
                //"com.springclass.bo",
                "com.springclass.service",

                "com.springclass.dao.hibernate",
        }
)
@PropertySource(value = {"classpath:test.properties"})
public class JavaConfig {

    @Value("${location.id.us}")
    private String locationUS;
    @Value("${location.id.nl}")
    private String locationNL;
    @Value("${location.id.fr}")
    private String locationFR;

    /**
     * Note: If you want to use @PropertySource, you must create a static
     * PropertySourcesPlaceholderConfigurer with the @Bean as seen here.
     * @return
     * @throws java.io.IOException
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySources() throws IOException {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        pspc.setProperties(yamlPropertiesFactoryBean().getObject());

        return pspc;
    }

    @Bean
    public static YamlPropertiesFactoryBean yamlPropertiesFactoryBean() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        return yaml;
    }
} // The end...
