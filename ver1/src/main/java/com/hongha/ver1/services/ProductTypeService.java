package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.ProductType;

public interface ProductTypeService {
	ProductType save(ProductType productTypeRequest);

	ProductType findById(long id);

	ProductType findByUUID(UUID genId);

	List<ProductType> getAll();

	ProductType update(long id, ProductType productTypeRequest);

	void delete(long id);

	ProductType updateByUUID(UUID genID, ProductType productTypeRequest);

	void deleteByUUID(UUID genID);
}
