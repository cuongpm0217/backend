package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.RepairBillItemListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(RepairBillItemListener.class)
@Table(name = "_repair_bill_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepairBillItem extends BaseEntityAudit{
	
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private long repairBillId;
	@Column(nullable = false)
	private long productId;
	@Column
	private int quantity;
	@Column
	private double price;
	@Column
	private int guarantee;// follow month	
	@Column
	private String note;
}
