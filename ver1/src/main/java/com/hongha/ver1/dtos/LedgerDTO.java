package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LedgerDTO extends BaseDTO {
	private long voucherId;
	private long accountingId;
	private String detail;
	private double credit;// tk có
	private double debit;// tk nợ
}
