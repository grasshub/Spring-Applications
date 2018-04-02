package com.springclass.rest;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springclass.data.User;
import com.springclass.data.UserDatastore;
import com.springclass.data.UserList;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
    

    @Autowired
	private UserDatastore userDatastore;

    //--- Service Methods ---------------------------------------------------//
    @RequestMapping(value="/user/{userToFind}", method=RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseBody
    public User getUser(@PathVariable("userToFind") String userString) {
    	
    	Integer userToFind = Integer.valueOf(userString);
    	User candidate = null;
    	candidate = userDatastore.getUser(userToFind);
    	return candidate;
    }
    
    @RequestMapping(value="/user", method=RequestMethod.POST)
    public User createUser(@RequestBody User newUser) {
    	userDatastore.createUser(newUser);
    	return newUser;
    }
    
    @RequestMapping(value="/user/delete/{id}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") String id) {
    	Integer userId = Integer.valueOf(id);
    	userDatastore.deleteUser(userId);
    }
    
    @RequestMapping(value="/user/update", method=RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
    	userDatastore.updateUser(user);
    }
    
    // To force the request without path extension to be displayed as XML file to avoid to be treated as String with nasty output
    @RequestMapping(value="/users", method=RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseBody
    public UserList getUsers() {
    	List<User> users = userDatastore.getUsers();
    	UserList userList = new UserList(users);
    	return userList;    	
    }

	@RequestMapping(value="/user/badRequest", method=RequestMethod.GET)
	public User badRequest()
            throws Exception{
		throw new Exception("Bad Request test");
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
