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
@Table(name = "_repair_bill_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairBillItem {
	@Column
	private long repairBillId;
	@Column
	private long productId;
	@Column
	private int quantity;
	@Column
	private BigInteger price;
	@Column
	private int warranty;// follow month
	@Column
	private long employeeId;
	@Column
	private String note;
}
