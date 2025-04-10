package com.tim.mitsuru.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String header = request.getHeader(SecurityConstant.HEADER_STRING);
		System.out.println("------Header is " + header);
		if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken token = getToken(header);
		SecurityContextHolder.getContext().setAuthentication(token);

		filterChain.doFilter(request, response);
			
	}

	private UsernamePasswordAuthenticationToken getToken(String header) {
		String user = JWT.require(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()))
						.build()
						.verify(header.replace(SecurityConstant.TOKEN_PREFIX,""))
						.getSubject();
		if (user != null) {
			return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		}

		return null;
	}
}

