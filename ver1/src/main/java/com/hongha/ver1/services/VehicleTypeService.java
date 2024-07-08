package com.hongha.ver1.services;

import java.util.UUID;

import org.springframework.data.domain.Slice;

import com.hongha.ver1.entities.VehicleType;

public interface VehicleTypeService {
	VehicleType save(VehicleType vehicleTypeRequest);

	Slice<VehicleType> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	VehicleType findById(long id);

	VehicleType update(long id, VehicleType vehicleTypeRequest);

	boolean delete(long id);

	Slice<VehicleType> findByName(String name, int pageNo, int pageSize, String sortBy, String sortType);

	VehicleType findByUUID(UUID genId);

	VehicleType updateByUUID(UUID genID, VehicleType vehicleTypeRequest);

	boolean deleteByUUID(UUID genID);
}
