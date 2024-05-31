package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.CustomerType;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.enums.EAction;
import com.hongha.ver1.repositories.CustomerTypeRepository;
import com.hongha.ver1.repositories.HistoryRepository;
import com.hongha.ver1.services.CustomerTypeService;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {
	@Autowired
	private CustomerTypeRepository cusTypeRepo;
	@Autowired
	private HistoryRepository hisRepo;
	private History his;

	@Override
	public CustomerType save(CustomerType customerTypeRequest) {
		his = new History(CustomerType.class.getName(), EAction.CREATE, customerTypeRequest.getId().toString());
		hisRepo.save(his);
		return cusTypeRepo.save(customerTypeRequest);
	}

	@Override
	public CustomerType findById(long id) {
		return cusTypeRepo.getReferenceById(id);
	}

	@Override
	public CustomerType findByUUID(UUID genId) {
		return cusTypeRepo.findByUUID(genId);
	}

	@Override
	public List<CustomerType> getAll() {
		return cusTypeRepo.findAll();
	}

	@Override
	public CustomerType update(long id, CustomerType customerTypeRequest) {
		CustomerType updateObj = cusTypeRepo.getReferenceById(id);
		updateObj.setName(customerTypeRequest.getName());
		his = new History(CustomerType.class.getName(), EAction.UPDATE, updateObj.getId().toString());
		hisRepo.save(his);
		return cusTypeRepo.save(updateObj);
	}

	@Override
	public void delete(long id) {
		CustomerType updateObj = cusTypeRepo.getReferenceById(id);
		if (!Objects.isNull(updateObj)) {
			his = new History(CustomerType.class.getName(), EAction.DELETE, updateObj.getId().toString());
			hisRepo.save(his);
			cusTypeRepo.deleteById(id);
		}
	}

	@Override
	public CustomerType updateByUUID(UUID genID, CustomerType customerTypeRequest) {
		CustomerType updateObj = cusTypeRepo.findByUUID(genID);
		updateObj.setName(customerTypeRequest.getName());
		his = new History(CustomerType.class.getName(), EAction.UPDATE, updateObj.getGenId().toString());
		hisRepo.save(his);
		return cusTypeRepo.save(updateObj);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		CustomerType updateObj = cusTypeRepo.findByUUID(genID);
		if (!Objects.isNull(updateObj)) {
			his = new History(CustomerType.class.getName(), EAction.DELETE, updateObj.getGenId().toString());
			hisRepo.save(his);
			cusTypeRepo.deleteById(updateObj.getId());
		}

	}
}
