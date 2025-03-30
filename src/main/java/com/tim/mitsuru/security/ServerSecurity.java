package com.tim.mitsuru.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ServerSecurity {
	 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		System.out.println("Hello from FilterChain");

		return http.build();
	}
}

