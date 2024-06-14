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
public class ExpenseDTO extends BaseDTO {
	private String name;
	private String detail;
	private BigInteger cost;
}
