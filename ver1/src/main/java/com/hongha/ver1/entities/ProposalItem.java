package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.ProposalItemListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(ProposalItemListener.class)
@Table(name = "_proposalItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalItem extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private long proposalId;
	@Column(nullable = false)
	private long productId;
	@Column
	private int quantity;
	@Column
	private double price;
	@Column
	private int warranty;
	@Column
	private String note;
}
