package com.cbs.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbs.authentication.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
