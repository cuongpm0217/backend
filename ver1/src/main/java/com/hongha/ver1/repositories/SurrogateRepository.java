package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Surrogate;

@Repository
public interface SurrogateRepository extends JpaRepository<Surrogate, Long>{
	@Query(value = "select b from _surrogate b where b._gen_id= :uuid", nativeQuery = true)
	Surrogate findByUUID(@Param("uuid") UUID gen_id);
}
