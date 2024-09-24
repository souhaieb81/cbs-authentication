package com.cbs.authentication.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cbs.authentication.model.Role;
import com.cbs.authentication.model.User;
import com.cbs.authentication.repository.RoleRepository;
import com.cbs.authentication.repository.UserRepository;
import com.cbs.authentication.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User addUser(User user) {
		Optional<Role> role = roleRepository.findById(2);

		if (role.isPresent()) {
			Set<Role> roles = new HashSet<>();
			roles.add(role.get());
			user = User.builder().username(user.getUsername())
					.password(new BCryptPasswordEncoder().encode(user.getPassword())).email(user.getEmail())
					.roles(roles).firstName(user.getFirstName()).lastName(user.getLastName()).build();
			userRepository.save(user);
		}

		return user;
	}

}
