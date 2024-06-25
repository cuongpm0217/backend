package com.hongha.ver1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hongha.ver1.entities.AccountInvoice;

@Repository
public interface AccountInvoiceRepository extends JpaRepository<AccountInvoice, Long> {
	AccountInvoice findByGenId(UUID genId);
}
