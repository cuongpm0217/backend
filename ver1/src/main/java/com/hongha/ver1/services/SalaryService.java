package com.hongha.ver1.services;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Salary;

public interface SalaryService {
	Salary save(Salary salaryRequest);

	Salary findById(long id);

	Salary findByUUID(UUID genId);

	Salary update(long id, Salary salaryRequest);

	boolean delete(long id);

	Salary updateByUUID(UUID genID, Salary salaryRequest);

	boolean deleteByUUID(UUID genID);

	Page<Salary> getAll(int pageNo, int pageSize, String sortBy, String sortType);
}
