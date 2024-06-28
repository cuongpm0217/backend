package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import com.hongha.ver1.entities.ProposalItem;

public interface ProposalItemService {
	ProposalItem save(ProposalItem proposalItemRequest);

	ProposalItem findById(long id);

	ProposalItem findByUUID(UUID genId);

	List<ProposalItem> getAll();

	ProposalItem update(long id, ProposalItem proposalItemRequest);

	void delete(long id);

	ProposalItem updateByUUID(UUID genID, ProposalItem proposalItemRequest);

	void deleteByUUID(UUID genID);

	Page<ProposalItem> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<ProposalItem> findByProposalId(long proposalId, int pageNo, int pageSize, String sortBy, String sortType);
}
