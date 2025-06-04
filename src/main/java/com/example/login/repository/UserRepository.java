package com.example.login.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.login.entity.User;

import jakarta.persistence.Id;

public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByNameContainingIgnoreCase(String name);
	List<User> findByEmailContainingIgnoreCase(String email);
	List<User> findByNumber(String number);


}
