package com.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.Companies;

public interface ICompaniesRepo extends JpaRepository<Companies, Long> {

}
