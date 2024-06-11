package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.RepairBillItem;
import com.hongha.ver1.repositories.RepairBillItemRepository;
import com.hongha.ver1.services.RepairBillItemService;

@Service
public class RepairBillItemServiceImpl implements RepairBillItemService{
	@Autowired
	private RepairBillItemRepository billItemRepo;

	@Override
	@Transactional
	public RepairBillItem save(RepairBillItem billItemRequest) {
		RepairBillItem isInserted = billItemRepo.save(billItemRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create RepairBillItem");
		}

	}

	@Override
	public RepairBillItem findById(long id) {
		RepairBillItem selected = billItemRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found RepairBillItem:" + String.valueOf(id));
		}
	}

	@Override
	public RepairBillItem findByUUID(UUID genId) {
		RepairBillItem selected = billItemRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found RepairBillItem:" + String.valueOf(genId));
		}
	}

	@Override
	public List<RepairBillItem> getAll() {
		return billItemRepo.findAll();
	}

	@Override
	@Transactional
	public RepairBillItem update(long id, RepairBillItem billItemRequest) {
		RepairBillItem selected = billItemRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(billItemRequest, selected);
			return updateObj(billItemRequest, selected);
			
		} else {
			throw new RuntimeException("Not found RepairBillItem:" + String.valueOf(id));
		}
	}

	private RepairBillItem updateObj(RepairBillItem billItemRequest, RepairBillItem selected) {
		selected.setEmployeeId(billItemRequest.getEmployeeId());
		selected.setNote(billItemRequest.getNote());
		selected.setPrice(billItemRequest.getPrice());
		selected.setProductId(billItemRequest.getProductId());
		selected.setQuantity(billItemRequest.getQuantity());
		selected.setRepairBillId(billItemRequest.getRepairBillId());
		selected.setWarranty(billItemRequest.getWarranty());
		RepairBillItem updated = billItemRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update RepairBillItem:" + billItemRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		RepairBillItem selected = billItemRepo.getReferenceById(id);
		if (selected != null) {
			billItemRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found RepairBillItem:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public RepairBillItem updateByUUID(UUID genID, RepairBillItem billItemRequest) {
		RepairBillItem selected = billItemRepo.findByUUID(genID);
		if (selected != null) {
			return updateObj(billItemRequest, selected);
		} else {
			throw new RuntimeException("Not found RepairBillItem:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		RepairBillItem selected = billItemRepo.findByUUID(genID);
		if (selected != null) {
			billItemRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found RepairBillItem:" + String.valueOf(genID));
		}
	}
}
