package com.hongha.ver1.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.AccountInvoice;

@Repository
public interface AccountInvoiceRepository extends JpaRepository<AccountInvoice, Long> {
	AccountInvoice findByGenId(UUID genId);
	Page<AccountInvoice> findByInvoiceId(long invoiceId,Pageable pageable);
	List<AccountInvoice> findByInvoiceId(long invoiceId);
}
