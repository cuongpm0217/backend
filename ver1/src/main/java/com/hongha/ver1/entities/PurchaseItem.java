package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.PurchaseItemListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(PurchaseItemListener.class)
@Table(name = "_purchaseItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem extends BaseEntityAudit{
	
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private long purchaseId;
	@Column(nullable = false)
	private long productId;
	@Column
	private int quantity;
	@Column
	private double cost;
	@Column
	private int warranty;
	@Column
	private String note;
}
