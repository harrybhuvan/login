package com.example.login.service;


import java.util.List;
import java.util.Optional;

import com.example.login.entity.User;


public interface UserService {
	 	public User createUser(User user);
	    List<User> getAllUsers();
	    Optional<User> getUserById(int id);
	    User updateUser(int id, User user);
	    void deleteUser(int id);
}
