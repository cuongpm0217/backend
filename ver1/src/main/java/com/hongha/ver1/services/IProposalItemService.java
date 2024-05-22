package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.ProposalItem;

public interface IProposalItemService {
	ProposalItem save(ProposalItem proposalItemRequest);

	ProposalItem findById(long id);

	ProposalItem findByUUID(UUID genId);

	List<ProposalItem> getAll();

	ProposalItem update(long id, ProposalItem proposalItemRequest);

	void delete(long id);

	ProposalItem updateByUUID(UUID genID, ProposalItem proposalItemRequest);

	void deleteByUUID(UUID genID);
}
