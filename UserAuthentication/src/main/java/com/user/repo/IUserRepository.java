package com.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.Users;

public interface IUserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);

	Optional<Users> findByVerificationEmailToken(String token);
}
