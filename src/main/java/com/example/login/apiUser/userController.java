package com.example.login.apiUser;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.User;
import com.example.login.service.UserService;

@RestController
@RequestMapping("/users")
public class userController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping
    public List<User> getAllUsers(){
    	return userService.getAllUsers();
    }
    
	@GetMapping("/{id}")
    Optional<User> getUserById(@PathVariable int id){
    	return userService.getUserById(id);
    }
    
	@PutMapping("/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
	    return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
	    userService.deleteUser(id);
	}

    		
}
