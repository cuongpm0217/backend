package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.CustomerType;

public interface ICustomerTypeService {
	CustomerType save(CustomerType customerTypeRequest);

	CustomerType findById(long id);

	CustomerType findByUUID(UUID genId);

	List<CustomerType> getAll();

	CustomerType update(long id, CustomerType customerTypeRequest);

	void delete(long id);

	CustomerType updateByUUID(UUID genID, CustomerType customerTypeRequest);

	void deleteByUUID(UUID genID);
}
