package com.cbs.authentication.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cbs.authentication.dto.AuthenticationDto;
import com.cbs.authentication.service.AuthService;
import com.cbs.authentication.service.JWTService;

/**
 * Provides methods for authenticating users and generating JWT tokens.
 */
@Service
public class AuthServiceImpl implements AuthService {

	private final JWTService jwtService;

	private final AuthenticationManager authenticationManager;

	public AuthServiceImpl(JWTService jwtService, AuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	/*
	 * 
	 */
	@Override
	public String generateToken(AuthenticationDto userLogin) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return jwtService.generateToken(authentication);
	}

}
