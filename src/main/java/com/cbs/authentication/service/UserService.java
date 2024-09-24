package com.cbs.authentication.service;

import com.cbs.authentication.model.User;

public interface UserService {

	/**
	 * create new user
	 * 
	 * @param user the user
	 * @return the created user
	 */
	public User addUser(User user);
}
