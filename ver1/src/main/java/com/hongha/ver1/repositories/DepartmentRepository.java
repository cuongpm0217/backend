package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	Department findByGenId(UUID genId);
	
	@Query(value= "SELECT _d.* FROM `_department` as _d "
			+ "LEFT JOIN `_branch` _b ON _d.branch_id = _b.id "
			+ "WHERE "			
			+ "(SELECT CONVERT(_d.updated_at,DATE)) LIKE CONCAT('%', CONCAT(:searchText, '%')) "
			+ "OR LOWER(_b.name) LIKE lower(concat('%', concat(:searchText, '%'))) "
			+ "OR LOWER(_d.code) LIKE lower(concat('%', concat(:searchText, '%'))) "
			+ "OR LOWER(_d.name) LIKE lower(concat('%', concat(:searchText, '%'))) "
			+ "OR LOWER(_d.vname) LIKE lower(concat('%', concat(:searchText, '%'))) "
			,countQuery = "SELECT count(_d.id) FROM `_department` as _d "
					+ "LEFT JOIN `_branch` _b ON _d.branch_id = _b.id "
					+ "WHERE "					
					+ "(SELECT CONVERT(_d.updated_at,DATE)) LIKE CONCAT('%', CONCAT(:searchText, '%')) "
					+ "OR LOWER(_b.name) LIKE lower(concat('%', concat(:searchText, '%'))) "
					+ "OR LOWER(_d.code) LIKE lower(concat('%', concat(:searchText, '%'))) "
					+ "OR LOWER(_d.name) LIKE lower(concat('%', concat(:searchText, '%'))) "
					+ "OR LOWER(_d.vname) LIKE lower(concat('%', concat(:searchText, '%'))) "
			,nativeQuery = true)
	Page<Department> findBySearchText(@Param("searchText") String searchText, Pageable pageable);
	Page<Department> findByBranchId(long branchId, Pageable pageable);
}
