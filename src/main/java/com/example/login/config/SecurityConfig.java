package com.example.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers("/users/**")
				.authenticated()
				.anyRequest()
				.permitAll()).httpBasic();
		return http.build();
	}
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User.builder()
	                .username("admin")
	                .password("{noop}password") // {noop} means plain text password
	                .roles("USER")
	                .build();
	        return new InMemoryUserDetailsManager(user);
	    }

}
