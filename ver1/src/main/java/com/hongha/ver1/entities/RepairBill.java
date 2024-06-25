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
public class RepairBill extends BaseEntityAudit {
	private static final long serialVersionUID = 1L;
	@Column
	private String code;
	@Column
	private String detail;
	@Column(name = "customer_id")
	private long customerId;
	@Column(name = "started_date")
	private Date startedDate;
	@Column(name = "end_date")
	private Date endDate = startedDate;//default
	@Column(name = "vehicle_id")
	private long vehicleId;
	@Column(name = "branch_id")
	private long branchId;
	@Column(name = "employee_id")
	private long employeeId;
	@Column(name = "surrogate_id")
	private long surrogateId;
	@Column
	private double total;
}
