package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO extends BaseDTO {
	private String name;// VND
	private String fullName;// Việt Nam đồng
	private String symbol;// ₫
	private long exchangeVND;// vnd > 1 usd > 24500
}
