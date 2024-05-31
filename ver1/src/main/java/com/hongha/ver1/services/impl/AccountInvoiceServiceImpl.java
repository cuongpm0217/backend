package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.AccountInvoice;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.enums.EAction;
import com.hongha.ver1.repositories.AccountInvoiceRepository;
import com.hongha.ver1.repositories.HistoryRepository;
import com.hongha.ver1.services.AccountInvoiceService;

@Service
public class AccountInvoiceServiceImpl implements AccountInvoiceService {
	@Autowired
	private AccountInvoiceRepository accInvRepo;
	@Autowired
	private HistoryRepository historyRepo;
	private History history;

	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return username;
	}

	@Override
	public AccountInvoice save(AccountInvoice accountInvoiceRequest) {
		accountInvoiceRequest.setCreatedBy(getUserName());
		accountInvoiceRequest.setUpdatedBy(getUserName());
		history = new History(AccountInvoice.class.getName(), EAction.CREATE, accountInvoiceRequest.getId().toString());
		historyRepo.save(history);
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
		updateObj.setUpdatedBy(getUserName());
		history = new History(AccountInvoice.class.getName(), EAction.UPDATE, updateObj.getId().toString());
		historyRepo.save(history);
		return accInvRepo.save(updateObj);
	}

	@Override
	public void delete(long id) {
		accInvRepo.deleteById(id);
		history = new History(AccountInvoice.class.getName(), EAction.DELETE, String.valueOf(id));
		historyRepo.save(history);
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
		updateObj.setUpdatedBy(getUserName());
		history = new History(AccountInvoice.class.getName(), EAction.UPDATE, updateObj.getGenId().toString());
		historyRepo.save(history);
		return accInvRepo.save(updateObj);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		AccountInvoice updateObj = accInvRepo.findByUUID(genID);
		if (updateObj.getId() != null)
			accInvRepo.deleteById(updateObj.getId());
		history = new History(AccountInvoice.class.getName(), EAction.DELETE, updateObj.getGenId().toString());
		historyRepo.save(history);
	}

}
