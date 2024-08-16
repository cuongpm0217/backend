package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.ProposalListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(ProposalListener.class)
@Table(name = "_proposal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proposal extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
	@Column
	private long customerId;
	@Column(name = "vehicle_id")
	private long vehicleId;
	@Column
	private long branchId;
	@Column
	private long employeeId;
	@Column
	private double total;
}
