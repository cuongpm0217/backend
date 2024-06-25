package com.hongha.ver1.entities;

import java.util.Date;

import com.hongha.ver1.entities.listeners.PurchaseListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(PurchaseListener.class)
@Table(name = "_purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntityAudit{
	private static final long serialVersionUID = 1L;
	@Column
	private String code;
	@Column
	private String detail;	
	@Column(name="date_receipt")
	private Date dateReceipt;
	@Column(name="partner_id")
	private long partnerId;
	@Column(name="employee_id")
	private long employeeId;	
	@Column(name="branch_id")
	private long branchId;
	@Column
	private double total;
}
