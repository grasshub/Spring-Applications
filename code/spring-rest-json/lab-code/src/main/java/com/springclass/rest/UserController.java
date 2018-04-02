package com.springclass.rest;


import com.springclass.data.User;
import com.springclass.data.UserDatastore;
import com.springclass.data.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @Autowired
	private UserDatastore userDatastore;


    @RequestMapping(value="/users", method=RequestMethod.GET,
            headers="Accept=application/json, application/xml")
    public UserList getUsers() {
        List<User> resultSet = userDatastore.getUsers();
        UserList wrappedSet = new UserList(resultSet);
        return wrappedSet;
    }

    @RequestMapping(value="/user/{userToFind}", method=RequestMethod.GET,
            headers="Accept=application/json, application/xml")
    public User getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("userToFind") String userString) {
        Integer userToFind = Integer.valueOf(userString);
        User candidate = null;
        candidate = userDatastore.getUser(userToFind);
        if (candidate == null) {
            throw new UserNotFoundException(
                    "Could not find a user corresponding to submitted identifier: " +
                            userToFind);
        };
        return candidate;
    }


    @RequestMapping(value="/user", method=RequestMethod.POST,
            headers="Accept=application/json")
    public User createUser(final @RequestBody User newUser) {
        userDatastore.createUser(newUser);
        return newUser;
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.DELETE)
    public UserList deleteUser(@PathVariable("userId") String userToDelete) {
        Integer userTarget = Integer.valueOf(userToDelete);
        User candidate = null;
        candidate = userDatastore.getUser(userTarget);
        if (candidate == null) {
            throw new UserNotFoundException(
                    "No delete occurred - " +
                            "could not find a user corresponding to submitted identifier: " +
                            userToDelete);
        };
        userDatastore.deleteUser(userTarget);
        List<User> resultSet = userDatastore.getUsers();
        UserList wrappedSet = new UserList(resultSet);
        return wrappedSet;
    }

    @RequestMapping(value="/user/{user}", method=RequestMethod.PUT)
    public User updateUser(@RequestBody User updatedUser) {
        User candidate = null;
        Integer userToUpdate = updatedUser.getId();
        if (userToUpdate == null) {
            throw new UserNotFoundException(
                    "No updated occurred - submitted user does not have an identifier");
        };
        candidate = userDatastore.getUser(userToUpdate);
        if (candidate == null) {
            throw new UserNotFoundException(
                    "No updated occurred - " +
                            "could not find a user corresponding to submitted identifier: " +
                            userToUpdate);
        };
        userDatastore.updateUser(updatedUser);
        return updatedUser;
    }


    @RequestMapping(value="/user/zip/{zipcode}", method=RequestMethod.GET)
    public UserList getUserByZip(@PathVariable("zipcode") String targetZipcode) {
        List<User> resultSet = userDatastore.getUsersByZipcode(targetZipcode);
        UserList wrappedSet = new UserList(resultSet);
        return wrappedSet;
    }

    @RequestMapping(value="/users/ds", method=RequestMethod.GET)
    public UserList getUserDataSet(@RequestParam("start") int start,
                                                 @RequestParam("size") int size) {
        List<User> resultSet = userDatastore.getUsers(start, size);
        UserList wrappedSet = new UserList(resultSet);
        return wrappedSet;
    }

    @RequestMapping(value="/user/notRequest", method=RequestMethod.GET)
    public User notFoundRequest()
            throws Exception{
        throw new UserNotFoundException("Not Found Request test");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public String handleNotFoundException(final HttpServletRequest request,
                                          final UserNotFoundException nfe) {
        logger.error(request.getRequestURL().toString(), nfe);

        return nfe.getMessage();
    }


} // The End...
