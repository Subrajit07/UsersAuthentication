package com.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.Employees;

public interface IEmployeesRepo extends JpaRepository<Employees, Long> {

	Optional<Employees> findByEmail(String email);
	
	Boolean existsByEmail(String email);
}
