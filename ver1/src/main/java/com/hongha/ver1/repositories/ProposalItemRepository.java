package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.ProposalItem;

@Repository
public interface ProposalItemRepository extends JpaRepository<ProposalItem, Long> {
	ProposalItem findByGenId(UUID genId);

	Page<ProposalItem> findByProposalId(long proposalId, Pageable paging);
}
