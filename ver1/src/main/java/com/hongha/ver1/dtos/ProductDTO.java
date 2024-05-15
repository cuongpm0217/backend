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
public class ProductDTO extends BaseDTO {
	private String name;
	private long productTypeId;
	private long productBrandId;
	private BigInteger price;
	private long currencyId;
	private long unitId;
}
