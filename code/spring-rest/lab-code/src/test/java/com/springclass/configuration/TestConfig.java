package com.springclass.configuration;

import com.springclass.fixture.AirportLocationFixture;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages =
        {
                "com.springclass.presentation",
//                "com.springclass.configuration",
                "com.springclass.dao",
                "com.springclass.jdbc.dao",
                "com.springclass.bo",
                "com.springclass.service",
                "com.springclass.fixture",
        }
)
@Import({RestConfig.class})
//@Import({JdbcConfig.class, MvcConfig.class})
@PropertySource(value = {"classpath:application.properties", "classpath:test.properties"})
public class TestConfig {

    @Bean
    public AirportLocationFixture airportLocationFixture(){
        return new AirportLocationFixture();
    }


} // The End...
