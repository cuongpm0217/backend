package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "select b from _product b where b._gen_id= :uuid", nativeQuery = true)
	Product findByUUID(@Param("uuid") UUID gen_id);
}
