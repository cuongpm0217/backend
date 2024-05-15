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
public class LedgerDTO extends BaseDTO {
	private long accountingVoucherId;
	private long accountingId;
	private String detail;
	private BigInteger credit;// tk có
	private BigInteger debit;// tk nợ
}
