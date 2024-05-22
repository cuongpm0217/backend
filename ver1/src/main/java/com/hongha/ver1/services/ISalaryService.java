package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Salary;

public interface ISalaryService {
	Salary save(Salary salaryRequest);

	Salary findById(long id);

	Salary findByUUID(UUID genId);

	List<Salary> getAll();

	Salary update(long id, Salary salaryRequest);

	void delete(long id);

	Salary updateByUUID(UUID genID, Salary salaryRequest);

	void deleteByUUID(UUID genID);
}
