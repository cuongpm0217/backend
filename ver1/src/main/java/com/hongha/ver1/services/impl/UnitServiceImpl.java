package com.hongha.ver1.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Unit;
import com.hongha.ver1.repositories.UnitRepository;
import com.hongha.ver1.services.UnitService;

@Service
public class UnitServiceImpl implements UnitService {
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
		Unit selected = unitRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(genId));
		}
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
		Unit selected = unitRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(unitRequest, selected);
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Unit selected = unitRepo.findByGenId(genID);
		if (selected != null) {
			unitRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Unit:" + String.valueOf(genID));
		}
	}

	@Override
	public Slice<Unit> findByName(String name, int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Slice<Unit> page = unitRepo.findByName(name, pageable);
		return page;
	}

	@Override
	public Slice<Unit> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Slice<Unit> page = unitRepo.findAll(pageable);
		return page;
	}

	private Pageable genPageable(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return pageable;
	}
}
