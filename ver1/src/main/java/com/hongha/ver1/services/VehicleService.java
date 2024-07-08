package com.hongha.ver1.services;

import java.util.UUID;
import org.springframework.data.domain.Page;
import com.hongha.ver1.entities.Vehicle;

public interface VehicleService {
	Vehicle save(Vehicle vehicleRequest);

	Page<Vehicle> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Vehicle findById(long id);

	Vehicle update(long id, Vehicle vehicleRequest);

	boolean delete(long id);

	Page<Vehicle> findByLicensePlate(String licensePlate,int pageNo, int pageSize, String sortBy, String sortType);

	Vehicle findByUUID(UUID genId);

	Vehicle updateByUUID(UUID genID, Vehicle vehicleRequest);

	boolean deleteByUUID(UUID genID);
}
