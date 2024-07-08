package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Customer;

public interface CustomerService {
	Customer save(Customer customerRequest);

	Customer findById(long id);

	Customer findByUUID(UUID genId);

	List<Customer> getAll();

	Customer update(long id, Customer customerRequest);

	boolean delete(long id);

	Customer updateByUUID(UUID genID, Customer customerRequest);

	boolean deleteByUUID(UUID genID);

	Page<Customer> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<Customer> findByNameLike(String name, int pageNo, int pageSize, String sortBy, String sortType);

	Page<Customer> findByPhone1OrPhone2Like(String phone1,String phone2, int pageNo, int pageSize, String sortBy, String sortType);
}
