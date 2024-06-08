package com.hongha.ver1.entities;

import java.util.Date;

import com.hongha.ver1.entities.listeners.RepairBillListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(RepairBillListener.class)
@Table(name = "_repair_bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairBill extends BaseEntityAudit{
	
	private static final long serialVersionUID = 1L;
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
