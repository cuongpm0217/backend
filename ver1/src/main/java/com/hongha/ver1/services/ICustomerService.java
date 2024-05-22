package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Customer;

public interface ICustomerService {
	Customer save(Customer customerRequest);

	Customer findById(long id);

	Customer findByUUID(UUID genId);

	List<Customer> getAll();

	Customer update(long id, Customer customerRequest);

	void delete(long id);

	Customer updateByUUID(UUID genID, Customer customerRequest);

	void deleteByUUID(UUID genID);
}
