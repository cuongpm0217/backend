package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.LedgerListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(LedgerListener.class)
@Table(name = "_ledger")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ledger extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
	@Column(name = "invoice_code")
	private long invoiceCode;// code PM0000001....
	@Column(name = "invoice_id")
	private long invoiceId;
	@Column(name = "account_id")
	private long accountId;
	@Column
	private String detail;
	@Column
	private double credit;// tk có
	@Column
	private double debit;// tk nợ
}
