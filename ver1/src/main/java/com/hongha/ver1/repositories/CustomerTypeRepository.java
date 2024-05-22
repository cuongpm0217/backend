package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.CustomerType;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType, Long> {
	@Query(value = "select b from _customer_type b where b._gen_id= :uuid", nativeQuery = true)
	CustomerType findByUUID(@Param("uuid") UUID gen_id);
}
