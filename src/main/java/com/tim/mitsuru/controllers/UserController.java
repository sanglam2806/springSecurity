package com.tim.mitsuru.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.mitsuru.model.User;

@RestController
@RequestMapping("api/user")
public class UserController {


	private User testUser = new User("mitsuru", "na-chan");

	@GetMapping("/id")
	public User findUserId() {
		return testUser;
	}
}

