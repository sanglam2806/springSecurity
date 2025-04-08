package com.tim.mitsuru.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.mitsuru.model.UserLogin;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter (AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
		try {
			UserLogin creds = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);

			return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				creds.getUsername(), creds.getPassword(), new ArrayList<>()
			));
		} catch (IOException e) {
			throw new BadCredentialsException(e.getMessage(), e);
		}
	}

	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain, Authentication authentication) {

	}
}

