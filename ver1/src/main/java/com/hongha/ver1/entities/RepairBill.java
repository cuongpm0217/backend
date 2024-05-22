package com.hongha.ver1.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_repair_bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairBill extends BaseEntityAudit{
	@Column
	private String code;
	@Column
	private String detail;
	@Column
	private long customerId;
	@Column
	private Date startedDate;
	@Column
	private Date endDate;
	@Column
	private String licensePlate;
	@Column
	private String vehicle;
	@Column
	private long branchId;
	@Column
	private long employeeId;
	
}
