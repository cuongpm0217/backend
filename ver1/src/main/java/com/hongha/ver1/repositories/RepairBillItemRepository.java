package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.RepairBillItem;

@Repository
public interface RepairBillItemRepository extends JpaRepository<RepairBillItem, Long> {
	RepairBillItem findByGenId(UUID genId);

	Page<RepairBillItem> findByRepairBillId(long repairBillId, Pageable pageable);
}
