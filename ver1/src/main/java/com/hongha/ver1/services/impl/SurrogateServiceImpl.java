package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Surrogate;
import com.hongha.ver1.repositories.SurrogateRepository;
import com.hongha.ver1.services.SurrogateService;

@Service
public class SurrogateServiceImpl implements SurrogateService{
	@Autowired
	private SurrogateRepository surRepo;

	@Override
	@Transactional
	public Surrogate save(Surrogate surRequest) {
		Surrogate isInserted = surRepo.save(surRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Surrogate");
		}

	}

	@Override
	public Surrogate findById(long id) {
		Surrogate selected = surRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Surrogate:" + String.valueOf(id));
		}
	}

	@Override
	public Surrogate findByUUID(UUID genId) {
		Surrogate selected = surRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Surrogate:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Surrogate> getAll() {
		return surRepo.findAll();
	}

	@Override
	@Transactional
	public Surrogate update(long id, Surrogate surRequest) {
		Surrogate selected = surRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(surRequest, selected);
			return updateObj(surRequest, selected);
			
		} else {
			throw new RuntimeException("Not found Surrogate:" + String.valueOf(id));
		}
	}

	private Surrogate updateObj(Surrogate surRequest, Surrogate selected) {
		selected.setAddress(surRequest.getAddress());
		selected.setCompany(surRequest.getCompany());
		selected.setName(surRequest.getName());
		selected.setPhone(surRequest.getPhone());
		Surrogate updated = surRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Surrogate:" + surRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Surrogate selected = surRepo.getReferenceById(id);
		if (selected != null) {
			surRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Surrogate:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Surrogate updateByUUID(UUID genID, Surrogate surRequest) {
		Surrogate selected = surRepo.findByUUID(genID);
		if (selected != null) {
			return updateObj(surRequest, selected);
		} else {
			throw new RuntimeException("Not found Surrogate:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Surrogate selected = surRepo.findByUUID(genID);
		if (selected != null) {
			surRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Surrogate:" + String.valueOf(genID));
		}
	}
}
