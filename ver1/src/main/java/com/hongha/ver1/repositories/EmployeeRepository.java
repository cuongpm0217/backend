package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findByGenId(UUID genId);

	Page<Employee> findByNameLike(String name, Pageable paging);

	Page<Employee> findByPhone1OrPhone2Like(String phone1, String phone2, Pageable paging);

	Page<Employee> findByBranchIdAndDepartmentId(long branchId, long departmentId, Pageable paging);
}
