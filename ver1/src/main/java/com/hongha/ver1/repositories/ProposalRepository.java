package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>{
	Proposal findByGenId(UUID genId);
}
