package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.AccountInvoice;
import com.hongha.ver1.repositories.AccountInvoiceRepository;
import com.hongha.ver1.services.AccountInvoiceService;

@Service
public class AccountInvoiceServiceImpl implements AccountInvoiceService {
	@Autowired
	private AccountInvoiceRepository accInvRepo;

	@Override
	@Transactional
	public AccountInvoice save(AccountInvoice accountInvoiceRequest) {
		AccountInvoice isInserted = accInvRepo.save(accountInvoiceRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create");
		}
	}

	@Override
	public AccountInvoice findById(long id) {
		AccountInvoice selected = accInvRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(id));
		}
	}

	@Override
	public AccountInvoice findByUUID(UUID genId) {
		AccountInvoice selected = accInvRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(genId));
		}
	}

	@Override
	public List<AccountInvoice> getAll() {
		return accInvRepo.findAll();
	}

	@Override
	@Transactional
	public AccountInvoice update(long id, AccountInvoice accountInvoiceRequest) {
		AccountInvoice updateObj = accInvRepo.getReferenceById(id);
		if (updateObj != null) {
			updateObj.setAccountId(accountInvoiceRequest.getAccountId());
			updateObj.setCredit(accountInvoiceRequest.getCredit());
			updateObj.setDebit(accountInvoiceRequest.getDebit());
			updateObj.setInvoiceCode(accountInvoiceRequest.getInvoiceCode());
			updateObj.setInvoiceId(accountInvoiceRequest.getInvoiceId());
			updateObj.setInvoiceType(accountInvoiceRequest.getInvoiceType());
			AccountInvoice updated = accInvRepo.save(updateObj);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update");
			}
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		AccountInvoice del = accInvRepo.getReferenceById(id);
		if (del != null) {
			accInvRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public AccountInvoice updateByUUID(UUID genID, AccountInvoice accountInvoiceRequest) {
		AccountInvoice updateObj = accInvRepo.findByUUID(genID);
		if (updateObj != null) {
			updateObj.setAccountId(accountInvoiceRequest.getAccountId());
			updateObj.setCredit(accountInvoiceRequest.getCredit());
			updateObj.setDebit(accountInvoiceRequest.getDebit());
			updateObj.setInvoiceCode(accountInvoiceRequest.getInvoiceCode());
			updateObj.setInvoiceId(accountInvoiceRequest.getInvoiceId());
			updateObj.setInvoiceType(accountInvoiceRequest.getInvoiceType());
			AccountInvoice updated = accInvRepo.save(updateObj);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update");
			}
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		AccountInvoice updateObj = accInvRepo.findByUUID(genID);
		if (updateObj != null)
			accInvRepo.deleteById(updateObj.getId());
		else
			throw new RuntimeException("Not found:" + String.valueOf(genID));

	}

}
