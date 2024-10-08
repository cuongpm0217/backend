package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
	Branch findByGenId(UUID genId);

	Page<Branch> findByNameContainingOrAddressContaining(String name, String address, Pageable paging);
}
