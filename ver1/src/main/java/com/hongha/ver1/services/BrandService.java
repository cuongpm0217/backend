package com.hongha.ver1.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Brand;

public interface BrandService {
	Brand save(Brand productBrandRequest);

	Brand findById(long id);

	Brand findByUUID(UUID genId);

	List<Brand> getAll() throws IOException;

	Brand update(long id, Brand productBrandRequest);

	void delete(long id);

	Brand updateByUUID(UUID genID, Brand productBrandRequest);

	void deleteByUUID(UUID genID);

	Page<Brand> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<Brand> findByNameLike(String name, int pageNo, int pageSize, String sortBy,String sortType);
}
