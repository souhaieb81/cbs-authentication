package com.cbs.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.authentication.dto.AuthenticationDto;
import com.cbs.authentication.model.User;
import com.cbs.authentication.service.AuthService;
import com.cbs.authentication.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserService userService;
	
	private final AuthService authService;

	public AuthController(UserService userService, AuthService authService) {
		this.userService = userService;
		this.authService = authService;
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthenticationDto userLogin) {
		String token = authService.generateToken(userLogin);

		return new ResponseEntity<>(token, HttpStatus.OK);
	}

}
