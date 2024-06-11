package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Ledger;
import com.hongha.ver1.repositories.LedgerRepository;
import com.hongha.ver1.services.LedgerService;

@Service
public class LedgerServiceImpl implements LedgerService {
	@Autowired
	private LedgerRepository ledRepo;

	@Override
	@Transactional
	public Ledger save(Ledger ledgerRequest) {
		Ledger isInserted = ledRepo.save(ledgerRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Ledger");
		}

	}

	@Override
	public Ledger findById(long id) {
		Ledger selected = ledRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Ledger:" + String.valueOf(id));
		}
	}

	@Override
	public Ledger findByUUID(UUID genId) {
		Ledger selected = ledRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Ledger:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Ledger> getAll() {
		return ledRepo.findAll();
	}

	@Override
	@Transactional
	public Ledger update(long id, Ledger ledgerRequest) {
		Ledger selected = ledRepo.getReferenceById(id);
		if (selected != null) {
			selected.setAccountingId(ledgerRequest.getAccountingId());
			selected.setAccountingVoucherId(ledgerRequest.getAccountingVoucherId());
			selected.setCredit(ledgerRequest.getCredit());
			selected.setDebit(ledgerRequest.getDebit());
			selected.setDetail(ledgerRequest.getDetail());
			Ledger updated = ledRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Ledger:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Ledger:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Ledger selected = ledRepo.getReferenceById(id);
		if (selected != null) {
			ledRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Ledger:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Ledger updateByUUID(UUID genID, Ledger ledgerRequest) {
		Ledger selected = ledRepo.findByUUID(genID);
		if (selected != null) {
			selected.setAccountingId(ledgerRequest.getAccountingId());
			selected.setAccountingVoucherId(ledgerRequest.getAccountingVoucherId());
			selected.setCredit(ledgerRequest.getCredit());
			selected.setDebit(ledgerRequest.getDebit());
			selected.setDetail(ledgerRequest.getDetail());
			Ledger updated = ledRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Ledger:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Ledger:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Ledger selected = ledRepo.findByUUID(genID);
		if (selected != null) {
			ledRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Ledger:" + String.valueOf(genID));
		}
	}
}
