package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.SalaryListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(SalaryListener.class)
@Table(name = "_salary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salary extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
	@Column
	private String name;
	@Column(name = "salary_type_id", nullable = false)
	private long salaryTypeId;
	@Column(name = "employee_id")
	private long employeeId;
	@Column
	private double wage;

}
