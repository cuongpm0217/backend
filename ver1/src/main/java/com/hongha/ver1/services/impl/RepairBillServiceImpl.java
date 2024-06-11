package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.RepairBill;
import com.hongha.ver1.repositories.RepairBillRepository;
import com.hongha.ver1.services.RepairBillService;

@Service
public class RepairBillServiceImpl implements RepairBillService{
	@Autowired
	private RepairBillRepository billRepo;

	@Override
	@Transactional
	public RepairBill save(RepairBill billRequest) {
		RepairBill isInserted = billRepo.save(billRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create RepairBill");
		}

	}

	@Override
	public RepairBill findById(long id) {
		RepairBill selected = billRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(id));
		}
	}

	@Override
	public RepairBill findByUUID(UUID genId) {
		RepairBill selected = billRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(genId));
		}
	}

	@Override
	public List<RepairBill> getAll() {
		return billRepo.findAll();
	}

	@Override
	@Transactional
	public RepairBill update(long id, RepairBill billRequest) {
		RepairBill selected = billRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(billRequest, selected);
			return updateObj(billRequest, selected);
			
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(id));
		}
	}

	private RepairBill updateObj(RepairBill billRequest, RepairBill selected) {
		selected.setBranchId(billRequest.getBranchId());
		selected.setCode(billRequest.getCode());
		selected.setCustomerId(billRequest.getCustomerId());
		selected.setDetail(billRequest.getDetail());
		selected.setEmployeeId(billRequest.getEmployeeId());
		selected.setLicensePlate(billRequest.getLicensePlate());
		selected.setPayerId(billRequest.getPayerId());
		selected.setSurrogateId(billRequest.getSurrogateId());
		selected.setVehicle(billRequest.getVehicle());
		selected.setStartedDate(billRequest.getStartedDate());
		selected.setEndDate(billRequest.getEndDate());
		RepairBill updated = billRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update RepairBill:" + billRequest.getId());
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		RepairBill selected = billRepo.getReferenceById(id);
		if (selected != null) {
			billRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public RepairBill updateByUUID(UUID genID, RepairBill billRequest) {
		RepairBill selected = billRepo.findByUUID(genID);
		if (selected != null) {
			return updateObj(billRequest, selected);
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		RepairBill selected = billRepo.findByUUID(genID);
		if (selected != null) {
			billRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(genID));
		}
	}
}
