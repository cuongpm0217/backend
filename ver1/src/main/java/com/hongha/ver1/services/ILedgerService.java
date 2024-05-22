package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Ledger;

public interface ILedgerService {
	Ledger save(Ledger ledgerRequest);

	Ledger findById(long id);

	Ledger findByUUID(UUID genId);

	List<Ledger> getAll();

	Ledger update(long id, Ledger ledgerRequest);

	void delete(long id);

	Ledger updateByUUID(UUID genID, Ledger ledgerRequest);

	void deleteByUUID(UUID genID);
}
