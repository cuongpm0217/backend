package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Unit;
import com.hongha.ver1.repositories.UnitRepository;
import com.hongha.ver1.services.UnitService;

@Service
public class UnitServiceImpl implements UnitService{
	@Autowired
	private UnitRepository unitRepo;

	@Override
	@Transactional
	public Unit save(Unit unitRequest) {
		Unit isInserted = unitRepo.save(unitRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Unit");
		}

	}

	@Override
	public Unit findById(long id) {
		Unit selected = unitRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(id));
		}
	}

	@Override
	public Unit findByUUID(UUID genId) {
		Unit selected = unitRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Unit> getAll() {
		return unitRepo.findAll();
	}

	@Override
	@Transactional
	public Unit update(long id, Unit unitRequest) {
		Unit selected = unitRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(unitRequest, selected);
			return updateObj(unitRequest, selected);
			
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(id));
		}
	}

	private Unit updateObj(Unit unitRequest, Unit selected) {
		selected.setName(unitRequest.getName());
		Unit updated = unitRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Unit:" + unitRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Unit selected = unitRepo.getReferenceById(id);
		if (selected != null) {
			unitRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Unit updateByUUID(UUID genID, Unit unitRequest) {
		Unit selected = unitRepo.findByUUID(genID);
		if (selected != null) {
			return updateObj(unitRequest, selected);
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Unit selected = unitRepo.findByUUID(genID);
		if (selected != null) {
			unitRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(genID));
		}
	}
}
