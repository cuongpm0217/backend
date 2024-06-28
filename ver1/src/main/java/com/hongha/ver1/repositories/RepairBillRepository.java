package com.hongha.ver1.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.RepairBill;

@Repository
public interface RepairBillRepository extends JpaRepository<RepairBill, Long> {
	RepairBill findByGenId(UUID genId);

	Page<RepairBill> findByStartedDateBetween(Date fromDate, Date toDate, Pageable pageable);

	RepairBill findByCode(String code);

	@Query("select r.* from _repair_bill r left join _vehicle v on r.vehicle_id = v.id where v.license_plate like :licensePlate")
	Page<RepairBill> findByLicensePlate(@Param("licensePlate") String licensePlate, Pageable pageable);
}
