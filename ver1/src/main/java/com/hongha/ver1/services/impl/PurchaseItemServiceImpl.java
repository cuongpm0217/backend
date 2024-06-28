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

import com.hongha.ver1.entities.PurchaseItem;
import com.hongha.ver1.repositories.PurchaseItemRepository;
import com.hongha.ver1.services.PurchaseItemService;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {
	@Autowired
	private PurchaseItemRepository purItemRepo;

	@Override
	@Transactional
	public PurchaseItem save(PurchaseItem purRequest) {
		PurchaseItem isInserted = purItemRepo.save(purRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create PurchaseItem");
		}

	}

	@Override
	public PurchaseItem findById(long id) {
		PurchaseItem selected = purItemRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found PurchaseItem:" + String.valueOf(id));
		}
	}

	@Override
	public PurchaseItem findByUUID(UUID genId) {
		PurchaseItem selected = purItemRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found PurchaseItem:" + String.valueOf(genId));
		}
	}

	@Override
	public List<PurchaseItem> getAll() {
		return purItemRepo.findAll();
	}

	@Override
	@Transactional
	public PurchaseItem update(long id, PurchaseItem purRequest) {
		PurchaseItem selected = purItemRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(purRequest, selected);
			return updateObj(purRequest, selected);

		} else {
			throw new RuntimeException("Not found PurchaseItem:" + String.valueOf(id));
		}
	}

	private PurchaseItem updateObj(PurchaseItem purRequest, PurchaseItem selected) {
		selected.setCost(purRequest.getCost());
		selected.setNote(purRequest.getNote());
		selected.setProductId(purRequest.getProductId());
		selected.setPurchaseId(purRequest.getPurchaseId());
		selected.setQuantity(purRequest.getQuantity());
		selected.setWarranty(purRequest.getWarranty());
		PurchaseItem updated = purItemRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update PurchaseItem:" + purRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		PurchaseItem selected = purItemRepo.getReferenceById(id);
		if (selected != null) {
			purItemRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found PurchaseItem:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public PurchaseItem updateByUUID(UUID genID, PurchaseItem purRequest) {
		PurchaseItem selected = purItemRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(purRequest, selected);
		} else {
			throw new RuntimeException("Not found PurchaseItem:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		PurchaseItem selected = purItemRepo.findByGenId(genID);
		if (selected != null) {
			purItemRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found PurchaseItem:" + String.valueOf(genID));
		}
	}

	@Override
	public Page<PurchaseItem> findByPurchaseId(long purchaseId, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<PurchaseItem> page = purItemRepo.findByPurchaseId(purchaseId, pageable);
		return page;
	}

//	private Pageable genPageable(int pageNo, int pageSize, String sortBy, String sortType) {
//		Sort sort = Sort.by(sortBy);
//		if (sortType.equals("des")) {
//			sort = sort.descending();
//		}
//		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//		return pageable;
//	}
}
