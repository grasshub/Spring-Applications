package com.springclass.configuration;

import java.util.HashMap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.springclass.interceptors.LoggingInterceptor;

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

	@SuppressWarnings("serial")
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// TODO Auto-generated method stub
		 configurer.ignoreAcceptHeader(false)
		 		   .favorPathExtension(true)
		 		   .defaultContentType(MediaType.APPLICATION_XML)
		 		   .mediaTypes(
		 				   new HashMap<String, MediaType>() {
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
