package com.example.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.login.DTO.UserDTO;
import com.example.login.entity.User;
import com.example.login.repository.UserRepository;
import com.example.login.service.UserServiceImplementation;

public class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImplementation userService;

	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	public void testCreateUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("John");
        userDTO.setEmail("john@example.com");
        userDTO.setNumber("1234567890");
        
        User user = new User();
        user.setId(1);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setNumber(userDTO.getNumber());
        
        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class))).thenReturn(user);
        
        UserDTO savedUser = userService.createUser(userDTO);
        
        assertEquals(userDTO.getName(),savedUser.getName());
        assertEquals(userDTO.getEmail(), savedUser.getEmail());
        assertEquals(userDTO.getNumber(), savedUser.getNumber());
	}
}
