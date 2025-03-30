package com.tim.mitsuru.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
	@GetMapping("/id")
	public String findUserId() {
		return " Find Na-chan id";
	}
}

