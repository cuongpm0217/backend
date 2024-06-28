package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO extends BaseDTO {
	private String code;
	private String detail;
	private long partnerId;
	private long employeeId;
	private long branchId;
	private double total;
	private int no;
}
