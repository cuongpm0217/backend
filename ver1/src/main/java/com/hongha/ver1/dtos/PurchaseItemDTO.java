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
public class PurchaseItemDTO extends BaseDTO {
	private long purchaseId;
	private long productId;
	private int quantity;
	private BigInteger cost;
	private int warranty;
	private String note;
}
