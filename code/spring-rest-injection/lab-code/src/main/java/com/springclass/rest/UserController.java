package com.springclass.rest;


import com.springclass.data.User;
import com.springclass.data.UserDatastore;
import com.springclass.data.UserList;
import com.springclass.interceptors.LoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
	private UserDatastore userDatastore;

    //--- Service Methods ---------------------------------------------------//

    @RequestMapping(value="/users/ds", method=RequestMethod.GET)
    public UserList getUserDataSet(@RequestParam("start") int start, @RequestParam("size") int size) {
        final List<User> resultSet = userDatastore.getUsers(start, size);
        final UserList wrappedSet = new UserList(resultSet);
        return wrappedSet;
    }

    @RequestMapping(value="/users/zip/{zipcode}", method=RequestMethod.GET)
    public UserList getUserByZip(@PathVariable("zipcode") String zipcode) {
        final List<User> resultSet = userDatastore.getUsersByZipcode(zipcode);
        final UserList wrappedSet = new UserList(resultSet);
        return wrappedSet;
    }

    // Handle UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionHandler(final HttpServletRequest request, UserNotFoundException exception) {
    	logger.error(request.getRequestURL().toString(), exception);
    	
    	// Return the exception message in simple format
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("<error><message>");
    	stringBuilder.append(exception.getMessage());
    	stringBuilder.append("</message></error>");
    	
    	return stringBuilder.toString();	
    }
    
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public UserList getUsers() {
        final List<User> resultSet = userDatastore.getUsers();
        final UserList wrappedSet = new UserList(resultSet);
        return wrappedSet;
    }

    // Handle the invalid user id search
    // Test with URL: http://localhost:8080/spring-rest/rest/user/99
    @RequestMapping(value="/user/{userToFind}", method=RequestMethod.GET)
    public User getUser(@PathVariable("userToFind") String userString) {
        Integer userToFind = Integer.valueOf(userString);
        User candidate = null;
        candidate = userDatastore.getUser(userToFind);
        
        // Could not find the requested user
        if (candidate == null) {
        	throw new UserNotFoundException("Could not find the user related to identifier: " + userString);      	
        }
        
        return candidate;
    }

    @RequestMapping(value="/user",
            method=RequestMethod.POST)
    public User createUser(@RequestBody User newUser) {
        userDatastore.createUser(newUser);
        return newUser;
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.DELETE)
    public UserList deleteUser(@PathVariable("userId") String userToDelete) {
        Integer userTarget = Integer.valueOf(userToDelete);
        userDatastore.deleteUser(userTarget);
        List<User> resultSet = userDatastore.getUsers();
        UserList wrappedSet = new UserList(resultSet);
        return wrappedSet;
    }

    @RequestMapping(value="/user/{user}", method=RequestMethod.PUT)
    public User updateUser(@RequestBody User updatedUser) {
        userDatastore.updateUser(updatedUser);
        return updatedUser;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public void handleBadRequest(HttpServletRequest request, Exception ex)
            throws Exception {

        logger.error(request.getRequestURL().toString(), ex);

        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null)
            throw ex;
    }

} // The End...
