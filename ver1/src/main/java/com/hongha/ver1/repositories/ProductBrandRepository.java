package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.ProductBrand;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {
	@Query(value = "select b from _product_brand b where b._gen_id= :uuid", nativeQuery = true)
	ProductBrand findByUUID(@Param("uuid") UUID gen_id);
}