package com.tim.mitsuru.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.mitsuru.model.UserLogin;


@RestController
@RequestMapping("api/user")
public class UserController {


	private UserLogin testUser = new UserLogin("mitsuru", "na-chan");

	@GetMapping("/id")
	public UserLogin findUserId() {
		return testUser;
	}
}

