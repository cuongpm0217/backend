package com.hongha.ver1.entities;

import java.math.BigInteger;

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
@Table(name="_ledger")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ledger extends BaseEntityAudit{
	
	private static final long serialVersionUID = 1L;
	@Column
	private long accountingVoucherId;//code PM0000001.... 
	@Column
	private long accountingId;
	@Column
	private String detail;
	@Column
	private BigInteger credit;//tk có
	@Column
	private BigInteger debit;//tk nợ
}
