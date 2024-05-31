package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.Customer;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.enums.EAction;
import com.hongha.ver1.repositories.CustomerRepository;
import com.hongha.ver1.repositories.HistoryRepository;
import com.hongha.ver1.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository cusRepo;
	@Autowired
	private HistoryRepository hisRepo;
	private History history;

	@Override
	public Customer save(Customer customerRequest) {
		history = new History(Customer.class.getName(), EAction.CREATE, customerRequest.getId().toString());
		hisRepo.save(history);
		return cusRepo.save(customerRequest);
	}

	@Override
	public Customer findById(long id) {
		return cusRepo.getReferenceById(id);
	}

	@Override
	public Customer findByUUID(UUID genId) {
		return cusRepo.findByUUID(genId);
	}

	@Override
	public List<Customer> getAll() {
		return cusRepo.findAll();
	}

	@Override
	public Customer update(long id, Customer customerRequest) {
		Customer updateObj = cusRepo.getReferenceById(id);
		updateObj.setAddress(customerRequest.getAddress());
		updateObj.setCustomerTypeId(customerRequest.getCustomerTypeId());
		updateObj.setName(customerRequest.getName());
		updateObj.setPhone1(customerRequest.getPhone1());
		updateObj.setPhone2(customerRequest.getPhone2());
		updateObj.setSurrogateId(customerRequest.getSurrogateId());
		history = new History(Customer.class.getName(), EAction.UPDATE, updateObj.getId().toString());
		hisRepo.save(history);
		return cusRepo.save(updateObj);
	}

	@Override
	public void delete(long id) {
		Customer updateObj = cusRepo.getReferenceById(id);
		if (!Objects.isNull(updateObj)) {
			history = new History(Customer.class.getName(), EAction.DELETE, updateObj.getId().toString());
			hisRepo.save(history);
			cusRepo.deleteById(updateObj.getId());
		}
	}

	@Override
	public Customer updateByUUID(UUID genID, Customer customerRequest) {
		Customer updateObj = cusRepo.findByUUID(genID);
		updateObj.setAddress(customerRequest.getAddress());
		updateObj.setCustomerTypeId(customerRequest.getCustomerTypeId());
		updateObj.setName(customerRequest.getName());
		updateObj.setPhone1(customerRequest.getPhone1());
		updateObj.setPhone2(customerRequest.getPhone2());
		updateObj.setSurrogateId(customerRequest.getSurrogateId());
		history = new History(Customer.class.getName(), EAction.UPDATE, updateObj.getGenId().toString());
		hisRepo.save(history);
		return cusRepo.save(updateObj);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		Customer updateObj = cusRepo.findByUUID(genID);
		if (!Objects.isNull(updateObj)) {
			history = new History(Customer.class.getName(), EAction.DELETE, String.valueOf(genID));
			hisRepo.save(history);
			cusRepo.deleteById(updateObj.getId());
		}

	}

}
