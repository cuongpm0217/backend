package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hongha.ver1.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	Vehicle findByGenId(UUID genId);

	Page<Vehicle> findByLicensePlate(String licensePlate, Pageable pageable);
}
