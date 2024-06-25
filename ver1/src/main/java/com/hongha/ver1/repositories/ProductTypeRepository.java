package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
	ProductType findByGenId(UUID genId);
}
