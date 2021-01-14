package com.phase3.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3.model.User;



@RestController
public class AuthenticationController {
	
public static Set<User> userList = new HashSet<>();
	
	boolean userExists = false;
	
	User currentSessionUser = null;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean firstPage(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		userList.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findAny()
		.ifPresent(user -> {
			userExists = true;
			currentSessionUser = user;
		});
		
		return userExists;
		
		
	}

}
