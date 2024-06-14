package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Customer;
import com.hongha.ver1.repositories.CustomerRepository;
import com.hongha.ver1.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository cusRepo;

	@Override
	@Transactional
	public Customer save(Customer customerRequest) {
		Customer created = cusRepo.save(customerRequest);
		if (created != null) {
			return created;
		} else {
			throw new RuntimeException("Can't create Customer");
		}
	}

	@Override
	public Customer findById(long id) {
		Customer selected = cusRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found customer:" + String.valueOf(id));
		}
	}

	@Override
	public Customer findByUUID(UUID genId) {
		Customer selected = cusRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Customer:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Customer> getAll() {
		return cusRepo.findAll();
	}

	@Override
	public Customer update(long id, Customer customerRequest) {
		Customer updateObj = cusRepo.getReferenceById(id);
		if (updateObj != null) {
			return updateCustomer(customerRequest, updateObj);
		} else {
			throw new RuntimeException("Not found Customer" + String.valueOf(id));
		}
	}

	private Customer updateCustomer(Customer customerRequest, Customer updateObj) {
		updateObj.setAddress1(customerRequest.getAddress1());
		updateObj.setAddress2(customerRequest.getAddress2());
		updateObj.setCustomerTypeId(customerRequest.getCustomerTypeId());
		updateObj.setName(customerRequest.getName());
		updateObj.setPhone1(customerRequest.getPhone1());
		updateObj.setPhone2(customerRequest.getPhone2());
		
		Customer updated = cusRepo.save(updateObj);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Customer:" + updateObj.getId());
		}
	}

	@Override
	public void delete(long id) {
		Customer updateObj = cusRepo.getReferenceById(id);
		if (updateObj != null) {
			cusRepo.deleteById(updateObj.getId());
		} else {
			throw new RuntimeException("Not found Customer:" + String.valueOf(id));
		}
	}

	@Override
	public Customer updateByUUID(UUID genID, Customer customerRequest) {
		Customer updateObj = cusRepo.findByUUID(genID);
		if (updateObj != null) {
			return updateCustomer(customerRequest, updateObj);
		} else {
			throw new RuntimeException("Not found Customer:" + String.valueOf(genID));
		}
	}

	@Override
	public void deleteByUUID(UUID genID) {
		Customer updateObj = cusRepo.findByUUID(genID);
		if (updateObj != null) {
			cusRepo.deleteById(updateObj.getId());
		} else {
			throw new RuntimeException("Not found Customer" + String.valueOf(genID));
		}

	}

}
