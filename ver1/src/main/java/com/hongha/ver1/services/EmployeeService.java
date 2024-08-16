package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Employee;

public interface EmployeeService {
	Employee save(Employee employeeRequest);

	Employee findById(long id);

	Employee findByUUID(UUID genId);

	List<Employee> getAll();

	Employee update(long id, Employee employeeRequest);

	boolean delete(long id);

	Employee updateByUUID(UUID genID, Employee employeeRequest);

	boolean deleteByUUID(UUID genID);

	Page<Employee> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<Employee> findByNameLike(String name, int pageNo, int pageSize, String sortBy, String sortType);

	Page<Employee> findByPhone1OrPhone2Like(String phone1, String phone2, int pageNo, int pageSize, String sortBy,
			String sortType);

	Page<Employee> findByBranchIdAndDepartmentId(long branchId, long departmentId, int pageNo, int pageSize,
			String sortBy, String sortType);

}
