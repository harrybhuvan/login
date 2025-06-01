package com.example.login.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.DTO.UserDTO;
import com.example.login.entity.User;
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
		return userRepo.findById(id).map(UserMapper::toDTO);
	}

	@Override
	public UserDTO updateUser(int id, UserDTO userDTO) {
		// TODO Auto-generated method stub
		Optional<User> oldUser = userRepo.findById(id);
		if(oldUser.isPresent()) {
			User u = oldUser.get();
			u.setEmail(userDTO.getEmail());
			u.setName(userDTO.getName());
			u.setNumber(userDTO.getNumber());
			return UserMapper.toDTO(userRepo.save(u));
		}
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}
	
	
}
