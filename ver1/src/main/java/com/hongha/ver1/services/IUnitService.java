package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Unit;

public interface IUnitService {
	Unit save(Unit unitRequest);

	Unit findById(long id);

	Unit findByUUID(UUID genId);

	List<Unit> getAll();

	Unit update(long id, Unit unitRequest);

	void delete(long id);

	Unit updateByUUID(UUID genID, Unit unitRequest);

	void deleteByUUID(UUID genID);
}
