package com.example.login.service;


import java.util.List;
import java.util.Optional;

import com.example.login.DTO.UserDTO;
import com.example.login.entity.User;


public interface UserService {
	 	public UserDTO createUser(UserDTO userDTO);
	    List<UserDTO> getAllUsers();
	    UserDTO getUserById(int id);
	    UserDTO updateUser(int id, UserDTO userDTO);
	    void deleteUser(int id);
}
