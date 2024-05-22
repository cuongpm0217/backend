package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Proposal;

public interface IProposalService {
	Proposal save(Proposal proposalRequest);

	Proposal findById(long id);

	Proposal findByUUID(UUID genId);

	List<Proposal> getAll();

	Proposal update(long id, Proposal proposalRequest);

	void delete(long id);

	Proposal updateByUUID(UUID genID, Proposal proposalRequest);

	void deleteByUUID(UUID genID);
}
