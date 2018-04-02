package com.springclass.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Profile
 *
 */

@Configuration
@ComponentScan(basePackages = {"com.springclass.rest"})
public class RestConfig {

    public RestConfig(){
        super();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

} // The End...
