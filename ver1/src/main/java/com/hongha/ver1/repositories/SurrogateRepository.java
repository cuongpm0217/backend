package com.hongha.ver1.repositories;


import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Surrogate;

@Repository
public interface SurrogateRepository extends JpaRepository<Surrogate, Long>{
	Surrogate findByGenId(UUID genId);
	Slice<Surrogate> findByName(String name,Pageable pageable);
	Slice<Surrogate> findByPhone(String phone,Pageable pageable);
	Slice<Surrogate> findByCustomerId(long customerId,Pageable pageable);
}
