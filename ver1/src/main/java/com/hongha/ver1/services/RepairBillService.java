package com.hongha.ver1.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.RepairBill;

public interface RepairBillService {
	RepairBill save(RepairBill repairBillRequest);

	RepairBill findById(long id);

	RepairBill findByUUID(UUID genId);

	RepairBill update(long id, RepairBill repairBillRequest);

	boolean delete(long id);

	RepairBill updateByUUID(UUID genID, RepairBill repairBillRequest);

	boolean deleteByUUID(UUID genID);

	Page<RepairBill> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<RepairBill> findByStartedDateBetween(Date fromDate, Date toDate, int pageNo, int pageSize, String sortBy,
			String sortType);

//	Page<RepairBill> findByLicensePlate(String licensePlate, int pageNo, int pageSize, String sortBy, String sortType);

}
