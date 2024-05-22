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
@Table(name = "_purchaseItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem extends BaseEntityAudit{
	@Column(nullable = false)
	private long purchaseId;
	@Column(nullable = false)
	private long productId;
	@Column
	private int quantity;
	@Column
	private BigInteger cost;
	@Column
	private int warranty;
	@Column
	private String note;
}