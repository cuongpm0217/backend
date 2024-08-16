package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalDTO extends BaseDTO {
	private long customerId;
	private long vehicleId;
	private long branchId;
	private long employeeId;
	private double total;
	private int no;
}
