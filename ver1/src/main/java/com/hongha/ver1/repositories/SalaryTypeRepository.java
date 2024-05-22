package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.SalaryType;

@Repository
public interface SalaryTypeRepository extends JpaRepository<SalaryType, Long> {
	@Query(value = "select b from _salary_type b where b._gen_id= :uuid", nativeQuery = true)
	SalaryType findByUUID(@Param("uuid") UUID gen_id);
}
