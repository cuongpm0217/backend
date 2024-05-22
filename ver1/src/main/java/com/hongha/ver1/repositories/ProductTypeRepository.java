package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
	@Query(value = "select b from _product_type b where b._gen_id= :uuid", nativeQuery = true)
	ProductType findByUUID(@Param("uuid") UUID gen_id);
}
