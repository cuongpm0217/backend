package com.hongha.ver1.entities;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_account_invoice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvoice extends BaseEntityAudit {
	@Column
	private long accountId;
	@Column
	private long invoiceId;
	@Column
	private String invoiceType;// bill purchase...
	@Column
	private String invoiceCode;
	@Column
	private BigInteger credit;
	@Column
	private BigInteger debit;
}
