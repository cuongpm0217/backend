package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.AccountInvoice;

public interface IAccountInvoiceService {
	AccountInvoice save(AccountInvoice accountInvoiceRequest);

	AccountInvoice findById(long id);

	AccountInvoice findByUUID(UUID genId);

	List<AccountInvoice> getAll();

	AccountInvoice update(long id, AccountInvoice accountInvoiceRequest);

	void delete(long id);

	AccountInvoice updateByUUID(UUID genID, AccountInvoice accountInvoiceRequest);

	void deleteByUUID(UUID genID);
}
