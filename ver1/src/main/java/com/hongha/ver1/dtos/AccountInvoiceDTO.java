package com.hongha.ver1.dtos;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvoiceDTO extends BaseDTO {
	private long accountId;
	private long invoiceId;
	private String invoiceType;// bill purcharse...
	private String invoiceCode;
	private BigInteger credit;
	private BigInteger debit;
}
