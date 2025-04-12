package com.tim.mitsuru.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class ServerSecurity {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userServiceImpl);
	
		// return http.getSharedObject(AuthenticationManagerBuilder.class)
		// 			.userDetailsService(userServiceImpl).and().build();
		return builder.build();
	}
	 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{
		http
			// .cors(customizer -> customizer.disable())
			.csrf((customizer) -> customizer.disable())
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/login").permitAll()
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults())
			.addFilter(new JWTAuthenticationFilter(authenticationManager))
			.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		;

		return http.build();
	}

}

