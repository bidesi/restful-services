package com.bidesi.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bidesi.rest.bean.User;
import com.bidesi.rest.exception.UserNotFoundException;
import com.bidesi.rest.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> findAllUser() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public User find(@PathVariable int id) {
		
		User user = userService.find(id);
		
		if(user == null)
			throw new UserNotFoundException("user id:"+ id);
		
		return user;
		
	}

	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		URI location = null;
		int userId = userService.add(user);
		if (userId != 0) {
			location = ServletUriComponentsBuilder.fromCurrentContextPath().path("users/" + userId).build().toUri();
		}
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteUser(id);
		
		if(user == null)
			throw new UserNotFoundException("user id:"+ id);
		
	}
}
