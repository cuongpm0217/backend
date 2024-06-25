package com.hongha.ver1.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.Ledger;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long>{
	Ledger findByGenId(UUID genId);
	Page<Ledger> findByCreateAtBetween(Date fromDate,Date toDate,Pageable paging);
}
