package com.springclass.configuration;

import com.springclass.fixture.AirportLocationFixture;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages =
        {
                "com.springclass.rest",
        }
)
@Import({RestConfig.class})
//@Import({JdbcConfig.class, MvcConfig.class})
@PropertySource(value = {"classpath:application.properties"})
public class TestConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

} // The End...
