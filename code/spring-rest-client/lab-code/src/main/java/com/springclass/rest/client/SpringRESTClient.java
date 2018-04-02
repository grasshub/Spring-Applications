package com.springclass.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.springclass.data.User;
import com.springclass.data.UserDatastore;
import com.springclass.data.UserList;

/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2016 Trivera Technologies, LLC.
 * http://www.triveratech.com
 * </p>
 * @author Trivera Technologies Tech Team.
 */
@Component
public class SpringRESTClient {

    private static final Logger logger = LoggerFactory.getLogger(SpringRESTClient.class);

    private String baseURL = "http://localhost:8080/spring-rest/rest";

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
   	private UserDatastore userDatastore;

	public void getUser() {

        logger.info("--> getUser()");
        HttpEntity<String> entity = prepareGetRequestHeaders(MediaType.APPLICATION_XML);

        ResponseEntity<User> response = restTemplate.exchange(
                baseURL + "/user/2",
                HttpMethod.GET, entity, User.class);

        User user = response.getBody();
        logger.info("{}: {}", user.getId(), user.getFullName());
	}
	
	public void removeUser() {
		
		logger.info("-->removeUser()");
		
		restTemplate.delete(baseURL + "/user/delete/1");	
	}
	
	public void updateUser() {
		
		logger.info("-->updateUser()");
		
		User user = userDatastore.getUser(2);
		
		user.setFullName("Updated User");
		
		restTemplate.put(baseURL + "/user/update", user);	
	}

    //-----------------------------------------------------------------------//
    public void createUser(RestTemplate rest) {
    	
    	User newUser = new User();
    	newUser.setCity("Sweet");
    	newUser.setFullName("Butch Otter");
    	newUser.setStreet("Main Way");
    	newUser.setUserName("Butch");
    	newUser.setZipcode("83400");
    	
    	HttpEntity<User> response = rest.postForEntity(baseURL + "/user", newUser, User.class);
    	User u = response.getBody();
    	System.out.println("New user: " + u.getId() + ", " + u.getFullName());
    }

    public void getAllUsers() {
        logger.info("---> getAllUsers");

        ResponseEntity<UserList> response = restTemplate.getForEntity(baseURL + "/users", UserList.class);

        UserList users = response.getBody();
        for(User u : users.getUsers()) {
            logger.info("{}: {}", u.getId(), u.getFullName());
        }
    }


	private HttpEntity<String> prepareGetRequestHeaders(MediaType type) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		return entity;
	}

} // The End...
