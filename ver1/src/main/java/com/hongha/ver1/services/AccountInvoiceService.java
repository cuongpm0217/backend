package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.AccountInvoice;

public interface AccountInvoiceService {
	AccountInvoice save(AccountInvoice accountInvoiceRequest);

	AccountInvoice findById(long id);

	AccountInvoice findByUUID(UUID genId);
	

	AccountInvoice update(long id, AccountInvoice accountInvoiceRequest);

	void delete(long id);

	AccountInvoice updateByUUID(UUID genID, AccountInvoice accountInvoiceRequest);

	void deleteByUUID(UUID genID);

	Page<AccountInvoice> getAll(int pageNo, int pageSize, String sortBy);
	Page<AccountInvoice> findByInvoiceId(long invoiceId,int pageNo, int pageSize, String sortBy);
	List<AccountInvoice> findByInvoiceId(long invoiceId);
}
