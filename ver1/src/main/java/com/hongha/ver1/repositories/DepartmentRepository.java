package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	Department findByGenId(UUID genId);
	Slice<Department> findByBranchIdAndVnameLike(long branchId,String vName,Pageable paging);
}
