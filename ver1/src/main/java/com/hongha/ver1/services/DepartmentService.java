package com.hongha.ver1.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Department;

public interface DepartmentService {
	Department save(Department departmentRequest);

	Department findById(long id);

	Department findByUUID(UUID genId);

	List<Department> getAll() throws IOException;

	Department update(long id, Department departmentRequest);

	void delete(long id);

	Department updateByUUID(UUID genID, Department departmentRequest);

	void deleteByUUID(UUID genID);
}
