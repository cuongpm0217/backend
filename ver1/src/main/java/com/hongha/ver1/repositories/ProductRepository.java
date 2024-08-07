package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByGenId(UUID genId);

	Page<Product> findByProductCategoryId(long categoryId, Pageable pageable);

}
