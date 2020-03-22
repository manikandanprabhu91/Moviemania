package com.carefirst.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carefirst.auth.entity.User;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Long> {
	public User findByEmailId(String email);
}
