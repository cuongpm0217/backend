package com.hongha.ver1.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairBillDTO extends BaseDTO {
	private String code;
	private String detail;
	private long customerId;
	private Date startedDate;
	private Date endDate;
	private long vehicleId;
	private long branchId;
	private long employeeId;
	private long surrogateId;
	private double total;
	private int no;
}
