package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Position;

public interface IPositionService {
	Position save(Position positionRequest);

	Position findById(long id);

	Position findByUUID(UUID genId);

	List<Position> getAll();

	Position update(long id, Position positionRequest);

	void delete(long id);

	Position updateByUUID(UUID genID, Position positionRequest);

	void deleteByUUID(UUID genID);
}
