package com.hongha.ver1.reporitories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Branch;

@Repository
public interface BranchReporitory extends JpaRepository<Branch, Long> {
	@Query(value = "select b from _branch b where b._gen_id= :uuid", nativeQuery = true)
	Branch findByUUID(@Param("uuid") UUID gen_id);
}
