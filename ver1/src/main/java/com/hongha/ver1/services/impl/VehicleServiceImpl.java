package com.hongha.ver1.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Vehicle;
import com.hongha.ver1.repositories.VehicleRepository;
import com.hongha.ver1.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleRepository vehicleRepo;

	@Override
	@Transactional
	public Vehicle save(Vehicle vehicleRequest) {
		Vehicle isInserted = vehicleRepo.save(vehicleRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Vehicle");
		}

	}

	@Override
	public Vehicle findById(long id) {
		Vehicle selected = vehicleRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Vehicle:" + String.valueOf(id));
		}
	}

	@Override
	public Vehicle findByUUID(UUID genId) {
		Vehicle selected = vehicleRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Vehicle:" + String.valueOf(genId));
		}
	}

	@Override
	@Transactional
	public Vehicle update(long id, Vehicle vehicleRequest) {
		Vehicle selected = vehicleRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(vehicleRequest, selected);
			return updateObj(vehicleRequest, selected);

		} else {
			throw new RuntimeException("Not found Vehicle:" + String.valueOf(id));
		}
	}

	private Vehicle updateObj(Vehicle vehicleRequest, Vehicle selected) {
		selected.setLicensePlate(vehicleRequest.getLicensePlate());
		selected.setModel(vehicleRequest.getModel());
		selected.setVehicleTypeId(vehicleRequest.getVehicleTypeId());
		selected.setYearOfFactory(vehicleRequest.getYearOfFactory());
		Vehicle updated = vehicleRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Vehicle:" + vehicleRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Vehicle selected = vehicleRepo.getReferenceById(id);
		if (selected != null) {
			vehicleRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Vehicle:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Vehicle updateByUUID(UUID genID, Vehicle vehicleRequest) {
		Vehicle selected = vehicleRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(vehicleRequest, selected);
		} else {
			throw new RuntimeException("Not found Vehicle:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Vehicle selected = vehicleRepo.findByGenId(genID);
		if (selected != null) {
			vehicleRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Vehicle:" + String.valueOf(genID));
		}
	}

	@Override
	public Page<Vehicle> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPagable(pageNo, pageSize, sortBy, sortType);
		Page<Vehicle> page = vehicleRepo.findAll(pageable);
		return page;
	}

	@Override
	public Page<Vehicle> findByLicensePlate(String licensePlate, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Pageable pageable = genPagable(pageNo, pageSize, sortBy, sortType);
		Page<Vehicle> page = vehicleRepo.findByLicensePlate(licensePlate, pageable);
		return page;
	}

	private Pageable genPagable(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return pageable;
	}

}
