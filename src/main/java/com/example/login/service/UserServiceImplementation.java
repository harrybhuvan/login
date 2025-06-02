package com.example.login.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.DTO.UserDTO;
import com.example.login.entity.User;
import com.example.login.exception.UserNotFoundException;
import com.example.login.mapper.UserMapper;
import com.example.login.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = UserMapper.toEntity(userDTO);
	    User saved = userRepo.save(user);
		return UserMapper.toDTO(saved);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll().stream()
				.map(UserMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<UserDTO> getUserById(int id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepo.findById(id).map(UserMapper::toDTO)
				.orElseThrow(()->new UserNotFoundException("User not found with id: "+id)));
	}

	@Override
	public UserDTO updateUser(int id, UserDTO userDTO) {
		// TODO Auto-generated method stub
		User oldUser  = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
	
			
		oldUser.setEmail(userDTO.getEmail());
		oldUser.setName(userDTO.getName());
		oldUser.setNumber(userDTO.getNumber());
			return UserMapper.toDTO(userRepo.save(oldUser));
		
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}
	
	
}
