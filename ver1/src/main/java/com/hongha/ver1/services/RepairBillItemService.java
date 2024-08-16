package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.RepairBillItem;

public interface RepairBillItemService {
	RepairBillItem save(RepairBillItem repairBillItemRequest);

	RepairBillItem findById(long id);

	RepairBillItem findByUUID(UUID genId);

	List<RepairBillItem> getAll();

	RepairBillItem update(long id, RepairBillItem repairBillItemRequest);

	boolean delete(long id);

	RepairBillItem updateByUUID(UUID genID, RepairBillItem repairBillItemRequest);

	boolean deleteByUUID(UUID genID);

	Page<RepairBillItem> findByRepairBillId(long repairBillId, int pageNo, int pageSize, String sortBy,
			String sortType);
}
