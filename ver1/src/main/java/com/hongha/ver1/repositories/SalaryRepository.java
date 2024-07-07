package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
	Salary findByGenId(UUID genId);
}
