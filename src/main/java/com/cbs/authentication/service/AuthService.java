package com.cbs.authentication.service;

import com.cbs.authentication.dto.AuthenticationDto;

/**
 * Provides methods for authenticating users and generating JWT tokens.
 */
public interface AuthService {

	/**
	 * Generates a JWT token for the given user credentials.
	 *
	 * @param userLogin the user credentials (username and password)
	 * @return a JWT token as a string
	 */
	public String generateToken(AuthenticationDto userLogin);
}
