package com.fitnessarea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitnessarea.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	public User findByUsername(String username);
	public User findByEmail(String uemail);
}
