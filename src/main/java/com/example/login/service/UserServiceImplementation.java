package com.example.login.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	@Override
	public Page<UserDTO> getAllUsersPaginated(int page, int size, String sortBy) {
		PageRequest pageable = PageRequest.of(page, size,Sort.by(sortBy));
		Page<User> pageUsers = userRepo.findAll(pageable);
		return pageUsers.map(UserMapper::toDTO);
	}
	
	@Override
	public List<UserDTO> getUsersByFilters(String name, String email, String number) {
	    List<User> filteredUsers = userRepo.findAll().stream()
	        .filter(user -> (name == null || user.getName().equalsIgnoreCase(name)))
	        .filter(user -> (email == null || user.getEmail().equalsIgnoreCase(email)))
	        .filter(user -> (number == null || user.getNumber().equals(number)))
	        .collect(Collectors.toList());

	    return filteredUsers.stream()
	            .map(UserMapper::toDTO)
	            .collect(Collectors.toList());
	}

	
	
}

