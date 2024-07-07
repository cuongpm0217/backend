package com.hongha.ver1.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Salary;
import com.hongha.ver1.repositories.SalaryRepository;
import com.hongha.ver1.services.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {
	@Autowired
	private SalaryRepository salRepo;

	@Override
	@Transactional
	public Salary save(Salary salRequest) {
		Salary isInserted = salRepo.save(salRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Salary");
		}

	}

	@Override
	public Salary findById(long id) {
		Salary selected = salRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Salary:" + String.valueOf(id));
		}
	}

	@Override
	public Salary findByUUID(UUID genId) {
		Salary selected = salRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Salary:" + String.valueOf(genId));
		}
	}

	@Override
	@Transactional
	public Salary update(long id, Salary salRequest) {
		Salary selected = salRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(salRequest, selected);
			return updateObj(salRequest, selected);

		} else {
			throw new RuntimeException("Not found Salary:" + String.valueOf(id));
		}
	}

	private Salary updateObj(Salary salRequest, Salary selected) {
		selected.setEmployeeId(salRequest.getEmployeeId());
		selected.setName(salRequest.getName());
		selected.setSalaryTypeId(salRequest.getSalaryTypeId());
		selected.setWage(salRequest.getWage());
		Salary updated = salRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Salary:" + salRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Salary selected = salRepo.getReferenceById(id);
		if (selected != null) {
			salRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Salary:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Salary updateByUUID(UUID genID, Salary salRequest) {
		Salary selected = salRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(salRequest, selected);
		} else {
			throw new RuntimeException("Not found Salary:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Salary selected = salRepo.findByGenId(genID);
		if (selected != null) {
			salRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Salary:" + String.valueOf(genID));
		}
	}

	@Override
	public Page<Salary> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Salary> page = salRepo.findAll(pageable);
		return page;
	}

}
