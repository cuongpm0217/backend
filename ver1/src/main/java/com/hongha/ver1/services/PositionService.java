package com.hongha.ver1.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Position;

public interface PositionService {
	Position save(Position positionRequest);

	Position findById(long id);

	Position findByUUID(UUID genId);

	List<Position> getAll() throws IOException;

	Position update(long id, Position positionRequest);

	boolean delete(long id);

	Position updateByUUID(UUID genID, Position positionRequest);

	boolean deleteByUUID(UUID genID);

	Page<Position> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<Position> findBySearchText(String searchText, int pageNo, int pageSize, String sortBy, String sortType);
}
