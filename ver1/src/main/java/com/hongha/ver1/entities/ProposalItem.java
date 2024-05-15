package com.hongha.ver1.entities;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_proposalItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalItem {
	@Column
	private long proposalId;
	@Column
	private long productId;
	@Column
	private int quantity;
	@Column
	private BigInteger price;
	@Column
	private int warranty;
	@Column
	private String note;
}
