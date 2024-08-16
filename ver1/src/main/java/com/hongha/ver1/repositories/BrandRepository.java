package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	Brand findByGenId(UUID genId);

	Page<Brand> findByNameLike(String name, Pageable pageable);
}
