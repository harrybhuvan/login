package com.example.login.apiUser;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.UserDTO;
import com.example.login.entity.User;
import com.example.login.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class userController {
	@Autowired
	private UserService userService;
	
	 @PostMapping
	    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
	        return ResponseEntity.ok(userService.createUser(userDTO));
	 }
	
	@GetMapping
    public List<UserDTO> getAllUsers(){
    	return userService.getAllUsers();
    }
    
	@GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
    	return userService.getUserById(id)
    			.map(ResponseEntity::ok)
    			.orElse(ResponseEntity.notFound().build());
    }
    
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable int id,@Valid @RequestBody UserDTO userDTO) {
	    return ResponseEntity.ok(userService.updateUser(id, userDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
	    userService.deleteUser(id);
	    return ResponseEntity.noContent().build();
	}

    		
}
