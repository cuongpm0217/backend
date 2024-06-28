package com.hongha.ver1.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import com.hongha.ver1.entities.Ledger;

public interface LedgerService {
	Ledger save(Ledger ledgerRequest);

	Ledger findById(long id);

	Ledger findByUUID(UUID genId);

	List<Ledger> getAll();

	Ledger update(long id, Ledger ledgerRequest);

	void delete(long id);

	Ledger updateByUUID(UUID genID, Ledger ledgerRequest);

	void deleteByUUID(UUID genID);

	Page<Ledger> findByCreateAtBetween(Date fromDate, Date toDate, int pageSize, String sortBy, String sortType);

	Page<Ledger> getAll(int pageNo, int pageSize, String sortBy, String sortType);
}
