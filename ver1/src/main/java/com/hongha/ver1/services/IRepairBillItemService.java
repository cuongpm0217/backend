package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.RepairBillItem;

public interface IRepairBillItemService {
	RepairBillItem save(RepairBillItem repairBillItemRequest);

	RepairBillItem findById(long id);

	RepairBillItem findByUUID(UUID genId);

	List<RepairBillItem> getAll();

	RepairBillItem update(long id, RepairBillItem repairBillItemRequest);

	void delete(long id);

	RepairBillItem updateByUUID(UUID genID, RepairBillItem repairBillItemRequest);

	void deleteByUUID(UUID genID);
}
