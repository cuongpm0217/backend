package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairBillItemDTO extends BaseDTO {
	private long repairBillId;
	private long productId;
	private int quantity;
	private double price;
	private int warranty;// follow month
	private long employeeId;
	private String note;
	private int no = this.getId().intValue();
}
