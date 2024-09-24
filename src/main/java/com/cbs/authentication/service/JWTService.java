package com.cbs.authentication.service;

import org.springframework.security.core.Authentication;

public interface JWTService {

	public String generateToken(Authentication authentication);

}
