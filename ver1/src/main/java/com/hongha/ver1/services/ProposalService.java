package com.hongha.ver1.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Slice;

import com.hongha.ver1.entities.Proposal;

public interface ProposalService {
	Proposal save(Proposal proposalRequest);

	Proposal findById(long id);

	Proposal findByUUID(UUID genId);

	List<Proposal> getAll();

	Proposal update(long id, Proposal proposalRequest);

	boolean delete(long id);

	Proposal updateByUUID(UUID genID, Proposal proposalRequest);

	boolean deleteByUUID(UUID genID);

	Slice<Proposal> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Slice<Proposal> findByCreatedAt(Date fromDate, Date toDate, int pageNo, int pageSize, String sortBy,
			String sortType);
}
