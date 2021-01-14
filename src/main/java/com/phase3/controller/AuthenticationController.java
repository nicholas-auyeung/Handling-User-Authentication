package com.phase3.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3.model.User;



@RestController
public class AuthenticationController {
	
	private String role = "admin"
;	
	public static Set<User> userList = new HashSet<>();
	
	boolean userExists = false;
	
	User currentSessionUser = null;
	
	
	
	@RequestMapping(value = "/username", method = RequestMethod.GET)
	public String getUsername() {
		Optional<User> user = userList.stream().findFirst();
		if(user.isPresent()) {
			return user.get().getUsername();
		}
		return null;
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String getRole() {
		Optional<User> user = userList.stream().findFirst();
		if(user.isPresent()) {
			return user.get().getRole();
		}
		return null;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Optional<User> getUser() {
		
		return userList.stream().findFirst();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean login(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		userList.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findAny()
		.ifPresent(user -> {
			userExists = true;
			currentSessionUser = user;
		});
		
		return userExists;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public User logout() {
		
		return currentSessionUser = null;
	}

}
