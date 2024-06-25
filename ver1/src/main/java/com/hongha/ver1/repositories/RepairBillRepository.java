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
	Page<RepairBill> findByStartedDateBetween(Date fromDate,Date toDate,Pageable pageable);
	//call proc find by license plate
}
