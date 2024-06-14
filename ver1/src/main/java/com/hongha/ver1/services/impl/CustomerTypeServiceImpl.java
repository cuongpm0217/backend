package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.CustomerType;
import com.hongha.ver1.entities.enums.ECustomerType;
import com.hongha.ver1.repositories.CustomerTypeRepository;
import com.hongha.ver1.services.CustomerTypeService;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {
	@Autowired
	private CustomerTypeRepository cusTypeRepo;

	@Override
	@Transactional
	public CustomerType save(CustomerType customerTypeRequest) {
		CustomerType isInserted = cusTypeRepo.save(customerTypeRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Customer Type");
		}
	}

	@Override
	public CustomerType findById(long id) {
		CustomerType selected = cusTypeRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Customer Type:" + String.valueOf(id));
		}
	}

	@Override
	public CustomerType findByUUID(UUID genId) {
		CustomerType selected = cusTypeRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Customer Type:" + String.valueOf(genId));
		}
	}

	@Override
	public List<CustomerType> getAll() {
		List<CustomerType> list = cusTypeRepo.findAll();
		if(list.isEmpty()) {
			CustomerType cusType = new CustomerType();
			cusType.setName(ECustomerType.ECustomerType_INVIDUAL);
			cusTypeRepo.save(cusType);
			CustomerType cusTypeOrg = new CustomerType();
			cusTypeOrg.setName(ECustomerType.ECustomerType_ORGANIZATION);
			cusTypeRepo.save(cusTypeOrg);
			CustomerType cusTypePartner = new CustomerType();
			cusTypePartner.setName(ECustomerType.ECustomerType_PARTNER);
			cusTypeRepo.save(cusTypePartner);
		}
		return list;
	}

	@Override
	@Transactional
	public CustomerType update(long id, CustomerType customerTypeRequest) {
		CustomerType updateObj = cusTypeRepo.getReferenceById(id);
		if (updateObj != null) {
			updateObj.setName(customerTypeRequest.getName());
			CustomerType updated = cusTypeRepo.save(updateObj);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Customer Type:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Customer Type:" + String.valueOf(id));
		}

	}

	@Override
	@Transactional
	public void delete(long id) {
		CustomerType updateObj = cusTypeRepo.getReferenceById(id);
		if (updateObj != null) {
			cusTypeRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Customer Type:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public CustomerType updateByUUID(UUID genID, CustomerType customerTypeRequest) {
		CustomerType updateObj = cusTypeRepo.findByUUID(genID);
		if (updateObj != null) {
			updateObj.setName(customerTypeRequest.getName());
			CustomerType updated = cusTypeRepo.save(updateObj);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Customer Type:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Customer Type:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		CustomerType updateObj = cusTypeRepo.findByUUID(genID);
		if (updateObj != null) {
			cusTypeRepo.deleteById(updateObj.getId());
		} else {
			throw new RuntimeException("Not found Customer Type:" + String.valueOf(genID));
		}

	}
}
