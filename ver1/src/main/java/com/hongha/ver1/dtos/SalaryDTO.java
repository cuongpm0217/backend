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
public class SalaryDTO extends BaseDTO {
	private String name;
	private long salaryTypeId;
	private long employeeId;
	private BigInteger wage;

}
