package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.hongha.ver1.entities.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
	VehicleType findByGenId(@Param("uuid") UUID genId);
	Slice<VehicleType> findByName(String name,Pageable pageable);
}
