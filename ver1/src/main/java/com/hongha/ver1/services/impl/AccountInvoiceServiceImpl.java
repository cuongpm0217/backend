package com.hongha.ver1.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		AccountInvoice selected = accInvRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(genId));
		}
	}

	@Override
	public Page<AccountInvoice> getAll(int pageNo, int pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<AccountInvoice> pageResult = accInvRepo.findAll(paging);
		return pageResult;
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
	public boolean delete(long id) {
		AccountInvoice del = accInvRepo.getReferenceById(id);
		if (del != null) {
			accInvRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public AccountInvoice updateByUUID(UUID genID, AccountInvoice accountInvoiceRequest) {
		AccountInvoice updateObj = accInvRepo.findByGenId(genID);
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
	public boolean deleteByUUID(UUID genID) {
		AccountInvoice updateObj = accInvRepo.findByGenId(genID);
		if (updateObj != null) {
			accInvRepo.deleteById(updateObj.getId());
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Page<AccountInvoice> findByInvoiceId(long invoiceId, int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<AccountInvoice> page = accInvRepo.findByInvoiceId(invoiceId, pageable);
		return page;
	}

	@Override
	public List<AccountInvoice> findByInvoiceId(long invoiceId) {
		List<AccountInvoice> list = accInvRepo.findByInvoiceId(invoiceId);
		if (!list.isEmpty()) {
			return list;
		} else {
			return new ArrayList<AccountInvoice>();
		}
	}

}
