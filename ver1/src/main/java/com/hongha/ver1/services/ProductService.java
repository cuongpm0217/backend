package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Product;

public interface ProductService {
	Product save(Product productRequest);

	Product findById(long id);

	Product findByUUID(UUID genId);

	List<Product> getAll();

	Product update(long id, Product productRequest);

	void delete(long id);

	void deleteByUUID(UUID genID);

	Product updateByUUID(UUID genID, Product productRequest);
}
