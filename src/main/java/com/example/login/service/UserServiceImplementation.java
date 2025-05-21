package com.example.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.entity.User;
import com.example.login.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository userRepo;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public Optional<User> getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id);
	}

	@Override
	public User updateUser(int id, User user) {
		// TODO Auto-generated method stub
		Optional<User> oldUser = userRepo.findById(id);
		if(oldUser.isPresent()) {
			User u = oldUser.get();
			u.setEmail(user.getEmail());
			u.setName(user.getName());
			u.setNumber(user.getNumber());
			return userRepo.save(u);
		}
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}
	
	
}
