package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
		ProposalItem selected = propItemRepo.findByUUID(genId);
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
				throw new RuntimeException("Can't update ProposalItem:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found ProposalItem:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		ProposalItem selected = propItemRepo.getReferenceById(id);
		if (selected != null) {
			propItemRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found ProposalItem:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public ProposalItem updateByUUID(UUID genID, ProposalItem propItemRequest) {
		ProposalItem selected = propItemRepo.findByUUID(genID);
		if (selected != null) {
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
				throw new RuntimeException("Can't update ProposalItem:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found ProposalItem:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		ProposalItem selected = propItemRepo.findByUUID(genID);
		if (selected != null) {
			propItemRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found ProposalItem:" + String.valueOf(genID));
		}
	}
}
