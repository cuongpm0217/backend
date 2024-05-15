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
@Table(name="_ledger")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ledger extends BaseEntityAudit{
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
