package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.ProductBrand;

public interface IProductBrandService {
	ProductBrand save(ProductBrand productBrandRequest);

	ProductBrand findById(long id);

	ProductBrand findByUUID(UUID genId);

	List<ProductBrand> getAll();

	ProductBrand update(long id, ProductBrand productBrandRequest);

	void delete(long id);

	ProductBrand updateByUUID(UUID genID, ProductBrand productBrandRequest);

	void deleteByUUID(UUID genID);
}
