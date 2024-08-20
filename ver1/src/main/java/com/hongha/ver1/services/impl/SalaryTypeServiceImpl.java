package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.SalaryType;
import com.hongha.ver1.repositories.SalaryTypeRepository;
import com.hongha.ver1.services.SalaryTypeService;

@Service
public class SalaryTypeServiceImpl implements SalaryTypeService {
	@Autowired
	private SalaryTypeRepository salTypeRepo;

	@Override
	@Transactional
	public SalaryType save(SalaryType salTypeRequest) {
		SalaryType isInserted = salTypeRepo.save(salTypeRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create SalaryType");
		}

	}

	@Override
	public SalaryType findById(long id) {
		SalaryType selected = salTypeRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found SalaryType:" + String.valueOf(id));
		}
	}

	@Override
	public SalaryType findByUUID(UUID genId) {
		SalaryType selected = salTypeRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found SalaryType:" + String.valueOf(genId));
		}
	}

	@Override
	public List<SalaryType> getAll() {
		return salTypeRepo.findAll();
	}

	@Override
	@Transactional
	public SalaryType update(long id, SalaryType salTypeRequest) {
		SalaryType selected = salTypeRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(salTypeRequest, selected);
			return updateObj(salTypeRequest, selected);

		} else {
			throw new RuntimeException("Not found SalaryType:" + String.valueOf(id));
		}
	}

	private SalaryType updateObj(SalaryType salTypeRequest, SalaryType selected) {
		selected.setName(salTypeRequest.getName());
		SalaryType updated = salTypeRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update SalaryType:" + salTypeRequest.getId());
		}
	}

	@Override
	@Transactional
	public boolean delete(long id) {
		SalaryType selected = salTypeRepo.getReferenceById(id);
		if (selected != null) {
			salTypeRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public SalaryType updateByUUID(UUID genID, SalaryType salTypeRequest) {
		SalaryType selected = salTypeRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(salTypeRequest, selected);
		} else {
			throw new RuntimeException("Not found SalaryType:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public boolean deleteByUUID(UUID genID) {
		SalaryType selected = salTypeRepo.findByGenId(genID);
		if (selected != null) {
			salTypeRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}
}
