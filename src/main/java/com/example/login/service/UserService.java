package com.example.login.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.login.DTO.UserDTO;
import com.example.login.entity.User;


public interface UserService {
	 	public UserDTO createUser(UserDTO userDTO);
	    List<UserDTO> getAllUsers();
	    Optional<UserDTO> getUserById(int id);
	    UserDTO updateUser(int id, UserDTO userDTO);
	    void deleteUser(int id);
	    Page<UserDTO> getAllUsersPaginated(int page, int size, String sortBy);
	    List<UserDTO> getUsersByFilters(String name, String email, String number);

}
