package com.tim.mitsuru.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ServerSecurity {

	@Autowired
	private UserServiceImpl userServiceImpl;
	 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			// .cors(customizer -> customizer.disable())
			.csrf((customizer) -> customizer.disable())
			.authorizeHttpRequests((authorize) -> authorize
				// .requestMatchers("/login").permitAll()
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults());
		// .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		;

		//will be declare  
		return http.build();
	}

	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails userDetails = User.withDefaultPasswordEncoder()
	// 		.username("user")
	// 		.password("password")
	// 		.roles("USER")
	// 		.build();
	//
	// 	return new InMemoryUserDetailsManager(userDetails);
	// }
	//
	@Bean
	public AuthenticationProvider authen() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userServiceImpl);

		return authenticationProvider;
	}
}

