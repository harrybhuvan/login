package com.example.login.apiUser;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.UserDTO;
import com.example.login.entity.User;
import com.example.login.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class userController {
	
	private static final Logger logger = LoggerFactory.getLogger(userController.class);
	@Autowired
	private UserService userService;
	
	@GetMapping("/paginated")
	public ResponseEntity<Page<UserDTO>> getAllUsersPaginated(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy){
		return ResponseEntity.ok(userService.getAllUsersPaginated(page, size, sortBy));
	}
	
	 @PostMapping
	    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		 	logger.info("Creating the new user");
	        return ResponseEntity.ok(userService.createUser(userDTO));
	 }
	
	 @GetMapping
	 public List<UserDTO> getAllUsers(
	     @RequestParam(required = false) String name,
	     @RequestParam(required = false) String email,
	     @RequestParam(required = false) String number) {

	     return userService.getUsersByFilters(name, email, number);
	 }

    
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO>  getUserById(@PathVariable int id) {
		logger.info("Fetching the user with ID : {}", id);
	    return userService.getUserById(id)
	    		.map(ResponseEntity::ok)
	    		.orElse(ResponseEntity.notFound()
	    				.build());
	}
    
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable int id,@Valid @RequestBody UserDTO userDTO) {
	    return ResponseEntity.ok(userService.updateUser(id, userDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		logger.info("Deleting the user ID: {} ",id);
	    userService.deleteUser(id);
	    return ResponseEntity.noContent().build();
	}

    		
}
