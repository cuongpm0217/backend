package com.hongha.ver1.dtos;

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
	private long categoryId;
	private long brandId;
	private double price;
	private double salePrice;
	private long currencyId;
	private long unitId;
	private String country;
	private int no;
}
