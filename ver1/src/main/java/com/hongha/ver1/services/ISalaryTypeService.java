package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.SalaryType;

public interface ISalaryTypeService {
	SalaryType save(SalaryType salaryTypeRequest);

	SalaryType findById(long id);

	SalaryType findByUUID(UUID genId);

	List<SalaryType> getAll();

	SalaryType update(long id, SalaryType salaryTypeRequest);

	void delete(long id);

	SalaryType updateByUUID(UUID genID, SalaryType salaryTypeRequest);

	void deleteByUUID(UUID genID);
}
