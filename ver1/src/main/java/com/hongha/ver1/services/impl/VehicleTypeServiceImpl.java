package com.hongha.ver1.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.VehicleType;
import com.hongha.ver1.repositories.VehicleTypeRepository;
import com.hongha.ver1.services.VehicleTypeService;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {
	@Autowired
	private VehicleTypeRepository vehicleTypeRepo;

	@Override
	@Transactional
	public VehicleType save(VehicleType vehicleTypeRequest) {
		VehicleType isInserted = vehicleTypeRepo.save(vehicleTypeRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create VehicleType");
		}

	}

	@Override
	public VehicleType findById(long id) {
		VehicleType selected = vehicleTypeRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found VehicleType:" + String.valueOf(id));
		}
	}

	@Override
	public VehicleType findByUUID(UUID genId) {
		VehicleType selected = vehicleTypeRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found VehicleType:" + String.valueOf(genId));
		}
	}

	@Override
	@Transactional
	public VehicleType update(long id, VehicleType vehicleTypeRequest) {
		VehicleType selected = vehicleTypeRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(vehicleTypeRequest, selected);
			return updateObj(vehicleTypeRequest, selected);

		} else {
			throw new RuntimeException("Not found VehicleType:" + String.valueOf(id));
		}
	}

	private VehicleType updateObj(VehicleType vehicleTypeRequest, VehicleType selected) {
		selected.setName(vehicleTypeRequest.getName());
		VehicleType updated = vehicleTypeRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update VehicleType:" + vehicleTypeRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		VehicleType selected = vehicleTypeRepo.getReferenceById(id);
		if (selected != null) {
			vehicleTypeRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found VehicleType:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public VehicleType updateByUUID(UUID genID, VehicleType vehicleTypeRequest) {
		VehicleType selected = vehicleTypeRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(vehicleTypeRequest, selected);
		} else {
			throw new RuntimeException("Not found VehicleType:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		VehicleType selected = vehicleTypeRepo.findByGenId(genID);
		if (selected != null) {
			vehicleTypeRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found VehicleType:" + String.valueOf(genID));
		}
	}

	@Override
	public Slice<VehicleType> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Slice<VehicleType> page = vehicleTypeRepo.findAll(pageable);
		return page;
	}

	@Override
	public Slice<VehicleType> findByName(String name, int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Slice<VehicleType> page = vehicleTypeRepo.findByName(name, pageable);
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
