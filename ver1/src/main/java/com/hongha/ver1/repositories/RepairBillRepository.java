package com.hongha.ver1.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.RepairBill;

@Repository
public interface RepairBillRepository extends JpaRepository<RepairBill, Long> {
	RepairBill findByGenId(UUID genId);

	Page<RepairBill> findByStartedDateBetween(Date fromDate, Date toDate, Pageable pageable);

	RepairBill findByCode(String code);

//	@Query("select _repair_bill.id,_repair_bill.gen_id,_repair_bill.code,_repair_bill.detail,"
//			+ "_repair_bill.customer_id,_repair_bill.started_date,_repair_bill.end_date,_repair_bill.vehicle_id,"
//			+ "_repair_bill.branch_id,_repair_bill.employee_id,_repair_bill.surrogate_id"
//			+ "from _repair_bill left join _vehicle  on _repair_bill.vehicle_id = _vehicle.id where _vehicle.license_plate like :licensePlate")
//	Page<RepairBill> findByLicensePlate(@Param("licensePlate") String licensePlate, Pageable pageable);


}
