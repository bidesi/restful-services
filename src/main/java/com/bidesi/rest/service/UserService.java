package com.bidesi.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bidesi.rest.bean.User;

@Component
public class UserService {
	
	private static int counter;
	private static List<User> users = new ArrayList<User>();
	
	
	
	static {
		users.add(new User(++counter, "Bidesi", "Gauda", new Date()));
		users.add(new User(++counter, "Ashok", "Kumar", new Date()));
		users.add(new User(++counter, "Daman", "Pradhan", new Date()));
		users.add(new User(++counter, "Rajib", "Lochan", new Date()));
		users.add(new User(++counter, "J P", "Nadda", new Date()));
	}
	
	
	public List<User> findAll(){
		return users;
	}
	
	public User find(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public int add(User user) {
		if(user != null) {
			user.setId(++counter); 	
			users.add(user);
			return counter;
		}
		return 0;
	}
	
}
