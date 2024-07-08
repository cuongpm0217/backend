package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.ProposalItem;
import com.hongha.ver1.repositories.ProposalItemRepository;
import com.hongha.ver1.services.ProposalItemService;

@Service
public class ProposalItemServiceImpl implements ProposalItemService {
	@Autowired
	private ProposalItemRepository propItemRepo;

	@Override
	@Transactional
	public ProposalItem save(ProposalItem propItemRequest) {
		ProposalItem isInserted = propItemRepo.save(propItemRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create ProposalItem");
		}
	}

	@Override
	public ProposalItem findById(long id) {
		ProposalItem selected = propItemRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found ProposalItem:" + String.valueOf(id));
		}
	}

	@Override
	public ProposalItem findByUUID(UUID genId) {
		ProposalItem selected = propItemRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found ProposalItem:" + String.valueOf(genId));
		}
	}

	@Override
	public List<ProposalItem> getAll() {
		return propItemRepo.findAll();
	}

	@Override
	@Transactional
	public ProposalItem update(long id, ProposalItem propItemRequest) {
		ProposalItem selected = propItemRepo.getReferenceById(id);
		if (selected != null) {
			return updateObj(propItemRequest, selected);
		} else {
			throw new RuntimeException("Not found ProposalItem:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public boolean delete(long id) {
		ProposalItem selected = propItemRepo.getReferenceById(id);
		if (selected != null) {
			propItemRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public ProposalItem updateByUUID(UUID genID, ProposalItem propItemRequest) {
		ProposalItem selected = propItemRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(propItemRequest, selected);
		} else {
			throw new RuntimeException("Not found ProposalItem:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public boolean deleteByUUID(UUID genID) {
		ProposalItem selected = propItemRepo.findByGenId(genID);
		if (selected != null) {
			propItemRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<ProposalItem> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<ProposalItem> page = propItemRepo.findAll(pageable);
		return page;
	}

	@Override
	public Page<ProposalItem> findByProposalId(long proposalId, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<ProposalItem> page = propItemRepo.findByProposalId(proposalId, pageable);
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

	private ProposalItem updateObj(ProposalItem propItemRequest, ProposalItem selected) {
		selected.setNote(propItemRequest.getNote());
		selected.setPrice(propItemRequest.getPrice());
		selected.setProductId(propItemRequest.getProductId());
		selected.setProposalId(propItemRequest.getProposalId());
		selected.setQuantity(propItemRequest.getQuantity());
		selected.setWarranty(propItemRequest.getWarranty());
		ProposalItem updated = propItemRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update ProposalItem:" + selected.getId());
		}
	}
}
