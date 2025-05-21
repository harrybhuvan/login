package com.example.login.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.User;

import jakarta.persistence.Id;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	

}
