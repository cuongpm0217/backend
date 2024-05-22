package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Employee;

public interface EmployeeService {
	Employee save(Employee employeeRequest);
	Employee findById(long id);
	Employee findByUUID(UUID genId);
	List<Employee> getAll();
	Employee update(long id,Employee employeeRequest);
	void delete(long id);
	Employee updateByUUID(UUID genID,Employee employeeRequest);
	void deleteByUUID(UUID genID);
}
