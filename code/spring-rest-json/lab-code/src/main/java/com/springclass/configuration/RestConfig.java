package com.springclass.configuration;

import com.springclass.interceptors.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
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

// Lab: you need to configure Spring in order for MVC features to be enabled in your applications
@EnableWebMvc

@Configuration
@ComponentScan(basePackages = {"com.springclass.rest"})
public class RestConfig extends WebMvcConfigurerAdapter {

    public RestConfig(){
        super();
    }


    //-----------------------------------------------------------------------//
    // TODO: Lab: Change defaultContentType to JSON :
    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer
                .ignoreAcceptHeader(false)
                .favorPathExtension(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaTypes(
                    new HashMap<String, MediaType>(){
                        {
                            put("xml", MediaType.APPLICATION_XML);
                            put("json", MediaType.APPLICATION_JSON);
                        }
                });
    }


    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
    }


} // The End...
