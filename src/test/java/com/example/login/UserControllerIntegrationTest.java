package com.example.login;

import com.example.login.DTO.UserDTO;
import com.example.login.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.intThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/users";
    }

    @Test
    public void testCreateUserIntegration() {
        // Prepare test user
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Alice");
        userDTO.setEmail("alice@example.com");
        userDTO.setNumber("9876543210");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);

        // Send POST request
        ResponseEntity<UserDTO> response = restTemplate.postForEntity(getBaseUrl(), request, UserDTO.class);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Alice", response.getBody().getName());
    }
    
    @Test
    public void testGetUserByIdIntegration() {
    	//create user
    	UserDTO userDTO = new UserDTO();
    	userDTO.setName("Bob");
    	userDTO.setEmail("bob@example.com");
    	userDTO.setNumber("12345678888");
    	
    	HttpHeaders header =new HttpHeaders();
    	header.setContentType(MediaType.APPLICATION_JSON);
    	
    	HttpEntity<UserDTO> request = new HttpEntity<>(userDTO,header);
    	ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity(getBaseUrl(), request, UserDTO.class);
    
    	int userId = createResponse.getBody().getId();
    	
    	//call GET API
    	ResponseEntity<UserDTO> getResponse = restTemplate.getForEntity(getBaseUrl()+ "/" +userId,UserDTO.class);
    
    	assertEquals(HttpStatus.OK, getResponse.getStatusCode());
    	assertEquals("Bob", getResponse.getBody().getName());
    }
    
    @Test
    public void testUpdateUserIntegration() {
        // Create a user first
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Charlie");
        userDTO.setEmail("charlie@example.com");
        userDTO.setNumber("9999999999");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
        ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity(getBaseUrl(), request, UserDTO.class);

        int userId = createResponse.getBody().getId();

        // Prepare update
        userDTO.setName("Charlie Updated");
        HttpEntity<UserDTO> updateRequest = new HttpEntity<>(userDTO, headers);

        // Call PUT API
        restTemplate.put(getBaseUrl() + "/" + userId, updateRequest);

        // Call GET to verify update
        ResponseEntity<UserDTO> getResponse = restTemplate.getForEntity(getBaseUrl() + "/" + userId, UserDTO.class);

        assertEquals("Charlie Updated", getResponse.getBody().getName());
    }

    
    @Test
    public void testDeleteUserIntegration() {
        // Create a user first
        UserDTO userDTO = new UserDTO();
        userDTO.setName("David");
        userDTO.setEmail("david@example.com");
        userDTO.setNumber("8888888888");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
        ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity(getBaseUrl(), request, UserDTO.class);

        int userId = createResponse.getBody().getId();

        // Call DELETE API
        restTemplate.delete(getBaseUrl() + "/" + userId);

        // Verify the user is deleted
        ResponseEntity<UserDTO> getResponse = restTemplate.getForEntity(getBaseUrl() + "/" + userId, UserDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }

    
    @Test
    public void testPaginationIntegration() {
        // Call GET API with pagination
        String url = getBaseUrl() + "/paginated?page=0&size=5&sortBy=name";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Optionally you can parse the response body and check the content
    }

}

