package com.hongha.ver1.services;

import java.util.UUID;

import org.springframework.data.domain.Slice;

import com.hongha.ver1.entities.Unit;

public interface UnitService {
	Unit save(Unit unitRequest);

	Unit findById(long id);

	Unit findByUUID(UUID genId);

	Unit update(long id, Unit unitRequest);

	boolean delete(long id);

	Unit updateByUUID(UUID genID, Unit unitRequest);

	boolean deleteByUUID(UUID genID);

	Slice<Unit> findByName(String name, int pageNo, int pageSize, String sortBy, String sortType);

	Slice<Unit> getAll(int pageNo, int pageSize, String sortBy, String sortType);
}
