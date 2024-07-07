package com.hongha.ver1.services.impl;

import java.util.Date;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Proposal;
import com.hongha.ver1.repositories.ProposalRepository;
import com.hongha.ver1.services.ProposalService;

@Service
public class ProposalServiceImpl implements ProposalService {
	@Autowired
	private ProposalRepository proposalRepo;

	@Override
	@Transactional
	public Proposal save(Proposal proposalRequest) {
		Proposal isInserted = proposalRepo.save(proposalRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Proposal");
		}

	}

	@Override
	public Proposal findById(long id) {
		Proposal selected = proposalRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Proposal:" + String.valueOf(id));
		}
	}

	@Override
	public Proposal findByUUID(UUID genId) {
		Proposal selected = proposalRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Proposal:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Proposal> getAll() {
		return proposalRepo.findAll();
	}

	@Override
	@Transactional
	public Proposal update(long id, Proposal proposalRequest) {
		Proposal selected = proposalRepo.getReferenceById(id);
		if (selected != null) {
			return updateObj(proposalRequest, selected);
		} else {
			throw new RuntimeException("Not found Proposal:" + String.valueOf(id));
		}
	}

	private Proposal updateObj(Proposal proposalRequest, Proposal selected) {
		selected.setBranchId(proposalRequest.getBranchId());
		selected.setCustomerId(proposalRequest.getCustomerId());
		selected.setEmployeeId(proposalRequest.getEmployeeId());
		selected.setVehicleId(proposalRequest.getVehicleId());
		selected.setTotal(proposalRequest.getTotal());
		Proposal updated = proposalRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Proposal:" + selected.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Proposal selected = proposalRepo.getReferenceById(id);
		if (selected != null) {
			proposalRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Proposal:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Proposal updateByUUID(UUID genID, Proposal proposalRequest) {
		Proposal selected = proposalRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(proposalRequest, selected);
		} else {
			throw new RuntimeException("Not found Proposal:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Proposal selected = proposalRepo.findByGenId(genID);
		if (selected != null) {
			proposalRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Proposal:" + String.valueOf(genID));
		}
	}

	@Override
	public Slice<Proposal> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Slice<Proposal> page = proposalRepo.findAll(pageable);
		return page;
	}

	@Override
	public Slice<Proposal> findByCreatedAt(Date fromDate, Date toDate, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Slice<Proposal> page = proposalRepo.findByCreatedAtBetween(fromDate, toDate, pageable);
		return page;
	}

	private Pageable genPageable(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return pageable;
	}
}
