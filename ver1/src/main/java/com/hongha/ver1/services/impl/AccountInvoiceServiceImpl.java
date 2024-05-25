package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.AccountInvoice;
import com.hongha.ver1.repositories.AccountInvoiceRepository;
import com.hongha.ver1.services.AccountInvoiceService;

@Service
public class AccountInvoiceServiceImpl implements AccountInvoiceService{
	@Autowired
	private AccountInvoiceRepository accInvRepo;
	@Override
	public AccountInvoice save(AccountInvoice accountInvoiceRequest) {
		return accInvRepo.save(accountInvoiceRequest);
	}

	@Override
	public AccountInvoice findById(long id) {
		return accInvRepo.getReferenceById(id);
	}

	@Override
	public AccountInvoice findByUUID(UUID genId) {
		return accInvRepo.findByUUID(genId);
	}

	@Override
	public List<AccountInvoice> getAll() {
		return accInvRepo.findAll();
	}

	@Override
	public AccountInvoice update(long id, AccountInvoice accountInvoiceRequest) {
		AccountInvoice updateObj = accInvRepo.getReferenceById(id);
		updateObj.setAccountId(accountInvoiceRequest.getAccountId());
		updateObj.setCredit(accountInvoiceRequest.getCredit());
		updateObj.setDebit(accountInvoiceRequest.getDebit());
		updateObj.setInvoiceCode(accountInvoiceRequest.getInvoiceCode());
		updateObj.setInvoiceId(accountInvoiceRequest.getInvoiceId());
		updateObj.setInvoiceType(accountInvoiceRequest.getInvoiceType());
		
		return accInvRepo.save(updateObj);
	}

	@Override
	public void delete(long id) {
		accInvRepo.deleteById(id);		
	}

	@Override
	public AccountInvoice updateByUUID(UUID genID, AccountInvoice accountInvoiceRequest) {
		AccountInvoice updateObj = accInvRepo.findByUUID(genID);
		updateObj.setAccountId(accountInvoiceRequest.getAccountId());
		updateObj.setCredit(accountInvoiceRequest.getCredit());
		updateObj.setDebit(accountInvoiceRequest.getDebit());
		updateObj.setInvoiceCode(accountInvoiceRequest.getInvoiceCode());
		updateObj.setInvoiceId(accountInvoiceRequest.getInvoiceId());
		updateObj.setInvoiceType(accountInvoiceRequest.getInvoiceType());
		
		return accInvRepo.save(updateObj);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		AccountInvoice updateObj = accInvRepo.findByUUID(genID);
		if(updateObj.getId()!=null)
			accInvRepo.deleteById(updateObj.getId());		
	}

}
