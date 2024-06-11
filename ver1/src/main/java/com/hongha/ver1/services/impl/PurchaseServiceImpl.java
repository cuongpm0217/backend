package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Purchase;
import com.hongha.ver1.repositories.PurchaseRepository;
import com.hongha.ver1.services.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private PurchaseRepository purRepo;

	@Override
	@Transactional
	public Purchase save(Purchase purRequest) {
		Purchase isInserted = purRepo.save(purRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Purchase");
		}

	}

	@Override
	public Purchase findById(long id) {
		Purchase selected = purRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(id));
		}
	}

	@Override
	public Purchase findByUUID(UUID genId) {
		Purchase selected = purRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Purchase> getAll() {
		return purRepo.findAll();
	}

	@Override
	@Transactional
	public Purchase update(long id, Purchase purRequest) {
		Purchase selected = purRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(purRequest, selected);
			return updateObj(purRequest, selected);
			
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(id));
		}
	}

	private Purchase updateObj(Purchase purRequest, Purchase selected) {
		selected.setBranchId(purRequest.getBranchId());
		selected.setCode(purRequest.getCode());
		selected.setDateReceipt(purRequest.getDateReceipt());
		selected.setDetail(purRequest.getDetail());
		selected.setEmployeeId(purRequest.getEmployeeId());
		selected.setPartnerId(purRequest.getPartnerId());
		Purchase updated = purRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Purchase:" + purRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Purchase selected = purRepo.getReferenceById(id);
		if (selected != null) {
			purRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Purchase updateByUUID(UUID genID, Purchase purRequest) {
		Purchase selected = purRepo.findByUUID(genID);
		if (selected != null) {
			return updateObj(purRequest, selected);
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Purchase selected = purRepo.findByUUID(genID);
		if (selected != null) {
			purRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(genID));
		}
	}
}
