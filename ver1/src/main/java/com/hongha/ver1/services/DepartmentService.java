package com.hongha.ver1.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Department;

public interface DepartmentService {
	Department save(Department departmentRequest);

	Department findById(long id);

	Department findByUUID(UUID genId);

	List<Department> getAll() throws IOException;

	Department update(long id, Department departmentRequest);

	boolean delete(long id);

	Department updateByUUID(UUID genID, Department departmentRequest);

	boolean deleteByUUID(UUID genID);

	Page<Department> findBySearchText(String searchText, int pageNo, int pageSize, String sortBy, String sortType);

	Page<Department> getAll(int pageNo, int pageSize, String sortBy, String sortType) throws IOException;
	
	Page<Department> getByBranch(long branchId,int pageNo, int pageSize, String sortBy, String sortType);
}
