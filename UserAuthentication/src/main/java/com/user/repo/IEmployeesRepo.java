package com.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.Employees;

public interface IEmployeesRepo extends JpaRepository<Employees, Long> {

	Employees findByEmail(String email);
}
