package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.RepairBill;

public interface RepairBillService {
	RepairBill save(RepairBill repairBillRequest);

	RepairBill findById(long id);

	RepairBill findByUUID(UUID genId);

	List<RepairBill> getAll();

	RepairBill update(long id, RepairBill repairBillRequest);

	void delete(long id);

	RepairBill updateByUUID(UUID genID, RepairBill repairBillRequest);

	void deleteByUUID(UUID genID);
}
