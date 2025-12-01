package com.auth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

}
