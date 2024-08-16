package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalItemDTO extends BaseDTO {
	private long proposalId;
	private long productId;
	private int quantity;
	private double price;
	private int warranty;
	private String note;
	private int no = this.getId().intValue();
}
