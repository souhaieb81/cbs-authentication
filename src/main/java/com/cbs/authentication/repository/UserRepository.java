package com.cbs.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbs.authentication.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
