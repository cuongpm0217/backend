package com.hongha.ver1.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	Purchase findByGenId(UUID genId);

	Purchase findByCode(String code);

	Page<Purchase> findByPartnerIdAndCreatedAtBetween(long partnerId, Date fromDate, Date toDate, Pageable paging);

	Page<Purchase> findByCreatedAtBetween(Date fromDate, Date toDate, Pageable paging);

}
