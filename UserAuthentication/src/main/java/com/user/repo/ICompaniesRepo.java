package com.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.Companies;

public interface ICompaniesRepo extends JpaRepository<Companies, Long> {

	Optional<Companies> findByNameIgnoreCase(String companyName);

}
