package com.springclass.rest.client;

import com.springclass.data.User;
import com.springclass.data.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    private static final Logger logger = LoggerFactory
            .getLogger(SpringRESTClient.class);

    private String baseURL = "http://localhost:8080/spring-rest-json/rest";

    private MediaType DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON;

    @Autowired
    private RestTemplate restTemplate;

    //--- Service Methods ---------------------------------------------------//

    public void getAllUsers() {
        logger.info("--> getAllUsers()");
        HttpEntity<String> entity = prepareGetRequestHeaders(DEFAULT_MEDIA_TYPE);

        ResponseEntity<UserList> response = restTemplate.exchange(
                baseURL + "/users",
                HttpMethod.GET, entity, UserList.class);

        UserList users = response.getBody();
        for(User u : users.getUsers()) {
            logger.info("{}: {}", u.getId(), u.getFullName());
        }
    }

    public void getUser() {
        logger.info("--> getUser()");
        HttpEntity<String> entity = prepareGetRequestHeaders(DEFAULT_MEDIA_TYPE);

        ResponseEntity<User> response = restTemplate.exchange(
                baseURL + "/user/1",
                HttpMethod.GET, entity, User.class);

        User user = response.getBody();
        logger.info("{}: {}", user.getId(), user.getFullName());
    }


    public void createUser() {
        logger.info("--> createUser()");

        User newUser = new User();
        newUser.setCity("Sweet");
        newUser.setFullName("Butch Otter");
        newUser.setStreet("Main Way");
        newUser.setUserName("Butch");
        newUser.setZipcode("83400");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(DEFAULT_MEDIA_TYPE);

        HttpEntity<User> entity = new HttpEntity<>(newUser, headers);

        ResponseEntity<User> response = restTemplate.postForEntity(
                baseURL + "/user",
                entity, User.class);

        User u = response.getBody();
        logger.info("New user: {}, {}", u.getId(), u.getFullName());
    }

    public void updateUser() {
        logger.info("--> updateUser()");

        HttpEntity<String> entity = prepareGetRequestHeaders(DEFAULT_MEDIA_TYPE);

        ResponseEntity<UserList> response = restTemplate.exchange(
                baseURL + "/users",
                HttpMethod.GET, entity, UserList.class);

        UserList users = response.getBody();
        List<User> userList = users.getUsers();
        User updatedUser = userList.get(1);
        updatedUser.setFullName("Crouch");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(DEFAULT_MEDIA_TYPE);

        HttpEntity<User> updated = new HttpEntity<User>(updatedUser, headers);

        restTemplate.put(
                baseURL + "/user/{id}",
                updated, updatedUser.getId().toString());
    }

    public void removeUser(String userId) {
        logger.info("--> removeUser()");

        HttpEntity<String> entity = prepareGetRequestHeaders(DEFAULT_MEDIA_TYPE);

        ResponseEntity<UserList> response = restTemplate.exchange(
                baseURL + "/user/" + userId,
                HttpMethod.DELETE, entity, UserList.class);

        UserList users = response.getBody();
        for(User u : users.getUsers()) {
            logger.info(u.getId() + ": " + u.getFullName());
        }
    }

	private HttpEntity<String> prepareGetRequestHeaders(MediaType type) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypes);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		return entity;
	}

} // The End...
