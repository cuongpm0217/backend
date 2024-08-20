package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
	Unit findByGenId(UUID genId);

	Slice<Unit> findByName(String name, Pageable pageable);
}
